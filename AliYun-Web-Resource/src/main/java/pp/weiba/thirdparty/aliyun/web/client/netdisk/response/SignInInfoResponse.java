package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 签到信息
 *
 * @author weiba
 * @date 2024/5/8 16:44
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignInInfoResponse {

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
         * isSignIn
         */
        @JSONField(name = "isSignIn")
        private Boolean isSignIn;
        /**
         * year
         */
        @JSONField(name = "year")
        private String year;
        /**
         * month
         */
        @JSONField(name = "month")
        private String month;
        /**
         * day
         */
        @JSONField(name = "day")
        private String day;
        /**
         * signInDay
         */
        @JSONField(name = "signInDay")
        private Integer signInDay;
        /**
         * blessing
         */
        @JSONField(name = "blessing")
        private String blessing;
        /**
         * subtitle
         */
        @JSONField(name = "subtitle")
        private String subtitle;
        /**
         * themeIcon
         */
        @JSONField(name = "themeIcon")
        private String themeIcon;
        /**
         * themeAction
         */
        @JSONField(name = "themeAction")
        private String themeAction;
        /**
         * theme
         */
        @JSONField(name = "theme")
        private String theme;
        /**
         * action
         */
        @JSONField(name = "action")
        private String action;
        /**
         * rewards
         */
        @JSONField(name = "rewards")
        private List<RewardsResponse> rewards;

        /**
         * RewardsResponse
         */
        @NoArgsConstructor
        @Data
        public static class RewardsResponse {
            /**
             * id
             */
            @JSONField(name = "id")
            private Object id;
            /**
             * name
             */
            @JSONField(name = "name")
            private String name;
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
            /**
             * nameIcon
             */
            @JSONField(name = "nameIcon")
            private String nameIcon;
            /**
             * type
             */
            @JSONField(name = "type")
            private String type;
            /**
             * actionText
             */
            @JSONField(name = "actionText")
            private Object actionText;
            /**
             * action
             */
            @JSONField(name = "action")
            private Object action;
            /**
             * status
             */
            @JSONField(name = "status")
            private String status;
            /**
             * remind
             */
            @JSONField(name = "remind")
            private String remind;
            /**
             * remindIcon
             */
            @JSONField(name = "remindIcon")
            private String remindIcon;
            /**
             * expire
             */
            @JSONField(name = "expire")
            private Object expire;
            /**
             * position
             */
            @JSONField(name = "position")
            private Integer position;
            /**
             * idempotent
             */
            @JSONField(name = "idempotent")
            private Object idempotent;
        }
    }
}
