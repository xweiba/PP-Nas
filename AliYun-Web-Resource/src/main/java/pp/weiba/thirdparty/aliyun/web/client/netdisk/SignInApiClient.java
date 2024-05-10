package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.thirdparty.aliyun.web.client.UrlConstants;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.SignInInfoListResponse;
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
     * 今日签到
     *
     * @return 签到状态
     * @author weiba
     * @date 2024/5/8 16:36
     */
    public SignInStatusResponse todaySignIn() {
        return postExecute(UrlConstants.POST_SIGN_IN_INFO_URL, new TypeReference<SignInStatusResponse>() {
        });
    }



    /**
     * 获取今日签到状态信息
     *
     * @return 签到信息
     * @author weiba
     * @date 2024/5/8 16:45
     */
    public SignInInfoResponse getTodaySignInStatusInfo() {
        // 必须传个对象，否则会报错
        return postSrtExecute(UrlConstants.POST_SIGN_IN_STATUS_INFO_URL, new HashMap<>(), new TypeReference<SignInInfoResponse>() {
        });
    }

    /**
     * 获取签到状态列表
     *
     * @return 签到奖励列表
     * @author weiba
     * @date 2024/5/8 16:38
     */
    public SignInInfoListResponse getSignInInfoList() {
        return postSrtExecute(UrlConstants.POST_SIGN_IN_LIST_STATUS_INFO_URL, new HashMap<>(), new TypeReference<SignInInfoListResponse>() {
        });
    }

    /**
     * 领取奖励
     *
     * @param signInDay 连续签到天数
     * @return 领取奖励信息
     * @author weiba
     * @date 2024/5/8 16:47
     */
    public SignInRewardResponse signInReward(Integer signInDay) {
        return postSrtExecute(UrlConstants.POST_SIGN_IN_REWARD_URL, new HashMap<String, Object>() {{
            put("signInDay", signInDay);
        }}, new TypeReference<SignInRewardResponse>() {
        });
    }

    /**
     * 完成今日签到及领取奖励
     *
     * @return
     * @author weiba
     * @date 2024/5/10 15:58
     */
    public Boolean todaySignInAndReward() {
        SignInStatusResponse signInStatusResponse = todaySignIn();
        if (!signInStatusResponse.getResult().getIsReward()) {
            SignInInfoResponse todaySignInStatusInfo = getTodaySignInStatusInfo();
            signInReward(todaySignInStatusInfo.getResult().getSignInDay());
        }
        return Boolean.TRUE;
    }


    public boolean signInRewardAll() {
        SignInInfoListResponse signInInfos = getSignInInfoList();
        for (SignInInfoListResponse.ResultResponse.SignInInfosResponse inInfo : signInInfos.getResult().getSignInInfos()) {
            if (inInfo.getStatus().equals("normal")) {
                for (SignInInfoListResponse.ResultResponse.SignInInfosResponse.RewardsResponse reward : inInfo.getRewards()) {
                    if (reward.getType().equals("dailySignIn") && reward.getStatus().equals("finished")) {
                        SignInRewardResponse signInReward = signInReward(Integer.valueOf(inInfo.getDay()));
                    }
                }
            }
        }
        return true;
    }

}
