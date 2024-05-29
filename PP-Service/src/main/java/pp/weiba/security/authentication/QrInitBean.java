package pp.weiba.security.authentication;
import lombok.extern.log4j.Log4j2;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import pp.weiba.framework.security.authentication.qr.IScanQR;
import pp.weiba.framework.security.authentication.qr.ScheduleScanQR;
import pp.weiba.thirdparty.aliyun.web.client.authentication.ALiYunAuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.model.NetDiskAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.qr.AliYunScanQRImpl;

/**
* 
*
* @author weiba
* @date 2024/5/28 16:43
*/
@Log4j2
@Configuration
public class QrInitBean {

    @Inject
    private ALiYunAuthenticationApiClient aLiYunAuthenticationApiClient;
    
    @Bean
    public IScanQR<NetDiskAuthentication> aliyunScanQr() {
        return new ScheduleScanQR<NetDiskAuthentication>(new AliYunScanQRImpl(aLiYunAuthenticationApiClient));
    }
    
}
