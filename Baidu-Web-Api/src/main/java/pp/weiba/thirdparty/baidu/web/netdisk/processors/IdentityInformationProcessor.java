package pp.weiba.thirdparty.baidu.web.netdisk.processors;

import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.client.HttpRequest;
import pp.weiba.thirdparty.baidu.web.client.IDataProcessor;

/**
 * 为请求添加认证信息头
 *
 * @author weiba
 * @date 2024/3/7 16:48
 */
@Log4j2
public class IdentityInformationProcessor implements IDataProcessor<HttpRequest> {

    @Override
    public HttpRequest process(HttpRequest request) {
        String identityInformationId = (String) request.getBuildParams().get("identityInformationId");
        String identityInformationType = (String) request.getBuildParams().get("identityInformationType");
        String cookie = getCookie(identityInformationId, identityInformationType);
        request.addheader("Cookie", cookie);
        return request;
    }

    public String getCookie(String identityInformationId, String identityInformationType) {
        return null;
    }
}
