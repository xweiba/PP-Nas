package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * {"success":true,"code":null,"message":null,"totalCount":null,"nextToken":null,"maxResults":null,"result":{"goodsId":223,"name":"备份盘专享容量","description":"","background":"https://gw.alicdn.com/imgextra/i4/O1CN01eMBx3u1JUhdEYZ7qL_!!6000000001032-2-tps-576-414.png","color":"blue","action":"smartdrive://webview?url=https%3A%2F%2Fpages.aliyundrive.com%2Fmobile-page%2Fcapacitymanager.html%3FdisableNav%3DYES","detailAction":null,"notice":"已获得备份盘专享容量 50MB","subNotice":"容量90天有效","bottleId":null,"bottleName":null,"bottleShareId":null},"arguments":null}
 */

/**
 * 签到信息
 *
 * @author weiba
 * @date 2024/5/8 17:22
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignInRewardResponse {

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
         * goodsId
         */
        @JSONField(name = "goodsId")
        private Integer goodsId;
        /**
         * name
         */
        @JSONField(name = "name")
        private String name;
        /**
         * description
         */
        @JSONField(name = "description")
        private String description;
        /**
         * background
         */
        @JSONField(name = "background")
        private String background;
        /**
         * color
         */
        @JSONField(name = "color")
        private String color;
        /**
         * action
         */
        @JSONField(name = "action")
        private String action;
        /**
         * detailAction
         */
        @JSONField(name = "detailAction")
        private Object detailAction;
        /**
         * notice
         */
        @JSONField(name = "notice")
        private String notice;
        /**
         * subNotice
         */
        @JSONField(name = "subNotice")
        private String subNotice;
        /**
         * bottleId
         */
        @JSONField(name = "bottleId")
        private Object bottleId;
        /**
         * bottleName
         */
        @JSONField(name = "bottleName")
        private Object bottleName;
        /**
         * bottleShareId
         */
        @JSONField(name = "bottleShareId")
        private Object bottleShareId;
    }
}
