package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;
import pp.weiba.thirdparty.aliyun.web.client.core.AliYunClientConstants;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.request.CreateWithFoldersRequest;
import pp.weiba.utils.FileUtils;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

import static cn.hutool.core.io.FileUtil.file;
import static cn.hutool.core.io.FileUtil.getMimeType;

/**
 * 阿里云工具类
 *
 * @author weiba
 * @date 2024/4/30 15:47
 */
@Log4j2
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
        String privateKey = signatureKeyInfo.getPrivateKey();
        String publicKey = signatureKeyInfo.getPublicKey();
        byte[] dataHash = getSignatureToSha256Byte(signatureKeyInfo);
        ECKeyPair keyPair = new ECKeyPair(new BigInteger(privateKey, 16), new BigInteger(publicKey, 16));
        Sign.SignatureData signatureInfo = Sign.signMessage(dataHash, keyPair, false);
        // 01 为recovered d = (f.x === y.r ? 0 : 2) | Number(f.y & s)， 还未解决，待解决
        return Hex.toHexString(signatureInfo.getR()) + Hex.toHexString(signatureInfo.getS()) + "01";
    }

    public static byte[] getSignatureToSha256Byte(SignatureInfo signatureKeyInfo) {
        // 5dde4e1bdf9e4966b387ba58f4b3fdc3:M0O2Hp0DTDMCAToTJrnFgvAc:0075833233234dd187c395ee3c7747b0:1
        String signatureString = signatureKeyInfo.getAppId() + ":" + signatureKeyInfo.getXDeviceId() + ":" + signatureKeyInfo.getUserId() + ":" + signatureKeyInfo.getNonce();
        byte[] dataBytes = signatureString.getBytes(StandardCharsets.UTF_8);
        byte[] dataHash = Hash.sha256(dataBytes);
        return dataHash;
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

    public static String intBytesToHex(int[] intByteArray ) {
        StringBuilder hexString = new StringBuilder();

        for (int b : intByteArray) {
            if (b < 10) {
                hexString.append("0");
            }
            hexString.append(Integer.toHexString(b));
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

    //默认大小
    public static final Integer DEFAULT_SIZE = 10480000;


    @SneakyThrows
    public static final String preHash(String path) {
        byte[] bytes = DigestUtil.sha1(FileUtils.getSliceFile(new File(path), 0, 1024));
        return HexUtil.encodeHexStr(bytes);
    }

    private static String convertHashToString(byte[] hashBytes) {
        StringBuilder returnVal = new StringBuilder();
        for (byte hashByte : hashBytes) {
            returnVal.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
        }
        return returnVal.toString().toUpperCase();
    }


    public static String proofCode(String path, String accessToken) {
        File file = new File(path);
        if (!file.isFile() || !file.exists()) {
            throw new RuntimeException("path必须为文件且必须存在");
        }
        String n = accessToken;
        BigInteger r = HexUtil.toBigInteger(SecureUtil.md5(n).substring(0, 16));
        BigInteger i = BigInteger.valueOf(file.length());
        BigInteger o = i.longValue() > 0 ? r.mod(i) : BigInteger.ZERO;
        long start = o.longValue();
        long end = NumberUtil.min(o.add(BigInteger.valueOf(8L)).longValue(), i.longValue());
        int len = Convert.toInt(end - start);
        byte[] b = FileUtils.getSliceFile(file, start, len);
        return Base64.encode(b);
    }

    public static List<CreateWithFoldersRequest.PartInfoListRequest> getPartInfoList(long size) {
        List<CreateWithFoldersRequest.PartInfoListRequest> partInfoList = null;
        if (size > 0) {
            // 单个文件分片最大限制5GB，最小限制 100KB
            partInfoList = new ArrayList<>();

            if (size < AliYunClientConstants.FILE_SPLIT_SIZE) {
                partInfoList.add(new CreateWithFoldersRequest.PartInfoListRequest().setPartNumber(1));
            } else {
                double splitCount = Math.ceil(size / (AliYunClientConstants.FILE_SPLIT_SIZE * 1.0));
                for (int j = 0; j < splitCount; j++) {
                    partInfoList.add(new CreateWithFoldersRequest.PartInfoListRequest().setPartNumber(j + 1));
                }
            }
        }
        return partInfoList;
    }

}
