package pp.weiba.thirdparty.aliyun.web.client.authentication.request;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.resource.model.SortType;

/**
* 开放平台无服务认证类型
*
* @author weiba
* @date 2024/5/23 16:51
*/
@Log4j2
public enum OpenAuthorizationPkceType {

    PLAIN("plain"),
    S256("S256");

    public static final OpenAuthorizationPkceType DEFULLT = PLAIN;
    private final String value;

    OpenAuthorizationPkceType(String value) {
        this.value = value;
    }

    public static OpenAuthorizationPkceType getByValue(String value) {
        OpenAuthorizationPkceType type = DEFULLT;
        if (StrUtil.isNotBlank(value)) {
            for (OpenAuthorizationPkceType temp : OpenAuthorizationPkceType.values()) {
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
