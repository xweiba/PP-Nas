package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 签到信息
 *
 * @author weiba
 * @date 2024/5/8 16:33
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignInStatusResponse {

    /**
     * success
     */
    @JSONField(name = "success")
    private Boolean success;
    /**
     * code
     */
    @JSONField(name = "code")
    private Object code;
    /**
     * message
     */
    @JSONField(name = "message")
    private Object message;
    /**
     * totalCount
     */
    @JSONField(name = "totalCount")
    private Object totalCount;
    /**
     * nextToken
     */
    @JSONField(name = "nextToken")
    private Object nextToken;
    /**
     * maxResults
     */
    @JSONField(name = "maxResults")
    private Object maxResults;
    /**
     * result
     */
    @JSONField(name = "result")
    private ResultResponse result;
    /**
     * arguments
     */
    @JSONField(name = "arguments")
    private Object arguments;

    /**
     * ResultResponse
     */
    @NoArgsConstructor
    @Data
    public static class ResultResponse {
        /**
         * monthSubject
         */
        @JSONField(name = "monthSubject")
        private String monthSubject;
        /**
         * isSignIn
         */
        @JSONField(name = "isSignIn")
        private Boolean isSignIn;
        /**
         * isReward
         */
        @JSONField(name = "isReward")
        private Boolean isReward;
        /**
         * goodsName
         */
        @JSONField(name = "goodsName")
        private String goodsName;
        /**
         * goodsId
         */
        @JSONField(name = "goodsId")
        private Integer goodsId;
        /**
         * goodsType
         */
        @JSONField(name = "goodsType")
        private String goodsType;
        /**
         * goodsDescription
         */
        @JSONField(name = "goodsDescription")
        private String goodsDescription;
        /**
         * saveShareBackground
         */
        @JSONField(name = "saveShareBackground")
        private String saveShareBackground;
        /**
         * webAndPcSignInBackground
         */
        @JSONField(name = "webAndPcSignInBackground")
        private String webAndPcSignInBackground;
        /**
         * webAndPcSignInBackgroundV2
         */
        @JSONField(name = "webAndPcSignInBackgroundV2")
        private String webAndPcSignInBackgroundV2;
        /**
         * rewardName
         */
        @JSONField(name = "rewardName")
        private String rewardName;
        /**
         * rewardImage
         */
        @JSONField(name = "rewardImage")
        private String rewardImage;
        /**
         * rewardDesc
         */
        @JSONField(name = "rewardDesc")
        private String rewardDesc;
    }
}
