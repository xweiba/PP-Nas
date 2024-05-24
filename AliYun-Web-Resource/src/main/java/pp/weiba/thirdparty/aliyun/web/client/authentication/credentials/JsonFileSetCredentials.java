package pp.weiba.thirdparty.aliyun.web.client.authentication.credentials;

import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.aliyun.web.client.authentication.AuthenticationApiClient;
import pp.weiba.thirdparty.aliyun.web.client.authentication.model.NetDiskAuthentication;
import pp.weiba.utils.FileUtils;

/**
 * 从文件设置认证
 *
 * @author weiba
 * @date 2024/5/8 16:56
 */
@Log4j2
public class JsonFileSetCredentials extends JsonStrSetCredentials {

    private final String relativeDirectory;
    private final String fileName;

    public JsonFileSetCredentials(AuthenticationApiClient authenticationApiClient, String relativeDirectory, String fileName) {
        super(authenticationApiClient, FileUtils.readJsonToWorkDir(relativeDirectory, fileName));
        this.relativeDirectory = relativeDirectory;
        this.fileName = fileName;
    }

    @Override
    public NetDiskAuthentication refresh() {
        super.refresh();
        // 会刷新 写回文件
        FileUtils.writeJsonToWorkDir(credentialData, relativeDirectory, fileName);
        return credentialData;
    }
}
