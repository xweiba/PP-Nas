package pp.weiba.thirdparty.aliyun.web.client.processors;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.IProcessor;
import pp.weiba.framework.net.client.IHttpClientAuthentication;
import pp.weiba.framework.net.client.model.HttpRequest;
import pp.weiba.framework.security.authentication.AuthenticationManager;
import pp.weiba.thirdparty.aliyun.web.client.ClientContants;
import pp.weiba.thirdparty.aliyun.web.client.authentication.NetDiskAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.TokenResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.UserInfo;
import pp.weiba.utils.StringUtils;

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
        NetDiskAuthentication netDiskAuthentication = getNetDiskAuthentication();
        if (netDiskAuthentication != null && netDiskAuthentication.getUserInfo() != null) {
            UserInfo userInfo = netDiskAuthentication.getUserInfo();
            String backupDriveId = userInfo.getBackupDriveId();
            String resourceDriveId = userInfo.getResourceDriveId();
            String userId = userInfo.getUserId();
            if (userInfo != null && (StrUtil.isNotBlank(backupDriveId) || StrUtil.isNotBlank(resourceDriveId)) || StrUtil.isNotBlank(userId)) {
                if (CollUtil.isNotEmpty(params)) {
                    for (Map.Entry<String, Object> item : params.entrySet()) {
                        Object value = item.getValue();
                        if (value instanceof String) {
                            if (StrUtil.isNotBlank(backupDriveId) && value.equals(ClientContants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG)) {
                                item.setValue(backupDriveId);
                            }
                            if (StrUtil.isNotBlank(resourceDriveId) && value.equals(ClientContants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG)) {
                                item.setValue(resourceDriveId);
                            }
                            if (StrUtil.isNotBlank(userId) && value.equals(ClientContants.REQUEST_PARAM_RESOURCE_USER_ID_TAG)) {
                                item.setValue(userId);
                            }
                        }
                    }
                }
                String requestBody = request.getRequestBody();
                if (StrUtil.isNotBlank(requestBody)) {
                    if (StrUtil.isNotBlank(backupDriveId) && requestBody.contains(ClientContants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG)) {
                        String format = StringUtils.formatWithByOneValue(requestBody, ClientContants.REQUEST_PARAM_BACKUP_DRIVE_ID_TAG, backupDriveId);
                        if (StrUtil.isNotBlank(format)) {
                            requestBody = format;
                        }
                    }
                    if (StrUtil.isNotBlank(resourceDriveId) && requestBody.contains(ClientContants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG)) {
                        String format = StringUtils.formatWithByOneValue(requestBody, ClientContants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG, resourceDriveId);
                        if (StrUtil.isNotBlank(format)) {
                            requestBody = format;
                        }
                    }
                    if (StrUtil.isNotBlank(userId) && requestBody.contains(ClientContants.REQUEST_PARAM_RESOURCE_USER_ID_TAG)) {
                        String format = StringUtils.formatWithByOneValue(requestBody, ClientContants.REQUEST_PARAM_RESOURCE_USER_ID_TAG, userId);
                        if (StrUtil.isNotBlank(format)) {
                            requestBody = format;
                        }
                    }

                    if (StrUtil.isNotBlank(requestBody)) {
                        request.setRequestBody(requestBody);
                    }
                }
            }

        }

        return request;
    }


    private NetDiskAuthentication getNetDiskAuthentication() {
        NetDiskAuthentication netDiskAuthentication = AuthenticationManager.getAuthentication(httpClientAuthentication.getAuthenticationId(), httpClientAuthentication.getAuthenticationType());
        if (netDiskAuthentication == null || netDiskAuthentication.getToken() == null || StrUtil.isBlank(netDiskAuthentication.getToken().getAccessToken())) {
            return null;
        }
        return netDiskAuthentication;
    }

}
