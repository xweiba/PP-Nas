package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

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
public class SearchResponse extends ApiResponse {

    private List<ListResponse> list;
    private List<?> contentlist;
    private int has_more;

    @NoArgsConstructor
    @Data
    public static class ListResponse {
        private int category;
        private int delete_type;
        private int extent_tinyint1;
        private String fs_id;
        private int isdir;
        private Date local_ctime;
        private Date local_mtime;
        private String md5;
        private int oper_id;
        private long owner_id;
        private String path;
        private Date server_ctime;
        private String server_filename;
        private Date server_mtime;
        private int share;
        private long size;
        private int wpfile;
    }
}
