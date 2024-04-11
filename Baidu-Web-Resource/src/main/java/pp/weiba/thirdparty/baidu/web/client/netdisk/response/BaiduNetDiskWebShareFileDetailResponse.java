package pp.weiba.thirdparty.baidu.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/*
{"csrf":"3Erd7a6W-xNXxXhaItcCxjgrcDL27TVDSvvU","uk":"1059102284","username":"qq17382899","loginstate":1,"vip_level":1,"skinName":"white","bdstoken":"5c8dbe38af63a261a7a745d24913317d","photo":"https://dgss0.bdstatic.com/6LZ1dD3d1sgCo2Kml5_Y_D3/sys/portrait/item/d03f717131373338323839399324","is_vip":0,"is_svip":0,"is_evip":0,"now":"2024-04-09T06:43:34.100Z","XDUSS":"kW2aDquxOjMMpZyg%2F4QgIO4C2wQ%2FTSoDMW%2BaLPQTDdscgERYGhagxIX1FBfTQwe3yb%2BigXEgC6PemjFnqAsjWQ5Wg6hgSKm1mV6Rk6wl26pv1OOn97FGDL6G%2Fh8BsBB6U1IFTju1BFBlLejjQRTcSKiVgwpSEGEmiNTuM5uSj9TDG6BoA1AnOvVTmeUpJE07H6Nv45%2FsGoGn6D%2B3Afaea6hDDkQzeLRIz3B4xCcK%2Fi1LWt1b1%2BSO%2BUksqeJyLXq2%2Bnkukz8RAqb6I3VOngo1EQ%3D%3D","curr_activity_code":0,"show_vip_ad":0,"share_photo":"https://himg.bdimg.com/sys/portrait/item/public.1.a57e5690.WdE7RvlTCAmA0jwKAVvvUw.jpg","share_uk":"1100963797611","shareid":7576225925,"hit_ogc":false,"expiredType":0,"public":0,"ctime":1712034378,"description":"","followFlag":0,"access_list_flag":true,"Elink_info":{"isElink":0,"eflag_disable":false},"sharetype":0,"view_visited":0,"view_limit":0,"owner_vip_level":1,"owner_svip10_id":"","owner_vip_type":2,"linkusername":"甜蜜***之星","share_page_type":"multi","title_img":["iVBORw0KGgoAAAANSUhEUgAAARsAAAAeCAIAAACT9S1xAAAEhUlEQVR42uzbXWhSbRwA8LM1P1OzDxG/utIkMCzo5gSKVxU5pKheJKjuMj+KqBMpBYL0IZmVkGRQ9HVhF2FKXngVGwzHdiYyphdzH+qY24XzwrmBx82dF/bEQXx7e1943yv3/12d///8fYTz8Od5zvE4QNM0BgD4n/TDJQAAOgoA6CgAoKMAANBRAEBHAQAdBQD4tx3VaDQePnxIUdQ/DvHx48dAIPCbAofDMT09Ddca7AQDf3di165d7969W1tb8/v9v/l8NBq9ceMGj8dTKBSXLl36a8HQ0NCnT59cLlepVPrlCHK5nM1mw0yA3tDHvDMxNTUVDoc7z2Wz2QMHDqhUKiaD4/jVq1c7Vye73f7161eZTHby5MlgMHjlypWuLzAYDMVikcvlonB5eXn37t0ikYgpiMfjOp0OZgL02hpVLpe/ffvm8XiYjFgs3tzcVCqVTEYmk6GDVqt1//79N2/exGKx06dPYxj2/fv3c+fOZbPZR48e8fl8VPb27dtqtTo3N8fhcFBmcNv169fh0oPe3/Xt3bv31q1bTPjgwYPV1dXODJJOp2022/z8fDAYFAgEIyMjKP/ixQuHw5FIJHw+n9VqLRQKBEHE43GmnQDYufdRGIY1m00Wi9WV9Hq9T548cblcS0tLHz58QBu59fV1tVqNYZjFYtHpdE6nc3Jy0mw2EwRRLpcHBweZj5MkubCwkEwmUchms2OxGEwD6MH7qGQyeffu3cePH+fzeZRJJBJcLvfUqVNM9eXLlymKarfbhw8fZpLPnj2bmJj48uULk6nVaiwWC90sud3uYrFot9vRKY/Hg+O4xWJBYX9/v9FohGkAPbhGtVotFou1sbHRbDZRZnFxkc1mMyGGYVtbW2KxOJ1Odz4Nz+fzlUolHo93jovjOPP4QaVSmUwmdLx///5Dhw4xIQA921H1el0kEv2xDYWhUIjP51+7du3gwYNMWSaTiUQinUOUSqV6vd6VlMvlUqkUri/Yubs+r9dbKBSi0SgKfT5fPp/XaDS5XK5r/UECgcDZs2c1Gg2z63v//v3Ro0ePHTuGCpxO59jYWKVSoWmaeWA4Ozu7Z88eiUTCjKNWqzt3jAD0yBpFkiSO4+h4dHQ0EAiQJKlUKvV6vdvt7vqd9+XLl36/n7kdQiiKMhqN4XAY/Sp1+/btRqPR9X03b940Go0XLlxgMjweD6YB9A56W7Va5XA46XSapulUKiUSiV69eoVO5XI5iURy8eLFlZUVlAmHwwKBABXTNP306VOr1YqOU6mUUCgkCKLdbtO/YjabX79+TQPQo352FEEQR44cqdVqNpttYGDg+fPnnUUzMzN6vV4sFkcikfHxcaFQ+OPHD4qiPn/+nEgkDAbDvXv3mGKSJBUKRSaTgY4CO9DPXd/58+fPnDnT19dXqVSGh4dPnDjRuY6p1WqSJEOhkMlk0mq1pVJp37596KW+ZrOp1Wrv3LnDFB8/frzzJYkuUqlUKBTC1gD0/pMJAMB/B/+PAgA6CgDoKACgowAA0FEAQEcBAB0FAOj2ZwAAAP//N4/PxIVVQH4AAAAASUVORK5CYII="],"file_list":[{"app_id":"250528","category":6,"delete_fs_id":"0","extent_int3":"1100963797611","extent_tinyint1":"0","extent_tinyint2":"0","extent_tinyint3":"0","extent_tinyint4":"0","file_key":"","fs_id":177972647024654,"isdelete":"0","isdir":1,"local_ctime":1610727197,"local_mtime":1610727197,"md5":"","oper_id":"1100963797611","owner_id":"5284923337","owner_type":"1","parent_path":"%2Fsharelink1100963797611-177972647024654","path":"/sharelink1100963797611-177972647024654/庆余年","path_md5":"5928317813339630865","privacy":"1","real_category":"0","root_ns":6622041620,"server_atime":"0","server_ctime":1610727197,"server_filename":"庆余年","server_mtime":1712034347,"share":"0","size":0,"status":"0","tkbind_id":"0","videotag":"0","wpfile":"0"}],"errortype":-1,"errno":0,"ufcTime":2350,"error":0,"data":{"expName":"ad_conf_print_list"},"self":0,"elink_self":0}
*/

/**
 * 分享详情首页信息
 *
 * @author xiaoweiba1028@gmail.com
 * @date 2024/4/9 14:44
 */

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaiduNetDiskWebShareFileDetailResponse {

    @JSONField(name = "csrf")
    private String csrf;
    @JSONField(name = "uk")
    private String uk;
    @JSONField(name = "username")
    private String username;
    @JSONField(name = "loginstate")
    private Integer loginstate;
    @JSONField(name = "vip_level")
    private Integer vipLevel;
    @JSONField(name = "skinName")
    private String skinName;
    @JSONField(name = "bdstoken")
    private String bdstoken;
    @JSONField(name = "photo")
    private String photo;
    @JSONField(name = "is_vip")
    private Integer isVip;
    @JSONField(name = "is_svip")
    private Integer isSvip;
    @JSONField(name = "is_evip")
    private Integer isEvip;
    @JSONField(name = "now")
    private String now;
    @JSONField(name = "XDUSS")
    private String xduss;
    @JSONField(name = "curr_activity_code")
    private Integer currActivityCode;
    @JSONField(name = "show_vip_ad")
    private Integer showVipAd;
    @JSONField(name = "share_photo")
    private String sharePhoto;
    @JSONField(name = "share_uk")
    private String shareUk;
    @JSONField(name = "shareid")
    private String shareid;
    @JSONField(name = "hit_ogc")
    private Boolean hitOgc;
    @JSONField(name = "expiredType")
    private Integer expiredType;
    @JSONField(name = "public")
    private Integer publicX;
    @JSONField(name = "ctime")
    private Integer ctime;
    @JSONField(name = "description")
    private String description;
    @JSONField(name = "followFlag")
    private Integer followFlag;
    @JSONField(name = "access_list_flag")
    private Boolean accessListFlag;
    @JSONField(name = "Elink_info")
    private ElinkInfoDTO elinkInfo;
    @JSONField(name = "sharetype")
    private Integer sharetype;
    @JSONField(name = "view_visited")
    private Integer viewVisited;
    @JSONField(name = "view_limit")
    private Integer viewLimit;
    @JSONField(name = "owner_vip_level")
    private Integer ownerVipLevel;
    @JSONField(name = "owner_svip10_id")
    private String ownerSvip10Id;
    @JSONField(name = "owner_vip_type")
    private Integer ownerVipType;
    @JSONField(name = "linkusername")
    private String linkusername;
    @JSONField(name = "share_page_type")
    private String sharePageType;
    @JSONField(name = "title_img")
    private List<String> titleImg;
    @JSONField(name = "file_list")
    private List<FileListDTO> fileList;
    @JSONField(name = "errortype")
    private Integer errortype;
    @JSONField(name = "errno")
    private Integer errno;
    @JSONField(name = "ufcTime")
    private Integer ufcTime;
    @JSONField(name = "error")
    private Integer error;
    @JSONField(name = "data")
    private DataDTO data;
    @JSONField(name = "self")
    private Integer self;
    @JSONField(name = "elink_self")
    private Integer elinkSelf;

    @NoArgsConstructor
    @Data
    public static class ElinkInfoDTO {
        @JSONField(name = "isElink")
        private Integer isElink;
        @JSONField(name = "eflag_disable")
        private Boolean eflagDisable;
    }

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JSONField(name = "expName")
        private String expName;
    }

    @NoArgsConstructor
    @Data
    public static class FileListDTO {
        @JSONField(name = "app_id")
        private String appId;
        @JSONField(name = "category")
        private Integer category;
        @JSONField(name = "delete_fs_id")
        private String deleteFsId;
        @JSONField(name = "extent_int3")
        private String extentInt3;
        @JSONField(name = "extent_tinyint1")
        private String extentTinyint1;
        @JSONField(name = "extent_tinyint2")
        private String extentTinyint2;
        @JSONField(name = "extent_tinyint3")
        private String extentTinyint3;
        @JSONField(name = "extent_tinyint4")
        private String extentTinyint4;
        @JSONField(name = "file_key")
        private String fileKey;
        @JSONField(name = "fs_id")
        private String fsId;
        @JSONField(name = "isdelete")
        private String isdelete;
        @JSONField(name = "isdir")
        private Integer isdir;
        @JSONField(name = "local_ctime")
        private Date localCtime;
        @JSONField(name = "local_mtime")
        private Date localMtime;
        @JSONField(name = "md5")
        private String md5;
        @JSONField(name = "oper_id")
        private String operId;
        @JSONField(name = "owner_id")
        private String ownerId;
        @JSONField(name = "owner_type")
        private String ownerType;
        @JSONField(name = "parent_path")
        private String parentPath;
        @JSONField(name = "path")
        private String path;
        @JSONField(name = "path_md5")
        private String pathMd5;
        @JSONField(name = "privacy")
        private String privacy;
        @JSONField(name = "real_category")
        private String realCategory;
        @JSONField(name = "root_ns")
        private Long rootNs;
        @JSONField(name = "server_atime")
        private Date serverAtime;
        @JSONField(name = "server_ctime")
        private Date serverCtime;
        @JSONField(name = "server_filename")
        private String serverFilename;
        @JSONField(name = "server_mtime")
        private Date serverMtime;
        @JSONField(name = "share")
        private String share;
        @JSONField(name = "size")
        private Long size;
        @JSONField(name = "status")
        private String status;
        @JSONField(name = "tkbind_id")
        private String tkbindId;
        @JSONField(name = "videotag")
        private String videotag;
        @JSONField(name = "wpfile")
        private String wpfile;
    }
}
