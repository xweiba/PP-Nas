package pp.weiba.thirdparty.aliyun.web.client.security.authentication;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.KeyValue;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.framework.net.client.IHttpClientAuthentication;
import pp.weiba.framework.net.client.adapters.ahc.AsyncHttpClientAdapter;
import pp.weiba.framework.net.client.adapters.hutool.HutoolHttpClientAdapter;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.framework.test.DefaultTest;
import pp.weiba.framework.utils.UserInfoUtils;
import pp.weiba.thirdparty.aliyun.web.client.ALiYunWebNetDiskHttpClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.ALiYunAuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.OpenApiAuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.model.NetDiskAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.ALiYunWebHttpClientAuthentication;
import pp.weiba.thirdparty.aliyun.web.client.authentication.credentials.JsonFileSetCredentials;
import pp.weiba.thirdparty.aliyun.web.client.authentication.response.TokenResponse;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.SignInApiClient;
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
    protected static KeyValue userInfo = new KeyValue("user", "1");

    /* 阿里云盘官方id 55091393987b4cc090b090ee17e85e0a, 改成自己的，防止账号被封 */
    public static final String OPEN_API_APP_ID = "55091393987b4cc090b090ee17e85e0a";

    public static final String TOKEN_SAVE_DIR_PATH = "/src/test/resources/authentication/";

    public static AliYunNetDiskWebAuthentication baiduWebAuthentication;

    public static IHttpClient httpClient;

    public static ALiYunAuthenticationApiClient authenticationApiClient;

    public static OpenApiAuthenticationApiClient openApiAuthenticationApiClient;

    public static SignInApiClient signInApiClient;

    public static boolean isHutoolHttpClient = false;

    public static void initAhcClientBaiduWebAuthentication() {
        baiduWebAuthentication = buildAliYunNetDiskWebAuthentication();
    }

    public static void initHutoolClientBaiduWebAuthentication() {
        isHutoolHttpClient = true;
        baiduWebAuthentication = buildAliYunNetDiskWebAuthentication();
    }

    protected static @NotNull AliYunNetDiskWebAuthentication buildAliYunNetDiskWebAuthentication() {
        buildUserInfo();
        buildAuthenticationApiClient();
        // 用户认证信息获取接口
        ICredential<NetDiskAuthentication> netDiskAuthenticationCredential = new JsonFileSetCredentials(authenticationApiClient, TOKEN_SAVE_DIR_PATH, tokenJsonFileName());
        return new AliYunNetDiskWebAuthentication(authenticationApiClient, netDiskAuthenticationCredential, signInApiClient, openApiAuthenticationApiClient, OPEN_API_APP_ID);
    }

    private static void buildUserInfo() {
        UserInfoUtils.setCurrentThreadUserInfo(userInfo);
    }

    protected static String getTokenJsonString() {
        return FileUtils.readJsonToWorkDir(TOKEN_SAVE_DIR_PATH, tokenJsonFileName());
    }

    protected static void saveTokenToJsonString(TokenResponse token) {
        FileUtils.writeJsonToWorkDir(token, TOKEN_SAVE_DIR_PATH, tokenJsonFileName());
    }

    protected static String tokenJsonFileName() {
        return userInfo.getKey() + "_" + userInfo.getValue();
    }


    protected static IHttpClientAuthentication buildHttpClientAuthentication() {
        // 配置当前用户认证信息, 存储中间变量
        return new ALiYunWebHttpClientAuthentication();
    }

    public static IHttpClient buildHutoolHttpClient() {
        return new ALiYunWebNetDiskHttpClient(new HutoolHttpClientAdapter(), buildHttpClientAuthentication());
    }

    protected static IHttpClient buildAHCHttpClient() {
        return new ALiYunWebNetDiskHttpClient(new AsyncHttpClientAdapter(), buildHttpClientAuthentication());
    }


    protected static void buildAuthenticationApiClient() {
        initAuthentication();
        authenticationApiClient = new ALiYunAuthenticationApiClient(httpClient);
        signInApiClient = new SignInApiClient(httpClient);
        openApiAuthenticationApiClient = new OpenApiAuthenticationApiClient(httpClient);
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
