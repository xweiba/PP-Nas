package pp.weiba.http;
import lombok.extern.log4j.Log4j2;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.net.client.adapters.hutool.HutoolHttpClientAdapter;
import pp.weiba.thirdparty.aliyun.web.client.ALiYunWebNetDiskHttpClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.ALiYunWebHttpClientAuthentication;

/**
* 
*
* @author weiba
* @date 2024/5/29 17:07
*/
@Log4j2
@Configuration
public class HttpInitBean {

    @Bean
    public IHttpClient defaultHttpClient() {
        return new HutoolHttpClientAdapter();
    }
    
}
