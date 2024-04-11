package pp.weiba.thirdparty.baidu.web.resource.netdisk.download;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import pp.weiba.framework.download.model.DownloadInfo;
import pp.weiba.thirdparty.baidu.web.client.netdisk.FileOperationApiClient;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.WebNetDiskAuthenticationTest;
import pp.weiba.utils.LogUtils;

@Log4j2
class BaiduNetDiskDownloadParserTest extends WebNetDiskAuthenticationTest {

    private final BaiduNetDiskDownloadParser baiduNetDiskDownloadParser = new BaiduNetDiskDownloadParser(new FileOperationApiClient(httpClient));

    @Test
    void parse() {
        DownloadInfo result = baiduNetDiskDownloadParser.parse("298360882864165");
        LogUtils.info(log, result);
    }
}
