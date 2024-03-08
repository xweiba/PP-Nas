package pp.weiba.thirdparty.baidu.web.api.security.authentication;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.api.netdisk.base.BaseApiClient;
import pp.weiba.thirdparty.baidu.web.api.netdisk.base.LoginStatusResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.base.TemplateVariableResponse;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.credentials.ICredential;

/**
 * 百度网盘认证信息统一处理
 *
 * @author weiba
 * @date 2024/3/8 11:10
 */
@Log4j2
public class BaiduWebAuthentication extends AbstractAuthentication<Authentication> {


    private final BaseApiClient baseApiClient;

    private final ICredential<Authentication> credential;

    public BaiduWebAuthentication(String authenticationId, String authenticationType, BaseApiClient baseApiClient, ICredential<Authentication> credential) {
        super(authenticationId, authenticationType);
        this.baseApiClient = baseApiClient;
        this.credential = credential;
    }

    @Override
    public Authentication initAuthentication() {
        return credential.getCredential();
    }

    @Override
    public Authentication detectionAuthentication(Authentication authentication) {
        if (authentication == null || StrUtil.isBlank(authentication.getBduss()) || StrUtil.isBlank(authentication.getStoken())) {
            // 记录日志，抛出异常
            log.error("百度网盘认证信息为空");
            throw new RuntimeException("百度网盟能认证信息为空");
        }
        AuthenticationManager.setAuthentication(authenticationId + "_" + authenticationType, authentication);

        LoginStatusResponse loginStatus = baseApiClient.getLoginStatus(baseApiClient.initBuildParams(authenticationId, authenticationType));
        authentication.setLoginInfo(loginStatus.getLoginInfo());
        return authentication;
    }

    @Override
    public Authentication completeAuthenticationInformation(Authentication authentication) {
        TemplateVariableResponse templateVariable = baseApiClient.getTemplateVariable(baseApiClient.initBuildParams(authenticationId, authenticationType));
        authentication.setTemplateVariable(templateVariable.getResult());
        return authentication;
    }
}
