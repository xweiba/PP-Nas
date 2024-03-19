package pp.weiba.thirdparty.baidu.web.api.netdisk.base;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.AbstractApiHttpClient;
import pp.weiba.framework.core.client.HttpResponse;
import pp.weiba.framework.core.client.IHttpClient;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.thirdparty.baidu.web.api.netdisk.UrlConstants;

import java.util.HashMap;

/**
 * 基础信息
 *
 * @author weiba
 * @date 2024/3/7 9:50
 */
@Log4j2
public class AuthenticationApiClient extends AbstractApiHttpClient {

    public AuthenticationApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 获取网盘容量信息
     *
     * @return 容量信息
     * @author weiba
     * @date 2024/3/7 14:57
     */
    public CapacityResponse getCapacity() {
        return execute(UrlConstants.GET_QUOTA, new TypeReference<CapacityResponse>() {
        });
    }

    /**
     * 获取页面默认模板变量
     *
     * @return 模板变量
     * @author weiba
     * @date 2024/3/7 14:57
     */
    public TemplateVariableResponse getTemplateVariable() {
        String pageTemplateVariableFields = "\"bdstoken\",\"token\",\"uk\",\"isdocuser\",\"servertime\"";
        return getPageTemplateVariable(pageTemplateVariableFields, new TypeReference<TemplateVariableResponse>() {
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
    public <T> T getPageTemplateVariable(String fields, TypeReference<T> typeReference) {
        return execute(UrlConstants.GET_TEMPLATE_VARIABLE, new HashMap<String, String>() {{
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
    public LoginStatusResponse getLoginStatus() {
        return execute(UrlConstants.GET_LOGIN_STATUS, new TypeReference<LoginStatusResponse>() {
        });
    }


    public void signOut(String authenticationId) {
        HttpResponse httpResponse = executeResponse(UrlConstants.GET_LOGOUT, new HashMap<String, String>() {{
            put("AcceptType", "html");
        }});
        if (httpResponse.getStatusCode() == 302) {
            log.info("退出登录成功，authenticationId:{}", authenticationId);
        }
    }

}
