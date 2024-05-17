package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/*
删除：
{"responses":[{"id":"663db23c8caba03781a540b9b0848e1434db38be","status":204}]}
* */

/**
* 批处理响应
*
* @author weiba
* @date 2024/5/10 13:41
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BatchResponse {


    /**
     * responses
     */
    @JSONField(name = "responses")
    private List<infoResponses> responses;

    /**
     * ResponsesResponse
     */
    @NoArgsConstructor
    @Data
    public static class infoResponses {
        /**
         * body
         */
        @JSONField(name = "body")
        private BodyResponse body;
        /**
         * id
         */
        @JSONField(name = "id")
        private String id;
        /**
         * status
         */
        @JSONField(name = "status")
        private Integer status;

        /**
         * BodyResponse
         */
        @NoArgsConstructor
        @Data
        public static class BodyResponse {
            /**
             * failedProcess
             */
            @JSONField(name = "failed_process")
            private Integer failedProcess;
            /**
             * totalProcess
             */
            @JSONField(name = "total_process")
            private Integer totalProcess;
            /**
             * downloadUrl
             */
            @JSONField(name = "download_url")
            private String downloadUrl;
            /**
             * skippedProcess
             */
            @JSONField(name = "skipped_process")
            private Integer skippedProcess;
            /**
             * state
             */
            @JSONField(name = "state")
            private String state;
            /**
             * message
             */
            @JSONField(name = "message")
            private String message;
            /**
             * asyncTaskId
             */
            @JSONField(name = "async_task_id")
            private String asyncTaskId;
            /**
             * consumedProcess
             */
            @JSONField(name = "consumed_process")
            private Integer consumedProcess;
            /**
             * status
             */
            @JSONField(name = "status")
            private String status;

            /**
             * domainId
             */
            @JSONField(name = "domain_id")
            private String domainId;
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
    }
}
