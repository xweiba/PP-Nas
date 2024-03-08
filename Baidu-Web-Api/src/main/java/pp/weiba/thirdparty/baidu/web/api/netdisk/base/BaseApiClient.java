package pp.weiba.thirdparty.baidu.web.api.netdisk.base;

import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.api.client.AbstractApiHttpClient;
import pp.weiba.thirdparty.baidu.web.api.client.IHttpClient;
import pp.weiba.thirdparty.baidu.web.api.client.TypeReference;
import pp.weiba.thirdparty.baidu.web.api.netdisk.UrlConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础信息
 *
 * @author weiba
 * @date 2024/3/7 9:50
 */
@Log4j2
public class BaseApiClient extends AbstractApiHttpClient {

    public BaseApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 获取网盘容量信息
     *
     * @return 容量信息
     * @author weiba
     * @date 2024/3/7 14:57
     */
    public CapacityResponse getCapacity(Map<String, Object> buildParams) {
        return execute(UrlConstants.GET_QUOTA, buildParams, new TypeReference<CapacityResponse>() {
        });
    }

    /**
     * 获取页面默认模板变量
     *
     * @return 模板变量
     * @author weiba
     * @date 2024/3/7 14:57
     */
    public TemplateVariableResponse getTemplateVariable(Map<String, Object> buildParams) {
        String pageTemplateVariableFields = "\"bdstoken\",\"token\",\"uk\",\"isdocuser\",\"servertime\"";
        return getPageTemplateVariable(buildParams, pageTemplateVariableFields, new TypeReference<TemplateVariableResponse>() {
        });
    }

    /**
     * 获取页面模板变量
     *
     * @param fields 模板变量
     * @return 模板变量
     * @author weiba
     * @date 2024/3/7 14:57
     */
    public <T> T getPageTemplateVariable(Map<String, Object> buildParams, String fields, TypeReference<T> typeReference) {
        return execute(UrlConstants.GET_TEMPLATE_VARIABLE, buildParams, new HashMap<String, String>() {{
            put("fields", fields);
        }}, typeReference);
    }

    /**
     * 获取用户登录状态
     *
     * @return 用户登录状态信息
     * @author weiba
     * @date 2024/3/7 17:39
     */
    public LoginStatusResponse getLoginStatus(Map<String, Object> buildParams) {
        return execute(UrlConstants.GET_LOGIN_STATUS, buildParams, new TypeReference<LoginStatusResponse>() {
        });
    }
}
