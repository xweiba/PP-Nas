package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import lombok.SneakyThrows;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;

import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

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
    public static String buildXSignatureV1(SignatureInfo signatureKeyInfo) {
        Integer nonce = signatureKeyInfo.getNonce();
        String privateKey = signatureKeyInfo.getPrivateKey();
        String publicKey = signatureKeyInfo.getPublicKey();
        byte[] dataBytes = (signatureKeyInfo.getAppId() + ":" + signatureKeyInfo.getXDeviceId() + ":" + signatureKeyInfo.getUserId() + ":" + nonce).getBytes(StandardCharsets.UTF_8);
        byte[] dataHash = Hash.sha256(dataBytes);
        ECKeyPair keyPair = new ECKeyPair(new BigInteger(privateKey, 16), new BigInteger(publicKey, 16));
        Sign.SignatureData signatureInfo = Sign.signMessage(dataHash, keyPair, false);
        // 01 为recovered d = (f.x === y.r ? 0 : 2) | Number(f.y & s)， 还未解决，待解决
        return Hex.toHexString(signatureInfo.getR()) + Hex.toHexString(signatureInfo.getS()) + "01";
    }

    public static String buildXSignature(SignatureInfo signatureKeyInfo) {
        return buildXSignatureV1(signatureKeyInfo);
    }

    public static SignatureInfo createSignatureInfo(String userId, String deviceId) {
        return createSignatureInfoV1(APP_ID, userId, deviceId);
    }

    public static SignatureInfo createSignatureInfoV1(String appId, String userId, String deviceId) {
        BigInteger privateKeyInt = new BigInteger(256, new SecureRandom());
        BigInteger publicKeyInt = Sign.publicKeyFromPrivate(privateKeyInt);
        String privateKey = privateKeyInt.toString(16);
        String publicKey = publicKeyInt.toString(16);
        return new SignatureInfo(privateKey, publicKey, appId, userId, deviceId, 0, null);
    }

    @SneakyThrows
    public static SignatureInfo createSignatureInfoV2(String appId, String userId, String deviceId) {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp256k1");
        keyPairGenerator.initialize(ecGenParameterSpec);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        byte[] privateKeyBytes = privateKey.getEncoded();
        String privateKeyHex = bytesToHex(privateKeyBytes);

        PublicKey publicKey = keyPair.getPublic();
        byte[] publicKeyBytes = publicKey.getEncoded();
        String publicKeyHex = bytesToHex(publicKeyBytes);

        return new SignatureInfo(privateKeyHex, publicKeyHex, appId, userId, deviceId, 0, null);
    }

    @SneakyThrows
    public static String buildXSignatureV2(SignatureInfo signatureKeyInfo) {
        Integer nonce = signatureKeyInfo.getNonce();
        String privateKeyHex = signatureKeyInfo.getPrivateKey();

        PrivateKey privateKey = hexToPrivateKey(privateKeyHex);
        // 使用私钥进行签名
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);
        byte[] dataBytes = (signatureKeyInfo.getAppId() + ":" + signatureKeyInfo.getXDeviceId() + ":" + signatureKeyInfo.getUserId() + ":" + nonce).getBytes(StandardCharsets.UTF_8);
        signature.update(dataBytes);
        byte[] signBytes = signature.sign();
        return bytesToHex(signBytes) + "01";
    }

    public static void main (String args[]) throws Exception {
        // 准备签名需要的数据
        String appId = "appId";
        String deviceId = "deviceId";
        String userId = "userId";
        String nonce = "nonce";

        // 生成ECDSA SECP256k1密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp256k1");
        keyPairGenerator.initialize(ecGenParameterSpec);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // 使用私钥进行签名
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(keyPair.getPrivate());
        byte[] data = (appId + deviceId + userId + nonce).getBytes();
        signature.update(data);
        byte[] signBytes = signature.sign();

        // 输出签名生成的十六进制表示
        String signHex = bytesToHex(signBytes);
        System.out.println("签名（十六进制）: " + signHex);

        // 将公钥转换为十六进制表示
        PublicKey publicKey = keyPair.getPublic();
        byte[] publicKeyBytes = publicKey.getEncoded();
        String publicKeyHex = bytesToHex(publicKeyBytes);
        System.out.println("公钥（十六进制）: " + publicKeyHex);

        // 将公钥转换为十六进制表示
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] privateKeyBytes = privateKey.getEncoded();
        String privateKeyHex = bytesToHex(privateKeyBytes);
        System.out.println("私钥（十六进制）: " + privateKeyHex);

        PrivateKey privateKey1 = hexToPrivateKey(privateKeyHex);
        System.out.println(privateKey1);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static PrivateKey hexToPrivateKey(String hex) throws Exception {
        // 将十六进制字符串转换成字节数组
        byte[] bytes = hexStringToByteArray(hex);

        // 创建 PKCS8EncodedKeySpec 对象
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);

        // 获取 KeyFactory
        KeyFactory kf = KeyFactory.getInstance("EC"); // 请确保与你的密钥算法相对应

        // 生成 PrivateKey
        return kf.generatePrivate(spec);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

}
