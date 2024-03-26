package pp.weiba.thirdparty.baidu.web.resource.security.authentication;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.AbstractAuthentication;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.LoginStatusResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.TemplateVariableResponse;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.AccessToken;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.Authentication;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.AuthenticationApiClient;

/**
 * 百度网盘认证信息统一处理
 *
 * @author weiba
 * @date 2024/3/8 11:10
 */
@Log4j2
public class BaiduWebAuthentication extends AbstractAuthentication<Authentication> {


    private final AuthenticationApiClient authenticationApiClient;

    private final ICredential<Authentication> credential;

    public BaiduWebAuthentication(String authenticationId, String authenticationType, AuthenticationApiClient authenticationApiClient, ICredential<Authentication> credential) {
        super(authenticationId, authenticationType);
        this.authenticationApiClient = authenticationApiClient;
        this.credential = credential;
    }

    @Override
    protected Authentication initAuthentication() {
        Authentication authentication = credential.getCredential();
        // 存储到认证管理器， 补全的请求要使用
        BaiduAuthenticationManager.setAuthentication(authenticationId, authenticationType, authentication);
        return authentication;
    }

    @Override
    public Authentication detectionAuthentication(Authentication authentication) {
        if (authentication == null || StrUtil.isBlank(authentication.getBduss()) || StrUtil.isBlank(authentication.getStoken())) {
            // 记录日志，抛出异常
            log.error("百度网盘认证信息为空");
            throw new RuntimeException("百度网盟能认证信息为空");
        }
        LoginStatusResponse loginStatus = authenticationApiClient.getLoginStatus();
        authentication.setLoginInfo(loginStatus.getLoginInfo());
        return authentication;
    }

    @Override
    public Authentication completeAuthenticationInformation(Authentication authentication) {
        TemplateVariableResponse templateVariable = authenticationApiClient.getTemplateVariable();
        authentication.setTemplateVariable(templateVariable.getResult());
        initAccessToken();
        return authentication;
    }

    @Override
    protected void doLogout() {
        authenticationApiClient.signOut(authenticationId);
        BaiduAuthenticationManager.removeAuthentication(authenticationId, authenticationType);
        BaiduAuthenticationManager.removeAuthentication(authenticationId, authenticationType + "_accessToken");
    }


    protected void initAccessToken() {
        AccessToken accessToken = authenticationApiClient.getAccessToken();
        if (accessToken == null) {
            return;
        }
        // 存储到认证管理器， 补全的请求要使用
        BaiduAuthenticationManager.setAuthentication(authenticationId, authenticationType + "_accessToken", accessToken);
    }

}
