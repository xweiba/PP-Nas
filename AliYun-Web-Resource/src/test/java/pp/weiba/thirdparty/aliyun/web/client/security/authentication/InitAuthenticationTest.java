package pp.weiba.thirdparty.aliyun.web.client.security.authentication;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
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
import pp.weiba.thirdparty.aliyun.web.client.authentication.credentials.JsonFileSetCredentials;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.TokenResponse;
import pp.weiba.thirdparty.aliyun.web.resource.security.authentication.AliYunNetDiskWebAuthentication;
import pp.weiba.utils.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class InitAuthenticationTest extends DefaultTest {

    // 设置客户端认证信息
    protected static String businessId = "1";
    protected static String businessType = "user";

    public static final String TOKEN_SAVE_DIR_PATH = "/src/test/resources/token/";

    public static AliYunNetDiskWebAuthentication baiduWebAuthentication;

    public static IHttpClient httpClient;

    public static AuthenticationApiClient authenticationApiClient;

    public static boolean isHutoolHttpClient = false;

    static void initAhcClientBaiduWebAuthentication() {
        baiduWebAuthentication = buildAliYunNetDiskWebAuthentication();
    }

    static void initHutoolClientBaiduWebAuthentication() {
        isHutoolHttpClient = true;
        baiduWebAuthentication = buildAliYunNetDiskWebAuthentication();
    }

    protected static @NotNull AliYunNetDiskWebAuthentication buildAliYunNetDiskWebAuthentication() {
        buildAuthenticationApiClient();
        // 用户认证信息获取接口
        ICredential<NetDiskAuthentication> netDiskAuthenticationCredential = new JsonFileSetCredentials(authenticationApiClient, TOKEN_SAVE_DIR_PATH, tokenJsonFileName());
        return new AliYunNetDiskWebAuthentication(businessId, businessType, authenticationApiClient, netDiskAuthenticationCredential);
    }

    protected static String getTokenJsonString() {
        return FileUtils.readJsonToWorkDir(TOKEN_SAVE_DIR_PATH, tokenJsonFileName());
    }

    protected static void saveTokenToJsonString(TokenResponse token) {
        FileUtils.writeJsonToWorkDir(token, TOKEN_SAVE_DIR_PATH, tokenJsonFileName());
    }

    protected static String tokenJsonFileName() {
        return businessType + "_" + businessId;
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


    protected static void buildAuthenticationApiClient() {
        initAuthentication();
        authenticationApiClient = new AuthenticationApiClient(httpClient);
    }

    protected static void initAuthentication() {
        if (isHutoolHttpClient) {
            httpClient = buildHutoolHttpClient();
        } else {
            httpClient = buildAHCHttpClient();
        }
    }

    /**
     * 将源码输出到txt文件中
     *
     * @author weiba
     * @date 2024/5/9 9:28
     */
    @Test
    @Disabled
    void test() throws IOException {
        String directoryPath = System.getProperty("user.dir"); // 替换为你的目录路径
        String outputFilePath = directoryPath + "\\java_files_info_"; // 输出文件名

        List<File> javaFiles = new ArrayList<>();
        try {
            Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".java"))
                    .filter(p -> !p.toString().contains("resource_manager_client"))
                    .forEach((item) -> {javaFiles.add(item.toFile());});
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int index = 1;
        Long maxSize = 0l;
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath + index + ".txt"));
        try {
            for (File javaFile : javaFiles) {
                String content = new String(Files.readAllBytes(javaFile.toPath()), StandardCharsets.UTF_8);
                maxSize += content.length();
                String packageName = getPackageName(content);
                String className = getClassName(content);
                String superClassName = getSuperClassName(content);

                writer.write("File: " + javaFile.getName() + "\n");
                writer.write("Package: " + packageName + "\n");
                writer.write("Class: " + className + "\n");
                if (superClassName != null) {
                    writer.write("Super Class: " + superClassName + "\n");
                }
                writer.write("Content:\n");
                writer.write("--------Strat--------\n");
                writer.write(content);
                writer.write("--------End--------\n");
                writer.newLine();
                if (maxSize > 200000) {
                    index++;
                    maxSize = 0l;
                    writer.close();
                    writer = new BufferedWriter(new FileWriter(outputFilePath + index + ".txt"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getPackageName(String content) {
        String pattern = "package\\s+([\\w\\.]+);";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(content);
        if (m.find()) {
            return m.group(1);
        }
        return "unknown";
    }

    private static String getClassName(String content) {
        String pattern = "class\\s+([\\w]+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(content);
        if (m.find()) {
            return m.group(1);
        }
        return "unknown";
    }

    private static String getSuperClassName(String content) {
        String pattern = "class\\s+[\\w]+\\s+extends\\s+([\\w\\.]+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(content);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }
}
