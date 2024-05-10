package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/*
* {"requests":[{"body":{"drive_id":"13298650","file_id":"6341189d4fa988adf1dc4bacbc5f04326ef4e3d1"},"headers":{"Content-Type":"application/json"},"id":"6341189d4fa988adf1dc4bacbc5f04326ef4e3d1","method":"POST","url":"/recyclebin/trash"}],"resource":"file"}
* */

/**
* 批处理请求参数
*
* @author weiba
* @date 2024/5/10 9:23
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BatchRequest {

    /**
     * requests
     */
    @JSONField(name = "requests")
    private List<RequestsResponse> requests;
    /**
     * resource
     */
    @JSONField(name = "resource")
    private String resource;

    /**
     * RequestsResponse
     */
    @NoArgsConstructor
    @Data
    public static class RequestsResponse {
        /**
         * body
         */
        @JSONField(name = "body")
        private BodyResponse body;
        /**
         * headers
         */
        @JSONField(name = "headers")
        private HeadersResponse headers;
        /**
         * id
         */
        @JSONField(name = "id")
        private String id;
        /**
         * method
         */
        @JSONField(name = "method")
        private String method;
        /**
         * url
         */
        @JSONField(name = "url")
        private String url;

        /**
         * BodyResponse
         */
        @NoArgsConstructor
        @Data
        public static class BodyResponse {
            /**
             * driveId
             */
            @JSONField(name = "drive_id")
            private String driveId;
            /**
             * fileId
             */
            @JSONField(name = "file_id")
            private String fileId;
        }

        /**
         * HeadersResponse
         */
        @NoArgsConstructor
        @Data
        public static class HeadersResponse {
            /**
             * contentType
             */
            @JSONField(name = "Content-Type")
            private String contentType;
        }
    }
}
