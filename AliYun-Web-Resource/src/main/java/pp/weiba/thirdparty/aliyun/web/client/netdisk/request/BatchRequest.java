package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.framework.core.convert.TypeReference;

import java.util.ArrayList;
import java.util.List;

/*
删除
 {"requests":[{"body":{"drive_id":"18654654","file_id":"6341189d4fa988adf1dc4bacbc5f04326ef4e3d1"},"headers":{"Content-Type":"application/json"},"id":"6341189d4fa988adf1dc4bacbc5f04326ef4e3d1","method":"POST","url":"/recyclebin/trash"}],"resource":"file"}
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

    public BatchRequest(BatchOperationRequest ...operations) {
        for (BatchOperationRequest operation : operations) {
            Object bodyRequest = operation.getBodyRequest();
            if (operation.getBodyRequest() == null) {
                bodyRequest = new RequestsRequest.BodyRequest()
                        .setDriveId(operation.getDriveId()).setFileId(operation.getFileId())
                        .setToDriveId(operation.getToDriveId()).setToParentFileId(operation.getToParentFileId());
            }
            RequestsRequest requestsResponse = new RequestsRequest()
                    .setUrl(operation.getUrl())
                    .setMethod(operation.getMethod())
                    .setId(operation.getId())
                    .setBody(bodyRequest);
            requestsResponse.getHeaders().setContentType(operation.getContentType());
            requests.add(requestsResponse);
        }
    }

    /**
     * requests
     */
    @JSONField(name = "requests")
    private List<RequestsRequest> requests = new ArrayList<>();

    /**
     * resource
     */
    @JSONField(name = "resource")
    private String resourceType = "file";

    /**
     * RequestsResponse
     */
    @NoArgsConstructor
    @Data
    public static class RequestsRequest {
        /**
         * body
         */
        @JSONField(name = "body")
        private Object body;
        /**
         * headers
         */
        @JSONField(name = "headers")
        private HeadersResponse headers = new HeadersResponse();
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
        @Accessors(chain = true)
        public static class BodyRequest {
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
            /**
             * driveId
             */
            @JSONField(name = "to_drive_id")
            private String toDriveId;
            /**
             * fileId
             */
            @JSONField(name = "to_parent_file_id")
            private String toParentFileId;


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
