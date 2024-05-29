package pp.weiba.thirdparty.aliyun.web.client.core;
import lombok.extern.log4j.Log4j2;

/**
* 客户端常量
*
* @author weiba
* @date 2024/5/10 13:55
*/
@Log4j2
public class AliYunClientConstants {

    public static final String BUSINESS_TYPE = "aLiYunPan";

    /* 备份盘 id */
    public static final String REQUEST_PARAM_BACKUP_DRIVE_ID_TAG = "{backup_drive_id}";

    /* 资源库 id*/
    public static final String REQUEST_PARAM_RESOURCE_DRIVE_ID_TAG = "{resource_drive_id}";

    /* 当前登录用户 id*/
    public static final String REQUEST_PARAM_RESOURCE_USER_ID_TAG = "{login_user_id}";

    /* 预上传文件 hash */
    public static final String REQUEST_UPLOAD_FILE_PROOF_CODE_TAG = "{upload_proof_code}";

    /* token 不添加 token type*/
    public static final String NOT_ADD_TOKEN_TYPE = "{NotAddTokenType}";

    /* 文件分片大小 */
    public static final Long FILE_SPLIT_SIZE = 10485824L;
}
