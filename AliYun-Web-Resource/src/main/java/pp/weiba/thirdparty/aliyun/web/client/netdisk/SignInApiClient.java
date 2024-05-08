package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.thirdparty.aliyun.web.client.UrlConstants;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.SignInInfoResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.SignInRewardResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.SignInStatusResponse;

import java.util.HashMap;

/**
 * 签到接口
 *
 * @author weiba
 * @date 2024/5/8 16:32
 */
@Log4j2
public class SignInApiClient extends AbstractApiHttpClient {

    public SignInApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 获取签到状态
     *
     * @return 签到状态
     * @author weiba
     * @date 2024/5/8 16:36
     */
    public SignInStatusResponse getSignInStatusInfo() {
        return postExecute(UrlConstants.POST_SIGN_IN_STATUS_INFO_URL, new TypeReference<SignInStatusResponse>() {
        });
    }

    /**
     * 获取签到信息，获取其中的连续签到天数
     *
     * @return 签到信息
     * @author weiba
     * @date 2024/5/8 16:45
     */
    public SignInInfoResponse getSignInInfo() {
        // 必须传个对象，否则会报错
        return postSrtExecute(UrlConstants.POST_SIGN_IN_INFO_URL, new HashMap<>(), new TypeReference<SignInInfoResponse>() {
        });
    }

    /**
     * 签到
     *
     * @param signInDay 连续签到天数
     * @return 签到信息
     * @author weiba
     * @date 2024/5/8 16:47
     */
    public SignInRewardResponse signInReward(Integer signInDay) {
        return postSrtExecute(UrlConstants.POST_SIGN_IN_REWARD_URL, new HashMap<String, Object>() {{
            put("signInDay", signInDay);
        }}, new TypeReference<SignInRewardResponse>() {
        });
    }

}
