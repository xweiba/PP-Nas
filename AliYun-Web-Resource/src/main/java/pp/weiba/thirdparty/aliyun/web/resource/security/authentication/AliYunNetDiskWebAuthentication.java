package pp.weiba.thirdparty.aliyun.web.resource.security.authentication;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.AbstractScheduledRefreshAuthentication;
import pp.weiba.framework.security.authentication.AuthenticationManager;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.thirdparty.aliyun.web.client.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.OpenApiAuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.model.NetDiskAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.model.OpenApiAuthenticationInfo;
import pp.weiba.thirdparty.aliyun.web.client.authentication.request.OpenAuthorizationPkceType;
import pp.weiba.thirdparty.aliyun.web.client.authentication.request.OpenAuthorizationRequest;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.OpenAccessTokenResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.AliYunUtils;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.SignInApiClient;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.SignatureInfo;
import pp.weiba.utils.ScheduledRunnable;
import pp.weiba.utils.ScheduledUtils;
import pp.weiba.utils.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 百度网盘认证信息统一处理
 *
 * @author weiba
 * @date 2024/3/8 11:10
 */
@Log4j2
public class AliYunNetDiskWebAuthentication extends AbstractScheduledRefreshAuthentication<NetDiskAuthentication> {


    private final AuthenticationApiClient authenticationApiClient;

    private final OpenApiAuthenticationApiClient openApiAuthenticationApiClient;

    public final SignInApiClient signInApiClient;

    public String openApiClientId;

    private String scheduledRefreshSignatureId;

    private String scheduledRefreshOpenApiAccessTokenId;

    public AliYunNetDiskWebAuthentication(String authenticationId, String authenticationType, AuthenticationApiClient authenticationApiClient, ICredential<NetDiskAuthentication> credential, SignInApiClient signInApiClient, OpenApiAuthenticationApiClient openApiAuthenticationApiClient, String openApiClientId) {
        super(authenticationId, authenticationType, credential);
        this.authenticationApiClient = authenticationApiClient;
        this.signInApiClient = signInApiClient;
        this.openApiAuthenticationApiClient = openApiAuthenticationApiClient;
        this.openApiClientId = openApiClientId;
    }

    @Override
    protected void initAuthentication() {
        super.initAuthentication();
        if (authentication.getToken() == null || StrUtil.isBlank(authentication.getToken().getAccessToken())) {
            // 记录日志，抛出异常
            log.error("阿里云盘认证信息为空");
            throw new RuntimeException("阿里云盘认证信息为空");
        }
    }

    private void initOpenAuthentication() {
        // openApi login
        OpenApiAuthenticationInfo openApiAuthenticationInfo = authentication.getOpenApiAuthenticationInfo();

        String newOpenApiClientIdTemp = (openApiAuthenticationInfo != null && StringUtils.isNotBlank(openApiAuthenticationInfo.getAppId()) && !openApiAuthenticationInfo.getAppId().equals(openApiClientId)) ? openApiAuthenticationInfo.getAppId() : null;

        if (openApiAuthenticationInfo == null || StringUtils.isNotBlank(newOpenApiClientIdTemp)) {
            if (StringUtils.isNotBlank(newOpenApiClientIdTemp)) {
                // 使用配置文件中的id
                openApiClientId = newOpenApiClientIdTemp;
                openApiAuthenticationInfo.setAccessToken(null);
            }
            getOpenApiAccessToken();
        }

        // 添加定时任务
        this.scheduleRefreshOpeonApiAccessToken();
    }

    private void getOpenApiAccessToken() {
        OpenAccessTokenResponse openAccessToken = openApiAuthenticationApiClient.getOpenAccessToken(new OpenAuthorizationRequest(openApiClientId, OpenAuthorizationPkceType.PLAIN));
        OpenApiAuthenticationInfo openApiAuthenticationInfo = new OpenApiAuthenticationInfo(openApiClientId, openAccessToken);
        authentication.setOpenApiAuthenticationInfo(openApiAuthenticationInfo);

        // 将数据写回配置文件
        credential.refresh();
    }

    private void scheduleRefreshOpeonApiAccessToken() {
        OpenAccessTokenResponse accessToken = authentication.getOpenApiAuthenticationInfo().getAccessToken();
        Long expiresIn = accessToken.getExpiresIn();
        Long createTime = accessToken.getCreateTime();
        long initNextDelay = expiresIn - ((new Date().getTime() - createTime) / 1000);
        ScheduledRunnable openApiAccessTokenScheduledRunnable = ScheduledRunnable.builder().command(() -> getOpenApiAccessToken())
                .businessId(authenticationId)
                .businessType("refresh_open_api_access_token_" + authenticationType)
                .firstNotDelay(false)
                .isRandom(false)
                .initNextDelay(initNextDelay)
                .delay(expiresIn).unit(TimeUnit.SECONDS).build();
        scheduledRefreshOpenApiAccessTokenId = ScheduledUtils.schedule(openApiAccessTokenScheduledRunnable);
    }


    @Override
    public void detectionAuthentication() {
        if (authentication.getUserInfo() == null) {
            authentication.setUserInfo(this.authenticationApiClient.getUserInfo());
        }

        if (authentication.getSBoxInfo() == null) {
            authentication.setSBoxInfo(this.authenticationApiClient.getSBoxInfo());
        }
    }

    @Override
    protected void completeAuthenticationInformation() {
        /* 待 refreshSignature 解决后在放出*/
        // createSession();

        // 添加签名定时随机刷新
        scheduledRefreshSignature();

        // 初始化签到
        // initSignIn();

        initOpenAuthentication();

        super.completeAuthenticationInformation();
    }

    @Deprecated
    private void initSignIn() {
        try {
            // 每次登录执行一次领取所有签到奖励
            signInApiClient.signInRewardAll();
            // 定时刷新， 每天自动签到
            scheduledRefreshSignIn();
        } catch (Exception e) {
            log.error("签到异常: {}", ExceptionUtil.getMessage(e));
        }
    }

    private void refreshSignatureRun() {
        // 签到及领取奖励
        signInApiClient.todaySignInAndReward();
    }



    private void createSession() {
        String deviceId = authentication.getToken().getDeviceId();
        // 生成公钥与私钥
        SignatureInfo signatureInfo = AliYunUtils.createSignatureInfo(authentication.getUserInfo().getUserId(), deviceId);

        authentication.setSignatureInfo(signatureInfo);

        String xSignature = AliYunUtils.buildXSignature(signatureInfo);
        signatureInfo.setXSignature(xSignature);

        // 将公钥设置到服务器
        authenticationApiClient.createSession(signatureInfo.getPublicKey());
    }


    public void scheduledRefreshSignature() {
        // 30 - 40 分钟刷新一次
        ScheduledRunnable signatureScheduledRunnable = ScheduledRunnable.builder().command(() -> createSession())
//        ScheduledRunnable signatureScheduledRunnable = ScheduledRunnable.builder().command(() -> refreshSignature())
                .businessId(authenticationId)
                .businessType("refresh_signature_" + authenticationType)
//                .delay(3).build();
                .delay(30).build();
        scheduledRefreshSignatureId = ScheduledUtils.schedule(signatureScheduledRunnable);
    }

    private void scheduledRefreshSignIn() {
        // 每24 - 34随机小时刷新一次
        ScheduledRunnable signatureScheduledRunnable = ScheduledRunnable.builder().command(() -> refreshSignatureRun())
                .businessId(authenticationId)
                .businessType("today_signin_and_reward" + authenticationType)
                .delay(24).unit(TimeUnit.HOURS).build();
        scheduledRefreshSignatureId = ScheduledUtils.schedule(signatureScheduledRunnable);
    }

    /**
     * 刷新会话， xSignature 计算有问题，待正常后再放出
     *
     * @author weiba
     * @date 2024/5/10 9:16
     */
    public void refreshSignature() {
        SignatureInfo signatureInfo = authentication.getSignatureInfo();
        if (signatureInfo != null) {
            if (signatureInfo.getNonce() > 2147483647) {
                signatureInfo.setNonce(0);
            } else {
                signatureInfo.setNonce(signatureInfo.getNonce() + 1);
            }
            String xSignature = AliYunUtils.buildXSignature(signatureInfo);
            log.info("刷新签名：{} -> {}", signatureInfo.getXSignature(), xSignature);
            signatureInfo.setXSignature(xSignature);
        }
        this.authenticationApiClient.refreshSession();
    }

    @Override
    protected void doLogout() {
        authenticationApiClient.signOut(authenticationId);
        ScheduledUtils.cancel(scheduledRefreshSignatureId);
        ScheduledUtils.cancel(scheduledRefreshOpenApiAccessTokenId);
        AuthenticationManager.removeAuthentication(authenticationId, authenticationType);
    }

}
