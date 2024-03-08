package pp.weiba.thirdparty.baidu.web.api.netdisk.processors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.api.client.HttpRequest;
import pp.weiba.thirdparty.baidu.web.api.client.IDataProcessor;
import pp.weiba.thirdparty.baidu.web.api.netdisk.utils.BaiduWebApiUtils;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.Authentication;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.AuthenticationManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求Url参数补全处理器
 *
 * @author weiba
 * @date 2024/3/7 15:26
 */
@Log4j2
public class UrlParameterCompletionProcessor implements IDataProcessor<HttpRequest> {

    @Override
    public HttpRequest process(HttpRequest request) {
        String url = request.getUrl();

        Map<String, String> formatMap = null;

        if (url.contains("{dp-logid}")) {
            formatMap = initMap(formatMap);

            String dpLogId = BaiduWebApiUtils.getDpLogId();

            formatMap.put("dp-logid", dpLogId);
        }
        if (url.contains("{bdstoken}")) {
            formatMap = initMap(formatMap);

            String identityInformationId = (String) request.getBuildParams().get("authenticationId");
            String identityInformationType = (String) request.getBuildParams().get("authenticationType");
            String bdstoken = getBDStoken(identityInformationId, identityInformationType);

            formatMap.put("bdstoken", bdstoken);
        }

        if (CollUtil.isNotEmpty(formatMap)) {
            url = StrUtil.format(url, formatMap);
            request.setUrl(url);
        }
        return request;
    }

    private String getBDStoken(String identityInformationId, String identityInformationType) {
        Authentication authentication = AuthenticationManager.getAuthentication(identityInformationId, identityInformationType);
        if (authentication == null || authentication.getTemplateVariable() == null || StrUtil.isBlank(authentication.getTemplateVariable().getBdstoken())) {
            return null;
        }
        return authentication.getTemplateVariable().getBdstoken();
    }

    private <T, F> Map<T, F> initMap(Map<T, F> formatMap) {
        if (formatMap == null) {
            formatMap = new HashMap<>();
        }
        return formatMap;
    }
}
