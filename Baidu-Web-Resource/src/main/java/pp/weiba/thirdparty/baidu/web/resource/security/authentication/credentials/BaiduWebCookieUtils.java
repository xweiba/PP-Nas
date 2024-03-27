package pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials;

import pp.weiba.utils.StringUtils;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

/**
 * 百度Cookie工具
 *
 * @author weiba
 * @date 2024/3/27 13:21
 */
public class BaiduWebCookieUtils {

    private static final String PASSPORT_DOMAIN = "passport.baidu.com";

    private static final String DOMAIN = "baidu.com";

    private static final String BAIDU_COOKIES = ",BDUSS,BAIDUID,";

    private static final String PASSPORT_COOKIES = ",STOKEN,PTOKEN,UBI,";

    private static final String BFESS_COOKIES = ",STOKEN,PTOKEN,BDUSS,BAIDUID,UBI,";

    public static List<HttpCookie> buildLoginCookies(String name, String value) {
        String upperCaseName = name.toUpperCase();
        List<HttpCookie> cookies = new ArrayList<>();
        cookies.add(buildLoginCookie(upperCaseName, value, false));
        if (StringUtils.contains(BFESS_COOKIES, upperCaseName)) {
            cookies.add(buildLoginCookie(upperCaseName, value, true));
        }
        return cookies;
    }


    public static HttpCookie buildLoginCookie(String name, String value, boolean addBfess) {
        String upperCaseName = name.toUpperCase() + (addBfess ? "_BFESS" : "");
        HttpCookie httpCookie = new HttpCookie(upperCaseName, value);
        httpCookie.setDiscard(false);
        if (StringUtils.contains(PASSPORT_COOKIES, upperCaseName)) {
            httpCookie.setDomain(PASSPORT_DOMAIN);
        } else {
            httpCookie.setDomain(DOMAIN);
        }
        httpCookie.setHttpOnly(false);
        httpCookie.setMaxAge(0);
        httpCookie.setPath("/");
        httpCookie.setSecure(true);
        httpCookie.setVersion(0);
        return httpCookie;
    }

}
