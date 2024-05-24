package pp.weiba.thirdparty.aliyun.web.client.authentication;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import pp.weiba.thirdparty.aliyun.web.client.authentication.request.OpenAccessTokenRequest;
import pp.weiba.thirdparty.aliyun.web.client.authentication.request.OpenAuthorizationPkceType;
import pp.weiba.thirdparty.aliyun.web.client.authentication.request.OpenAuthorizationRequest;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.OpenAccessTokenResponse;
import pp.weiba.thirdparty.aliyun.web.client.security.authentication.WebNetDiskAuthenticationTest;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
class OpenApiAuthenticationApiClientTest extends WebNetDiskAuthenticationTest {

    OpenAuthorizationRequest openAuthorizationRequest;

    String authorizationCode;

    @Test
    void getAuthorizationCode() {
        openAuthorizationRequest = new OpenAuthorizationRequest(OPEN_API_APP_ID, OpenAuthorizationPkceType.PLAIN);
        authorizationCode = openApiAuthenticationApiClient.getAuthorizationCode(openAuthorizationRequest);
    }

    @Test
    void getOpenAccessToken() {
        getAuthorizationCode();
        OpenAccessTokenRequest openAccessTokenRequest = new OpenAccessTokenRequest(openAuthorizationRequest.getClientId(), authorizationCode,openAuthorizationRequest.getCodeChallengeStr());
        OpenAccessTokenResponse result = openApiAuthenticationApiClient.getOpenAccessToken(openAccessTokenRequest);
        assertNotNull(result);
    }

}
