package pp.weiba.thirdparty.aliyun.web.client.security.authentication;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.net.client.IHttpClientAuthentication;
import pp.weiba.framework.net.client.adapters.ahc.AsyncHttpClientAdapter;
import pp.weiba.framework.net.client.adapters.hutool.HutoolHttpClientAdapter;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.framework.test.DefaultTest;
import pp.weiba.thirdparty.aliyun.web.client.WebAliYunNetDiskHttpClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.NetDiskAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.WebHttpClientAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.credentials.ManualSetCredentials;
import pp.weiba.thirdparty.aliyun.web.resource.security.authentication.AliYunNetDiskWebAuthentication;

@Log4j2
public class InitAuthenticationTest extends DefaultTest {

    // 设置客户端认证信息
    protected static String businessId = "1";
    protected static String businessType = "user";

    //
    protected static String authorization = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIwMDc1ODlkNzczMzk0ZGQxODdjMzk1ZWUzYzc3NDdiMCIsImN1c3RvbUpzb24iOiJ7XCJjbGllbnRJZFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwiLFwiZG9tYWluSWRcIjpcImJqMjlcIixcInNjb3BlXCI6W1wiRFJJVkUuQUxMXCIsXCJTSEFSRS5BTExcIixcIkZJTEUuQUxMXCIsXCJVU0VSLkFMTFwiLFwiVklFVy5BTExcIixcIlNUT1JBR0UuQUxMXCIsXCJTVE9SQUdFRklMRS5MSVNUXCIsXCJCQVRDSFwiLFwiT0FVVEguQUxMXCIsXCJJTUFHRS5BTExcIixcIklOVklURS5BTExcIixcIkFDQ09VTlQuQUxMXCIsXCJTWU5DTUFQUElORy5MSVNUXCIsXCJTWU5DTUFQUElORy5ERUxFVEVcIl0sXCJyb2xlXCI6XCJ1c2VyXCIsXCJyZWZcIjpcImh0dHBzOi8vd3d3LmFsaXBhbi5jb20vXCIsXCJkZXZpY2VfaWRcIjpcIjk5MmQ0MDY0MWY1MzRmZmNiNzYwMzIyNWVhYjBiODM1XCJ9IiwiZXhwIjoxNzE0NDc2NTQ0LCJpYXQiOjE3MTQ0NjkyODR9.NXG8jn_2Tt4mveEK8zHJ7I5CpAx6uemr6htelhhZTBgIMQLqUTO74vpTo9L42ilroZx8C86a5hx8Dg-G82yiA-cKr_RaW0vHt-LSd2lXbZZLo3JpHmUa2KjrcvkYDObGpx1WntpwatzDOR-IEiTuqH9HrakKvo8BEH0MT_X356M";

    public static AliYunNetDiskWebAuthentication baiduWebAuthentication;

    public static IHttpClient httpClient;

    public static boolean isHutoolHttpClient = false;

    static void initAhcClientBaiduWebAuthentication() {
        baiduWebAuthentication = buildAliYunNetDiskWebAuthentication();
    }

    static void initHutoolClientBaiduWebAuthentication() {
        isHutoolHttpClient = true;
        baiduWebAuthentication = buildAliYunNetDiskWebAuthentication();
    }

    protected static @NotNull AliYunNetDiskWebAuthentication buildAliYunNetDiskWebAuthentication() {
        // 用户认证信息获取接口
        ICredential<NetDiskAuthentication> netDiskAuthenticationCredential = new ManualSetCredentials(authorization);
        return new AliYunNetDiskWebAuthentication(businessId, businessType, buildAuthenticationApiClient(), netDiskAuthenticationCredential);
    }


    protected static IHttpClientAuthentication buildHttpClientAuthentication() {
        // 配置当前用户认证信息, 存储中间变量
        return new WebHttpClientAuthentication(businessId, businessType);
    }

    public static IHttpClient buildHutoolHttpClient() {
        return new WebAliYunNetDiskHttpClient(new HutoolHttpClientAdapter(), buildHttpClientAuthentication());
    }

    protected static IHttpClient buildAHCHttpClient() {
        return new WebAliYunNetDiskHttpClient(new AsyncHttpClientAdapter(), buildHttpClientAuthentication());
    }


    protected static AuthenticationApiClient buildAuthenticationApiClient() {
        initAuthentication();
        return new AuthenticationApiClient(httpClient);
    }

    protected static void initAuthentication() {
        if (isHutoolHttpClient) {
            httpClient = buildHutoolHttpClient();
        } else {
            httpClient = buildAHCHttpClient();
        }
    }
}
