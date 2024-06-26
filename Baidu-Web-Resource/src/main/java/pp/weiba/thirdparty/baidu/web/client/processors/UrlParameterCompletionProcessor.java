package pp.weiba.thirdparty.baidu.web.client.processors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.IProcessor;
import pp.weiba.framework.net.client.IHttpClientAuthentication;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.security.authentication.AuthenticationManager;
import pp.weiba.thirdparty.baidu.web.client.netdisk.utils.BaiduNetDiskWebScript;
import pp.weiba.thirdparty.baidu.web.client.netdisk.utils.BaiduWebApiUtils;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.NetDiskAuthentication;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.response.AccessToken;

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
        String url = request.getUrl();

        Map<String, String> formatMap = null;

        if (url.contains("{dp-logid}")) {
            formatMap = initMap(formatMap);
            String dpLogId = BaiduWebApiUtils.getDpLogId();

            formatMap.put("dp-logid", dpLogId);
        }

        if (url.contains("{logid}")) {
            formatMap = initMap(formatMap);
            String logid = BaiduNetDiskWebScript.getLogId();

            formatMap.put("logid", logid);
        }
        if (url.contains("{bdstoken}")) {
            formatMap = initMap(formatMap);
            String bdstoken = getBDStoken();

            formatMap.put("bdstoken", bdstoken);
        }
        if (url.contains("{uk}")) {
            formatMap = initMap(formatMap);
            Integer uk = getUk();

            formatMap.put("uk", String.valueOf(uk));
        }
        if (url.contains("{xpan_access_token}")) {
            formatMap = initMap(formatMap);
            String xpan_access_token = getAccessToken();

            formatMap.put("xpan_access_token", xpan_access_token);
        }

        if (CollUtil.isNotEmpty(formatMap)) {
            url = StrUtil.format(url, formatMap);
            request.setUrl(url);
        }
        return request;
    }

    private String getBDStoken() {
        NetDiskAuthentication netDiskAuthentication = getNetDiskAuthentication();
        if (netDiskAuthentication.getTemplateVariable() == null || StrUtil.isBlank(netDiskAuthentication.getTemplateVariable().getBdstoken())) {
            throw new RuntimeException("认证失败，请先登录");
        }
        return netDiskAuthentication.getTemplateVariable().getBdstoken();
    }

    private Integer getUk() {
        NetDiskAuthentication netDiskAuthentication = getNetDiskAuthentication();
        if (netDiskAuthentication.getLoginInfo() == null || netDiskAuthentication.getLoginInfo().getUk() == null) {
            throw new RuntimeException("认证失败，请先登录");
        }
        return netDiskAuthentication.getLoginInfo().getUk();
    }

    private String getAccessToken() {
        NetDiskAuthentication netDiskAuthentication = getNetDiskAuthentication();
        AccessToken accessToken = netDiskAuthentication.getOpenApiAccessToken();
        if (accessToken == null || StrUtil.isBlank(accessToken.getAccessToken())) {
            throw new RuntimeException("认证失败，请先登录");
        }
        return accessToken.getAccessToken();
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
