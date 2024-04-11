package pp.weiba.thirdparty.baidu.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/9/23
 **/

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaiduNetDiskWebQueryShareFileResponse {

    @JSONField(name = "errno")
    private Integer errno;
    @JSONField(name = "request_id")
    private Long requestId;
    @JSONField(name = "server_time")
    private Integer serverTime;
    @JSONField(name = "cfrom_id")
    private Integer cfromId;
    @JSONField(name = "hitrisk")
    private Integer hitrisk;
    @JSONField(name = "appeal_status")
    private Integer appealStatus;
    @JSONField(name = "is_zombie")
    private Integer isZombie;
    @JSONField(name = "vip_point")
    private Integer vipPoint;
    @JSONField(name = "vip_level")
    private Integer vipLevel;
    @JSONField(name = "svip10_id")
    private String svip10Id;
    @JSONField(name = "vip_type")
    private Integer vipType;
    @JSONField(name = "sharetype")
    private Integer sharetype;
    @JSONField(name = "view_visited")
    private Integer viewVisited;
    @JSONField(name = "view_limit")
    private Integer viewLimit;
    @JSONField(name = "expired_type")
    private Integer expiredType;
    @JSONField(name = "list")
    private List<ListDTO> list;

    @NoArgsConstructor
    @Data
    public static class ListDTO {
        @JSONField(name = "category")
        private Integer category;
        @JSONField(name = "fs_id")
        private String fsId;
        @JSONField(name = "isdir")
        private Integer isdir;
        @JSONField(name = "local_ctime")
        private Date localCtime;
        @JSONField(name = "local_mtime")
        private Date localMtime;
        @JSONField(name = "md5")
        private String md5;
        @JSONField(name = "path")
        private String path;
        @JSONField(name = "server_ctime")
        private Date serverCtime;
        @JSONField(name = "server_filename")
        private String serverFilename;
        @JSONField(name = "server_mtime")
        private Date serverMtime;
        @JSONField(name = "size")
        private Long size;
    }
}
