package pp.weiba.thirdparty.baidu.web.security.authentication;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.baidu.web.netdisk.base.LoginStatusResponse;
import pp.weiba.thirdparty.baidu.web.netdisk.base.TemplateVariableResponse;

/**
 * 百度认证信息
 *
 * @author weiba
 * @date 2024/3/7 16:22
 */
@Accessors(chain = true)
@Getter
@Setter
public class Authentication {

    public static final String COOKIE_HEADER_FORMAT = "BDUSS={}; BDUSS_BFESS={}; STOKEN={}; ";
    public TemplateVariableResponse.ResultBO templateVariable;
    public LoginStatusResponse.LoginInfo loginInfo;
    private String bduss;
    private String stoken;
    private String cookie;

    public Authentication(String bduss, String stoken) {
        this.bduss = bduss;
        this.stoken = stoken;
        this.cookie = StrUtil.format(COOKIE_HEADER_FORMAT, bduss, bduss, stoken);
    }

}
