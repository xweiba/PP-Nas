package pp.weiba.thirdparty.aliyun.web.client.authentication;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.net.client.model.HttpResponse;
import pp.weiba.thirdparty.aliyun.web.client.UrlConstants;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.RefreshTokenResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.CreateSessionRequest;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.SBoxInfo;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.UserInfo;

/**
 * 认证信息接口
 *
 * @author weiba
 * @date 2024/4/29 17:29
 */
@Log4j2
public class AuthenticationApiClient extends AbstractApiHttpClient {

    public AuthenticationApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    public boolean createSession(String pubkey) {
        HttpResponse httpResponse = postStrExecuteResponse(UrlConstants.POST_CREATE_SESSION_URL, new CreateSessionRequest(pubkey));
        return httpResponse.getStatusCode() == 200;
    }

    public RefreshTokenResponse refreshToken() {
        return postExecute(UrlConstants.POST_TOKEN_REFRESH_URL, new TypeReference<RefreshTokenResponse>() {
        });
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     * @author weiba
     * @date 2024/4/30 14:05
     */
    public UserInfo getUserInfo() {
        return postExecute(UrlConstants.POST_GET_USER_INFO_URL, new TypeReference<UserInfo>() {
        });
    }

    /**
     * 获取环境信息
     *
     * @return 环境信息
     * @author weiba
     * @date 2024/4/30 16:32
     */
    public SBoxInfo getSBoxInfo() {
        return postExecute(UrlConstants.POST_GET_SBOX_INFO_URL, new TypeReference<SBoxInfo>() {
        });
    }

    public void signOut(String authenticationId) {

    }
}
