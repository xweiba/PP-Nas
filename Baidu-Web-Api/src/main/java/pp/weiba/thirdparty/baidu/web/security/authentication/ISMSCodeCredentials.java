package pp.weiba.thirdparty.baidu.web.security.authentication;

/**
 * 短信验证接口
 *
 * @author weiba
 * @date 2024/3/7 16:34
 */
public interface ISMSCodeCredentials {

    // 发送短信
    Boolean sendSMS(String phone);

    // 短信验证
    Boolean verifySMS(String phone, String code);

}
