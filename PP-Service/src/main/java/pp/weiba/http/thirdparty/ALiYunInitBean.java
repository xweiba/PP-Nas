package pp.weiba.http.thirdparty;

import lombok.extern.log4j.Log4j2;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.thirdparty.aliyun.web.client.ALiYunWebNetDiskHttpClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.ALiYunWebHttpClientAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.ALiYunAuthenticationApiClient;

/**
 * @author weiba
 * @date 2024/5/28 16:44
 */
@Log4j2
@Configuration
public class ALiYunInitBean {

    @Bean
    public IHttpClient aLiYunWebNetDiskHttpClient(IHttpClient defaultHttpClient) {
        return new ALiYunWebNetDiskHttpClient(defaultHttpClient, new ALiYunWebHttpClientAuthentication());
    }

    @Bean
    public ALiYunAuthenticationApiClient authenticationApiClient(IHttpClient aLiYunWebNetDiskHttpClient) {
        return new ALiYunAuthenticationApiClient(aLiYunWebNetDiskHttpClient);
    }

}
