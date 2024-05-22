package pp.weiba.thirdparty.aliyun.web.client.netdisk.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.aliyun.web.client.AliYunClientConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* 创建分享
*
* @author weiba
* @date 2024/5/15 16:43
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateShareRequest {

    /**
     * expiration， 过期时间， 留空为永久有效
     */
    @JSONField(name = "expiration", format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date expiration;
    /**
     * sharePwd 分享密码
     */
    @JSONField(name = "share_pwd")
    private String sharePwd;
    /**
     * driveId 资源盘id
     */
    @JSONField(name = "drive_id")
    private String driveId = AliYunClientConstants.REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG;
    /**
     * fileIdList
     */
    @JSONField(name = "file_id_list")
    private List<String> fileIdList = new ArrayList<>();

    @JSONField(format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    public Date getExpiration() {
        return expiration;
    }
}
