package pp.weiba.utils;

import java.net.HttpCookie;
import java.util.Collection;

/**
 * HttpCookie
 *
 * @author weiba
 * @date 2024/3/28 14:42
 */
public class HttpCookieUtils {

    public static String getCookiesString(Collection<HttpCookie> cookies) {
        if (cookies == null || cookies.isEmpty()) return "";
        StringBuilder cookieStr = new StringBuilder();
        for (HttpCookie cookie : cookies) {
            cookieStr.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
        }
        return cookieStr.toString();
    }

}
