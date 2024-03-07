package pp.weiba.thirdparty.baidu.web.netdisk.processors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.client.HttpRequest;
import pp.weiba.thirdparty.baidu.web.client.IDataProcessor;
import pp.weiba.thirdparty.baidu.web.netdisk.utils.BaiduWebApiUtils;

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

            String identityInformationId = (String) request.getBuildParams().get("identityInformationId");
            String identityInformationType = (String) request.getBuildParams().get("identityInformationType");
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

        return null;
    }

    private String replacePlaceholder(String str, String regex, String replacement) {
        String replacePlaceholder = regex + "={}";
        return str.replaceAll(replacePlaceholder, regex + "=" + replacement);
    }

    private <T, F> Map<T, F> initMap(Map<T, F> formatMap) {
        if (formatMap == null) {
            formatMap = new HashMap<>();
        }
        return formatMap;
    }
}
