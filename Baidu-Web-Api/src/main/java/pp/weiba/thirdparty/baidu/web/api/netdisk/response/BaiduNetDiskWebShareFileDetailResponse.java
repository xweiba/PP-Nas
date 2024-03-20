package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

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
public class BaiduNetDiskWebShareFileDetailResponse {

    private String csrf;
    private String uk;
    private String username;
    private Integer loginstate;
    private Integer vipLevel;
    private String skinName;
    private String bdstoken;
    private String photo;
    private Integer isVip;
    private Integer isSvip;
    private Integer isEvip;
    private String now;
    private String xduss;
    private Integer currActivityCode;
    private Integer showVipAd;
    private String sharePhoto;
    private String shareUk;
    private String shareid;
    private Boolean hitOgc;
    private Integer expiredType;
    private Integer publicX;
    private Integer ctime;
    private String description;
    private Integer followFlag;
    private Boolean accessListFlag;
    private ElinkInfoBO elinkInfo;
    private Integer ownerVipLevel;
    private Integer ownerVipType;
    private String linkusername;
    private String sharePageType;
    private List<String> titleImg;
    private List<FileListBO> fileList;
    private Integer errortype;
    private Integer errno;
    private Integer ufcTime;
    private Integer self;
    private Integer elinkSelf;

    @NoArgsConstructor
    @Data
    public static class ElinkInfoBO {
        private Integer isElink;
        private Boolean eflagDisable;
    }

    @NoArgsConstructor
    @Data
    public static class FileListBO {
        private String appId;
        private Integer category;
        private String deleteFsId;
        private String extentInt3;
        private String extentTinyint1;
        private String extentTinyint2;
        private String extentTinyint3;
        private String extentTinyint4;
        private String fileKey;
        private String fsId;
        private String isdelete;
        private Integer isdir;
        private Date localCtime;
        private Date localMtime;
        private String md5;
        private String operId;
        private String ownerId;
        private String ownerType;
        private String parentPath;
        private String path;
        private String pathMd5;
        private String privacy;
        private String realCategory;
        private Integer rootNs;
        private String serverAtime;
        private Date serverCtime;
        private String serverFilename;
        private Date serverMtime;
        private String share;
        private Long size;
        private String status;
        private String tkbindId;
        private String videotag;
        private String wpfile;
    }
}
