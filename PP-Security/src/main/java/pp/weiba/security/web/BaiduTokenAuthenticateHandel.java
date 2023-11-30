package pp.weiba.security.web;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import pp.weiba.framework.security.IAuthenticateHandel;

@Data
public class BaiduTokenAuthenticateHandel implements IAuthenticateHandel {

    public static final String COOKIE_HEADER = "BDUSS={}; BDUSS_BFESS={}; STOKEN={}; ";
    private final String bduss;
    private final String stoken;
    private final String cookie;

    public BaiduTokenAuthenticateHandel(String bduss, String stoken) {
        this.bduss = bduss;
        this.stoken = stoken;
        this.cookie = StrUtil.format(COOKIE_HEADER, bduss, bduss, stoken);
    }

    @Override
    public void authenticate() {

    }
}
