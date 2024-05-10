package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
* 签到列表
*
* @author weiba
* @date 2024/5/9 9:48
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignInInfoListResponse {

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
         * month
         */
        @JSONField(name = "month")
        private String month;
        /**
         * signInCount
         */
        @JSONField(name = "signInCount")
        private Integer signInCount;
        /**
         * themeIcon
         */
        @JSONField(name = "themeIcon")
        private String themeIcon;
        /**
         * signInInfos
         */
        @JSONField(name = "signInInfos")
        private List<SignInInfosResponse> signInInfos;

        /**
         * SignInInfosResponse
         */
        @NoArgsConstructor
        @Data
        public static class SignInInfosResponse {
            /**
             * day
             */
            @JSONField(name = "day")
            private String day;
            /**
             * date
             */
            @JSONField(name = "date")
            private String date;
            /**
             * blessing
             */
            @JSONField(name = "blessing")
            private String blessing;
            /**
             * status
             */
            @JSONField(name = "status")
            private String status;
            /**
             * subtitle
             */
            @JSONField(name = "subtitle")
            private String subtitle;
            /**
             * theme
             */
            @JSONField(name = "theme")
            private String theme;
            /**
             * icon
             */
            @JSONField(name = "icon")
            private Object icon;
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
                private Object nameIcon;
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
                private Object remindIcon;
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
}
