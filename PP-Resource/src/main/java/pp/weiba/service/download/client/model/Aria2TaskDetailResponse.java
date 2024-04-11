package pp.weiba.service.download.client.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author xiaoweiba1028@gmail.com
 * @description 查询任务详情
 * @date 2023/6/1 0:26
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
@SuperBuilder
public class Aria2TaskDetailResponse {

    private Long completedLength;
    private String connections;
    private String dir;
    private String downloadSpeed;
    private List<Aria2QueryStatusResponse.FilesResponse> files;
    private String gid;
    private String numPieces;
    private String pieceLength;
    private String status;
    private Long totalLength;
    private String uploadLength;
    private String uploadSpeed;

}
