package pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.Authentication;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

/**
 * 百度WebCookie
 *
 * @author weiba
 * @date 2024/3/27 13:46
 */
@Log4j2
public abstract class WebCookieCredentials implements ICredential<Authentication> {

    protected String bduss;

    protected String stoken;

    protected String ptoken;

    protected String baiduid;

    protected String ubi;

    public WebCookieCredentials() {
    }

    public WebCookieCredentials(String bduss, String stoken, String ptoken, String baiduid, String ubi) {
        this.bduss = bduss;
        this.stoken = stoken;
        this.ptoken = ptoken;
        this.baiduid = baiduid;
        this.ubi = ubi;
    }

    @Override
    public Authentication getCredential() {
        List<HttpCookie> cookieList = new ArrayList();

        cookieList.addAll(BaiduWebCookieUtils.buildLoginCookies("bduss", bduss));
        cookieList.addAll(BaiduWebCookieUtils.buildLoginCookies("stoken", stoken));
        cookieList.addAll(BaiduWebCookieUtils.buildLoginCookies("ptoken", ptoken));
        cookieList.addAll(BaiduWebCookieUtils.buildLoginCookies("baiduid", baiduid));
        cookieList.addAll(BaiduWebCookieUtils.buildLoginCookies("ubi", ubi));
        return new Authentication(cookieList);
    }

}
