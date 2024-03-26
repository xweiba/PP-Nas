package pp.weiba.thirdparty.baidu.web.api.security.authentication;

import lombok.Data;
import lombok.experimental.Accessors;
import pp.weiba.framework.KeyValue;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.LoginStatusResponse;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.TemplateVariableResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 百度认证信息
 *
 * @author weiba
 * @date 2024/3/7 16:22
 */
@Accessors(chain = true)
@Data
public class Authentication {

    public static final String COOKIE_HEADER_FORMAT = "BDUSS={}; BDUSS_BFESS={}; STOKEN={}; ";
    public TemplateVariableResponse.Result templateVariable;
    public LoginStatusResponse.LoginInfo loginInfo;
    private String bduss;
    private String stoken;
    private String baiduid;
    private List<KeyValue> cookies;

    public Authentication(String bduss, String stoken, String baiduid) {
        this.bduss = bduss;
        this.stoken = stoken;
        this.cookies = new ArrayList<KeyValue>() {{
            add(new KeyValue("BDUSS", bduss));
            add(new KeyValue("BDUSS_BFESS", bduss));
            add(new KeyValue("BAIDUID", baiduid));
            add(new KeyValue("BAIDUID_BFESS", baiduid));
            add(new KeyValue("STOKEN", stoken));
        }};
    }

}
