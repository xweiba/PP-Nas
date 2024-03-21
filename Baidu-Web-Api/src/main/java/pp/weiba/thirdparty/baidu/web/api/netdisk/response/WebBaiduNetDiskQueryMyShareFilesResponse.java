package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

/**
 * @author xiaoweiba1028@gmail.com
 * @description 百度批量取消分享接口Response
 * @date 2023/5/23 0:14
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
@SuperBuilder
public class WebBaiduNetDiskQueryMyShareFilesResponse {

    private int count;
    private String createsharetips_ldlj;
    private int errno;
    private List<ListResponse> list;
    private String newno;
    private int nextpage;
    private long request_id;
    private String show_msg;

    @NoArgsConstructor
    @Data
    public static class ListResponse {
        private int appId;
        private int bitmap;
        private int channel;
        private String channelInfo;
        private int ctime;
        private int dCnt;
        private String description;
        private int dtime;
        private Date expiredTime;
        private int expiredType;
        private List<String> fsIds;
        private int ip;
        private int isElink;
        private int is_card;
        private String name;
        private String passwd;
        private int port;
        private int privilege_type;
        private int publicX;
        private int publicChannel;
        private String shareId;
        private String shareinfo;
        private int sharetype;
        private String shortlink;
        private String shorturl;
        private String sinfo;
        private int status;
        private String substatus;
        private int tCnt;
        private int tag;
        private int tplId;
        private int typicalCategory;
        private String typicalPath;
        private int vCnt;
    }
}