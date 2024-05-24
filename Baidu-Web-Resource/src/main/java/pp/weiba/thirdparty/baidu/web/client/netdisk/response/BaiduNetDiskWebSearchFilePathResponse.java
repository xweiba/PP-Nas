package pp.weiba.thirdparty.baidu.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 百度网盘搜索返回对象
 *
 * @author xiaoweiba1028@gmail.com
 * @date 2023/5/18 22:07
 */
/*
* {
    "errno": 0,
    "list": [
        {
            "category": 6,
            "delete_type": 0,
            "extent_tinyint1": 0,
            "fs_id": 367356318748435,
            "isdir": 1,
            "local_ctime": 1683816459,
            "local_mtime": 1683816459,
            "md5": "",
            "oper_id": 0,
            "owner_id": 4965405763,
            "path": "/switch/游戏/The Legend of Zelda Tears of the Kingdom",
            "server_ctime": 1683816459,
            "server_filename": "The Legend of Zelda Tears of the Kingdom",
            "server_mtime": 1683972622,
            "share": 0,
            "size": 0,
            "wpfile": 0
        },
        {
            "category": 6,
            "delete_type": 0,
            "extent_tinyint1": 0,
            "fs_id": 480274501126151,
            "isdir": 0,
            "local_ctime": 1683818975,
            "local_mtime": 1683819706,
            "md5": "70dd0ec0dv96311223170b83a73754ff",
            "oper_id": 0,
            "owner_id": 4965405763,
            "path": "/switch/游戏/The Legend of Zelda Tears of the Kingdom/The Legend of Zelda Tears of the Kingdom [0100F2C0115B6000] [v0].nsp",
            "server_ctime": 1683823898,
            "server_filename": "The Legend of Zelda Tears of the Kingdom [0100F2C0115B6000] [v0].nsp",
            "server_mtime": 1683972622,
            "share": 0,
            "size": 17150648656,
            "wpfile": 0
        }
    ],
    "request_id": 8963318864349211000,
    "contentlist": [],
    "has_more": 0
}*/
@NoArgsConstructor
@Data
public class BaiduNetDiskWebSearchFilePathResponse {


    @JSONField(name = "errno")
    private Integer errno;
    @JSONField(name = "list")
    private List<ListBO> list;
    @JSONField(name = "request_id")
    private Long requestId;
    @JSONField(name = "contentlist")
    private List<?> contentlist;
    @JSONField(name = "has_more")
    private Integer hasMore;

    @NoArgsConstructor
        @AllArgsConstructor
        @Data
    public static class ListBO {
        @JSONField(name = "category")
        private Integer category;
        @JSONField(name = "delete_type")
        private Integer deleteType;
        @JSONField(name = "extent_tinyint1")
        private Integer extentTinyint1;
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
        @JSONField(name = "oper_id")
        private Integer operId;
        @JSONField(name = "owner_id")
        private Long ownerId;
        @JSONField(name = "path")
        private String path;
        @JSONField(name = "server_ctime")
        private Date serverCtime;
        @JSONField(name = "server_filename")
        private String serverFilename;
        @JSONField(name = "server_mtime")
        private Date serverMtime;
        @JSONField(name = "share")
        private Integer share;
        @JSONField(name = "size")
        private Long size;
        @JSONField(name = "wpfile")
        private Integer wpfile;
    }
}
