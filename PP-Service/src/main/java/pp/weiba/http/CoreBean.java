package pp.weiba.http;

import lombok.extern.log4j.Log4j2;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.net.client.IHttpClientAuthentication;
import pp.weiba.framework.net.client.adapters.ahc.AsyncHttpClientAdapter;
import pp.weiba.framework.net.client.adapters.hutool.HutoolHttpClientAdapter;
import pp.weiba.thirdparty.aliyun.web.client.ALiYunWebNetDiskHttpClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.ALiYunWebHttpClientAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.baidu.web.client.security.authentication.BaiduYunWebHttpClientAuthentication;

/**
 * @author weiba
 * @date 2024/5/28 16:44
 */
@Log4j2
@Configuration
public class CoreBean {

    @Bean
    public IHttpClient aLiYunWebNetDiskHttpClient() {
        return new ALiYunWebNetDiskHttpClient(new HutoolHttpClientAdapter(), new ALiYunWebHttpClientAuthentication());
    }

    @Bean
    public AuthenticationApiClient authenticationApiClient(IHttpClient aLiYunWebNetDiskHttpClient) {
        return new AuthenticationApiClient(aLiYunWebNetDiskHttpClient);
    }

}
