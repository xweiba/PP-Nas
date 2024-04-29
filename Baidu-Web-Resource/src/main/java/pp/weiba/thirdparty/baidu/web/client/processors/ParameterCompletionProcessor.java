package pp.weiba.thirdparty.baidu.web.client.processors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.IProcessor;
import pp.weiba.framework.net.client.IHttpClientAuthentication;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.security.authentication.AuthenticationManager;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.NetDiskAuthentication;

import java.util.Map;

/**
 * 请求Url参数补全处理器
 *
 * @author weiba
 * @date 2024/3/7 15:26
 */
@Log4j2
public class ParameterCompletionProcessor implements IProcessor<HttpRequest> {

    private final IHttpClientAuthentication httpClientAuthentication;

    public ParameterCompletionProcessor(IHttpClientAuthentication httpClientAuthentication) {
        this.httpClientAuthentication = httpClientAuthentication;
    }

    @Override
    public HttpRequest process(HttpRequest request) {
        Map<String, Object> params = request.getRequestParams();

        if (CollUtil.isNotEmpty(params)) {
            for (Map.Entry<String, Object> item : params.entrySet()) {
                Object value = item.getValue();
                if (value instanceof String) {
                    if (value.equals("{STOKEN}")) {
                        item.setValue(getStoken());
                    } else if (value.equals("{BDSTOKEN}")) {
                        item.setValue(getBDStoken());
                    }
                }
            }
        }
        return request;
    }

    private String getStoken() {
        NetDiskAuthentication netDiskAuthentication = AuthenticationManager.getAuthentication(httpClientAuthentication.getAuthenticationId(), httpClientAuthentication.getAuthenticationType());
        if (netDiskAuthentication == null || !netDiskAuthentication.containsCookie("STOKEN")) {
            throw new RuntimeException("stoken 获取失败，请检查配置");
        }
        return netDiskAuthentication.getCookieValue("STOKEN");
    }

    private String getBDStoken() {
        NetDiskAuthentication netDiskAuthentication = AuthenticationManager.getAuthentication(httpClientAuthentication.getAuthenticationId(), httpClientAuthentication.getAuthenticationType());
        if (netDiskAuthentication == null || netDiskAuthentication.getTemplateVariable() == null || StrUtil.isBlank(netDiskAuthentication.getTemplateVariable().getBdstoken())) {
            throw new RuntimeException("认证失败，请先登录");
        }
        return netDiskAuthentication.getTemplateVariable().getBdstoken();
    }

}
