package pp.weiba.thirdparty.aliyun.web.client.authentication.request;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;

/**
* OpenAccessToken 获取的身份类型
*
* @author weiba
* @date 2024/5/23 17:04
*/
@Log4j2
public enum OpenAccessTokenGrantType {

    AUTHORIZATION_CODE("authorization_code"),
    REFRESH_TOKEN("refresh_token");

    public static final OpenAccessTokenGrantType DEFULLT = AUTHORIZATION_CODE;

    private final String value;

    OpenAccessTokenGrantType(String value) {
        this.value = value;
    }

    public static OpenAccessTokenGrantType getByValue(String value) {
        OpenAccessTokenGrantType type = DEFULLT;
        if (StrUtil.isNotBlank(value)) {
            for (OpenAccessTokenGrantType temp : OpenAccessTokenGrantType.values()) {
                if (temp.value.equals(value)) {
                    type = temp;
                    break;
                }
            }
        }
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
    
}
