package pp.weiba.thirdparty.baidu.web.resource.security.authentication;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.net.client.model.HttpResponse;
import pp.weiba.framework.security.authentication.AbstractAuthentication;
import pp.weiba.framework.security.authentication.AbstractScheduledRefreshAuthentication;
import pp.weiba.framework.security.authentication.AuthenticationManager;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.LoginStatusResponse;
import pp.weiba.thirdparty.baidu.web.client.netdisk.response.TemplateVariableResponse;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.NetDiskAuthentication;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.WebOAuthLoginAuthentication;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.response.AccessToken;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.response.AuthenticationApiClient;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

/**
 * 百度网盘认证信息统一处理
 *
 * @author weiba
 * @date 2024/3/8 11:10
 */
@Log4j2
public class BaiduNetDiskWebAuthentication extends AbstractScheduledRefreshAuthentication<NetDiskAuthentication> {

    private final AuthenticationApiClient authenticationApiClient;

    private final ICredential<NetDiskAuthentication> credential;

    public BaiduNetDiskWebAuthentication(String authenticationId, String authenticationType, AuthenticationApiClient authenticationApiClient, ICredential<NetDiskAuthentication> credential) {
        super(authenticationId, authenticationType, credential);
        this.authenticationApiClient = authenticationApiClient;
        this.credential = credential;
    }

    @Override
    public void detectionAuthentication() {
        if (authentication == null || CollUtil.isEmpty(authentication.getDomainCookieMap())) {
            // 记录日志，抛出异常
            log.error("百度网盘认证信息为空");
            throw new RuntimeException("百度网盘认证信息为空");
        }
        LoginStatusResponse loginStatus = authenticationApiClient.getLoginStatus();
        authentication.setLoginInfo(loginStatus.getLoginInfo());
    }

    @Override
    protected void scheduledRefreshAuthenticationRun() {
        super.scheduledRefreshAuthenticationRun();
    }

    @Override
    public void completeAuthenticationInformation() {
        TemplateVariableResponse templateVariable = authenticationApiClient.getTemplateVariable();
        authentication.setTemplateVariable(templateVariable.getResult());
        super.completeAuthenticationInformation();
    }

    @Override
    protected void appLogin() {
        // accessToken
        if (authentication.getAccessToken() == null) {
            openApiLogin(authentication);
        }

        // 网盘认证
        if (authentication.getTemplateVariable() == null) {
            netDiskLogin(authentication);
        }
        super.appLogin();
    }


    private void netDiskLogin(NetDiskAuthentication netDiskAuthentication) {
        WebOAuthLoginAuthentication webOAuthLoginAuthentication = netDiskAuthentication.getWebOAuthLoginAuthentication();

        HttpResponse httpResponse = authenticationApiClient.oauthDomain(webOAuthLoginAuthentication, "https://pan.baidu.com/disk/home");

        Map<String, HttpCookie> netDiskDomainCookieMap = new HashMap<>();
        // 获取网盘认证信息
        HttpCookie stoekn = httpResponse.getCookieMap().get("STOKEN");
        netDiskDomainCookieMap.put("STOKEN", stoekn);
        netDiskDomainCookieMap.put("BDUSS", webOAuthLoginAuthentication.getCookieMap().get("BDUSS"));
        netDiskDomainCookieMap.put("BDUSS_BFESS", webOAuthLoginAuthentication.getCookieMap().get("BDUSS_BFESS"));
        netDiskDomainCookieMap.put("BAIDUID", webOAuthLoginAuthentication.getCookieMap().get("BAIDUID"));
        netDiskDomainCookieMap.put("BAIDUID_BFESS", webOAuthLoginAuthentication.getCookieMap().get("BAIDUID_BFESS"));
        netDiskDomainCookieMap.put("newlogin", new HttpCookie("newlogin", "1"));

        netDiskAuthentication.setDomainCookieMap(netDiskDomainCookieMap);
    }

    @Override
    protected void doLogout() {
        authenticationApiClient.signOut(authenticationId);
        AuthenticationManager.removeAuthentication(authenticationId, authenticationType);
    }

    protected void openApiLogin(NetDiskAuthentication netDiskAuthentication) {
        AccessToken accessToken = authenticationApiClient.getAccessToken(netDiskAuthentication.getWebOAuthLoginAuthentication());
        if (accessToken == null) {
            return;
        }
        // 存储到认证管理器， 补全的请求要使用
        netDiskAuthentication.setAccessToken(accessToken);
    }

}
