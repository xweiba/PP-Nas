package pp.weiba.service.download.client.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author xiaoweiba1028@gmail.com
 * @description 已停止/已完成任务信息
 * @date 2023/6/1 0:53
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
@SuperBuilder
public class Aria2TellStoppedResponse {

    private String completedLength;
    private String connections;
    private String downloadSpeed;
    private String errorCode;
    private List<Aria2QueryStatusResponse.FilesResponse> files;
    private String gid;
    private String status;
    private String totalLength;
    private String uploadSpeed;

}
