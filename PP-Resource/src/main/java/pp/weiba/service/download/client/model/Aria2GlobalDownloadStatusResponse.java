package pp.weiba.service.download.client.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2023/7/3 9:37
 */
@NoArgsConstructor
@Data
public class Aria2GlobalDownloadStatusResponse {

    @JSONField(name = "downloadSpeed")
    private Integer downloadSpeed;
    @JSONField(name = "numActive")
    private Integer numActive;
    @JSONField(name = "numStopped")
    private Integer numStopped;
    @JSONField(name = "numStoppedTotal")
    private Integer numStoppedTotal;
    @JSONField(name = "numWaiting")
    private Integer numWaiting;
    @JSONField(name = "uploadSpeed")
    private Integer uploadSpeed;

}
