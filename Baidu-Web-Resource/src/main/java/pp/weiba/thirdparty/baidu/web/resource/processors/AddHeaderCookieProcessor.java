package pp.weiba.thirdparty.baidu.web.resource.processors;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.HttpRequest;
import pp.weiba.framework.core.convert.IDataProcessor;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.Authentication;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.AuthenticationManager;

/**
 * 为请求添加认证信息头
 *
 * @author weiba
 * @date 2024/3/7 16:48
 */
@Log4j2
public class AddHeaderCookieProcessor implements IDataProcessor<HttpRequest> {

    @Override
    public HttpRequest process(HttpRequest request) {
        String identityInformationId = (String) request.getBuildParams().get("authenticationId");
        String identityInformationType = (String) request.getBuildParams().get("authenticationType");
        String cookie = getCookie(identityInformationId, identityInformationType);
        request.addheader("Cookie", cookie);
        return request;
    }

    public String getCookie(String identityInformationId, String identityInformationType) {
        Authentication authentication = AuthenticationManager.getAuthentication(identityInformationId, identityInformationType);
        if (authentication != null) {
            return authentication.getCookie();
        }
        return null;
    }
}
