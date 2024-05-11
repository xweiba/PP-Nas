package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
* 资源盘间复制资源
*
* @author weiba
* @date 2024/5/10 17:54
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CopyToResourceResponse {


    /**
     * items
     */
    @JSONField(name = "items")
    private List<ItemsResponse> items;

    /**
     * ItemsResponse
     */
    @NoArgsConstructor
    @Data
    public static class ItemsResponse {
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
         * sourceDriveId
         */
        @JSONField(name = "source_drive_id")
        private String sourceDriveId;
        /**
         * sourceFileId
         */
        @JSONField(name = "source_file_id")
        private String sourceFileId;
        /**
         * status 201 同步， 202 异步，需要使用asyncTaskId确认是否执行完毕
         */
        @JSONField(name = "status")
        private Integer status;
        /**
         * asyncTaskId
         */
        @JSONField(name = "async_task_id")
        private String asyncTaskId;
    }
}
