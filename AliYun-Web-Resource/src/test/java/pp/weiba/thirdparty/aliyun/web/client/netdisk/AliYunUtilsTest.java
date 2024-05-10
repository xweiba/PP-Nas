package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class AliYunUtilsTest {

    int[] byteArray =  new int[] {159, 86, 99, 251, 84, 103, 62, 253, 154, 90, 22, 203, 78, 16, 59, 115, 32, 2, 209, 234, 90, 172, 179, 129, 132, 85, 0, 241, 28, 108, 69, 48};

    SignatureInfo signatureKeyInfo = new SignatureInfo().setAppId(AliYunUtils.APP_ID)
            .setUserId("0075833233234dd187c395ee3c7747b0")
            .setNonce(1)
            .setXDeviceId("fiu3hktiwvecatotjrncrg7u")
            .setPrivateKey("c1ca99d14bc9dfdedf1d0e4819bf43a3513d9bec4c1d40fefcc6370cbfd4195b")
            .setPublicKey("04d3716a6fd490cb5ec0e312476e8af615f3b164112ad4963e9827ac7203c91df028a842c4e98e5604297bab820ed16d249212643b9482193d0e7493c3b70cfce4")
            ;

    @Test
    @Disabled
    void getSignatureToSha256Byte() {
        byte[] signatureToSha256Byte = AliYunUtils.getSignatureToSha256Byte(signatureKeyInfo);
        String bytesToHex = AliYunUtils.bytesToHex(signatureToSha256Byte);
        Assertions.assertEquals("998cfdeb38dc10c6da4528ef26f78c4f8825ef33af44808342d17d4512f0fd6c", bytesToHex);
    }

    @Test
    @Disabled
    void buildXSignatureV2() {

        // signature c6a6556869ecf921731aea487fb20c25d03a1b92c3141eb770ca185043bf62b0638fdecf4134de99772162780ec0b2ed89322d2413d6f20d2167b49b628360b0

        String signature = AliYunUtils.buildXSignatureV1(signatureKeyInfo);
        Assertions.assertEquals("c6a6556869ecf921731aea487fb20c25d03a1b92c3141eb770ca185043bf62b0638fdecf4134de99772162780ec0b2ed89322d2413d6f20d2167b49b628360b0", signature);

    }
}
