package pp.weiba.thirdparty.baidu.web.resource.security.authentication.credentials;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.BaiduWebCookieUtils;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.NetDiskAuthentication;
import pp.weiba.thirdparty.baidu.web.api.security.authentication.WebOAuthLoginAuthentication;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 百度Web OAUTH 认证信息
 *
 * @author weiba
 * @date 2024/3/27 13:46
 */
@Log4j2
public abstract class WebCookieCredentials implements ICredential<NetDiskAuthentication> {

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


    private static String getCookieValue(Map<String, HttpCookie> cookieMap, String name) {
        HttpCookie httpCookie = cookieMap.get(name);
        if (httpCookie == null || StrUtil.isBlank(httpCookie.getValue())) {
            log.error("{} cookie is not found", name);
            throw new IllegalArgumentException("cookie is not found");
        }
        return httpCookie.getValue();
    }

    @Override
    public NetDiskAuthentication getCredential() {
        buildCredential();
        return buildNetDiskAuthentication();
    }

    protected NetDiskAuthentication buildNetDiskAuthentication() {
        List<HttpCookie> cookieList = new ArrayList();
        addCookie(cookieList, "bduss", bduss);
        addCookie(cookieList, "stoken", stoken);
        addCookie(cookieList, "ptoken", ptoken);
        addCookie(cookieList, "baiduid", baiduid);
        addCookie(cookieList, "ubi", ubi);
        Map<String, HttpCookie> webOAuthCookies = cookieList.stream().collect(Collectors.toMap(HttpCookie::getName, item -> item));
        return new NetDiskAuthentication(new WebOAuthLoginAuthentication(webOAuthCookies));
    }

    private void addCookie(List<HttpCookie> cookieList, String cookieName, String cookieValue) {
        if (StrUtil.isBlank(cookieName) || StrUtil.isBlank(cookieValue)) return;
        cookieList.addAll(BaiduWebCookieUtils.buildLoginCookies(cookieName, cookieValue));
    }

    public void initCookies(Map<String, HttpCookie> cookieMap) {
        this.bduss = getCookieValue(cookieMap, "BDUSS");
        this.stoken = getCookieValue(cookieMap, "STOKEN");
        this.ptoken = getCookieValue(cookieMap, "PTOKEN");
        this.baiduid = getCookieValue(cookieMap, "BAIDUID");
        this.ubi = getCookieValue(cookieMap, "UBI");
    }

}
