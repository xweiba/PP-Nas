package pp.weiba.thirdparty.baidu.web.netdisk.base;

import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.client.HttpRequest;
import pp.weiba.thirdparty.baidu.web.client.IHttpClient;
import pp.weiba.thirdparty.baidu.web.client.TypeReference;
import pp.weiba.thirdparty.baidu.web.netdisk.AbstractBaiduNetDiskApiClient;
import pp.weiba.thirdparty.baidu.web.netdisk.UrlConstants;

import java.util.HashMap;

/**
 * 基础信息
 *
 * @author weiba
 * @date 2024/3/7 9:50
 */
@Log4j2
public class BaseApiClient extends AbstractBaiduNetDiskApiClient {

    private BaseApiClient(IHttpClient httpClient) {
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
        return httpClient.execute(HttpRequest.urlFormatBuilder(UrlConstants.GET_QUOTA), new TypeReference<CapacityResponse>() {
        });
    }

    /**
     * 获取页面默认模板变量
     *
     * @return 模板变量
     * @author weiba
     * @date 2024/3/7 14:57
     */
    protected TemplateVariableResponse getTemplateVariable() {
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
        return this.httpClient.execute(HttpRequest.urlFormatBuilder(UrlConstants.GET_TEMPLATE_VARIABLE, new HashMap<String, String>() {{
            put("fields", fields);
        }}), typeReference);
    }

    /**
     * 获取用户登录状态
     *
     * @return 用户登录状态信息
     * @author weiba
     * @date 2024/3/7 17:39
     */
    public LoginStatusResponse getLoginStatus() {
        LoginStatusResponse loginStatus = this.httpClient.execute(HttpRequest.urlFormatBuilder(UrlConstants.GET_LOGIN_STATUS), new TypeReference<LoginStatusResponse>() {
        });
        LoginStatusResponse.LoginInfo loginInfoTemp = loginStatus == null || loginStatus.getLoginInfo() == null ? null : loginStatus.getLoginInfo();
        if (loginInfoTemp == null) {
            throw new RuntimeException("login 认证失败！");
        }
        return loginStatus;
    }
}
