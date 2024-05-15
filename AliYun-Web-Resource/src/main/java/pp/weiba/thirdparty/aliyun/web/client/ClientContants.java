package pp.weiba.thirdparty.aliyun.web.client;
import lombok.extern.log4j.Log4j2;

/**
* 客户端常量
*
* @author weiba
* @date 2024/5/10 13:55
*/
@Log4j2
public class ClientContants {

    /* 备份盘 id */
    public static final String REQUEST_PARAM_BACKUP_DRIVE_ID_TAG = "{backup_drive_id}";

    /* 资源库 id*/
    public static final String REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG = "{resource_drive_id}";

    /* 当前登录用户 id*/
    public static final String REQUEST_PARAM_RESOURCE_USER_ID_TAG = "{login_user_id}";
}
