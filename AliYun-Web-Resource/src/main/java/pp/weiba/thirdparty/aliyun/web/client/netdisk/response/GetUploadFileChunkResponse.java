package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.CreateWithFoldersRequest;

import java.util.List;

/**
* 
*
* @author weiba
* @date 2024/5/22 14:53
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetUploadFileChunkResponse {

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
    /**
     * partInfoList
     */
    @JSONField(name = "part_info_list")
    private List<CreateWithFoldersResponse.PartInfoListResponse> partInfoList;
    /**
     * uploadId
     */
    @JSONField(name = "upload_id")
    private String uploadId;
    /**
     * createAt
     */
    @JSONField(name = "create_at")
    private String createAt;
}
