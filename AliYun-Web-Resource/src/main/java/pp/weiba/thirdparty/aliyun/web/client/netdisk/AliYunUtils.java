package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * 阿里云工具类
 *
 * @author weiba
 * @date 2024/4/30 15:47
 */
public class AliYunUtils {

    /* 阿里云盘 App Id*/
    public static final String APP_ID = "5dde4e1bdf9e4966b387ba58f4b3fdc3";

    /**
     * 阿里云盘 x-signature 生成
     *
     * @param signatureKeyInfo 签名信息
     * @return x-signature
     * @author weiba
     * @date 2024/4/30 15:49
     */
    public static String buildXSignature(SignatureInfo signatureKeyInfo) {
        Integer nonce = signatureKeyInfo.getNonce();
        String privateKey = signatureKeyInfo.getPrivateKey();
        String publicKey = signatureKeyInfo.getPublicKey();
        byte[] dataBytes = (signatureKeyInfo.getAppId() + ":" + signatureKeyInfo.getXDeviceId() + ":" + signatureKeyInfo.getUserId() + ":" + nonce).getBytes(StandardCharsets.UTF_8);
        byte[] dataHash = Hash.sha256(dataBytes);
        ECKeyPair keyPair = new ECKeyPair(new BigInteger(privateKey, 16), new BigInteger(publicKey, 16));
        Sign.SignatureData signatureInfo = Sign.signMessage(dataHash, keyPair, false);
        return Hex.toHexString(signatureInfo.getR()) + Hex.toHexString(signatureInfo.getS()) + "01";
    }

    public static SignatureInfo createSignatureInfo(String userId, String deviceId) {
        return createSignatureInfo(APP_ID, userId, deviceId);
    }

    public static SignatureInfo createSignatureInfo(String appId, String userId, String deviceId) {
        BigInteger privateKeyInt = new BigInteger(256, new SecureRandom());
        BigInteger publicKeyInt = Sign.publicKeyFromPrivate(privateKeyInt);
        String privateKey = privateKeyInt.toString(16);
        String publicKey = publicKeyInt.toString(16);
        return new SignatureInfo(privateKey, publicKey, appId, userId, deviceId, 0, null);
    }

}
