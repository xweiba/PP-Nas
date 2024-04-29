package pp.weiba.thirdparty.aliyun.web.client.processors;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.IProcessor;
import pp.weiba.framework.net.client.IHttpClientAuthentication;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.security.authentication.AuthenticationManager;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.NetDiskAuthentication;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求Url参数补全处理器
 *
 * @author weiba
 * @date 2024/3/7 15:26
 */
@Log4j2
public class UrlParameterCompletionProcessor implements IProcessor<HttpRequest> {

    private final IHttpClientAuthentication httpClientAuthentication;

    public UrlParameterCompletionProcessor(IHttpClientAuthentication httpClientAuthentication) {
        this.httpClientAuthentication = httpClientAuthentication;
    }

    @Override
    public HttpRequest process(HttpRequest request) {
        String url = request.getHeaderMap().get("Authorization");


        return request;
    }

    private NetDiskAuthentication getNetDiskAuthentication() {
        NetDiskAuthentication netDiskAuthentication = AuthenticationManager.getAuthentication(httpClientAuthentication.getAuthenticationId(), httpClientAuthentication.getAuthenticationType());
        if (netDiskAuthentication == null) {
            throw new RuntimeException("认证失败，请先登录");
        }
        return netDiskAuthentication;
    }

    private <T, F> Map<T, F> initMap(Map<T, F> formatMap) {
        if (formatMap == null) {
            formatMap = new HashMap<>();
        }
        return formatMap;
    }
}
