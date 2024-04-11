package pp.weiba.thirdparty.baidu.web.resource.netdisk.download;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.HttpResponse;
import pp.weiba.framework.download.model.DownloadAuthInfo;
import pp.weiba.framework.download.model.DownloadInfo;
import pp.weiba.framework.download.parser.IDownloadParser;
import pp.weiba.framework.download.parser.UrlParseException;
import pp.weiba.thirdparty.baidu.web.api.netdisk.FileOperationApiClient;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.FileDetailByFSIdResponse;

/**
 * 百度网盘下载解析器
 *
 * @author weiba
 * @date 2024/4/11 10:13
 */
@Log4j2
public class BaiduNetDiskDownloadParser implements IDownloadParser<String> {


    private final FileOperationApiClient fileOperationApiClient;

    public BaiduNetDiskDownloadParser(FileOperationApiClient fileOperationApiClient) {
        this.fileOperationApiClient = fileOperationApiClient;
    }

    @Override
    public DownloadInfo parse(String fsId) throws UrlParseException {
        FileDetailByFSIdResponse fileInfoByFsIds = fileOperationApiClient.getFileInfoByFsIds(fsId);
        if (fileInfoByFsIds == null || CollUtil.isEmpty(fileInfoByFsIds.getList())) {
            throw new UrlParseException("资源不存在");
        }

        FileDetailByFSIdResponse.ListBO item = fileInfoByFsIds.getList().get(0);
        HttpResponse response = fileOperationApiClient.getRealDownloadUrl(item.getDlink(), item.getSize());
        String realDownloadUrl = response.getHeaderMap().get("Location");
        if (StrUtil.isBlank(realDownloadUrl)) {
            throw new UrlParseException("真实下载地址获取失败");
        }

        DownloadAuthInfo authInfo = new DownloadAuthInfo();
        if (item.getSize() >= FileOperationApiClient.LARGE_MAX_SIZE) {
            authInfo.addheader("User-Agent", FileOperationApiClient.LARGE_FILE_USER_AGENT);
        }
        // 不要鉴权信息
        // authInfo.setCookieMap(response.getCookieMap());
        DownloadInfo downloadInfo = new DownloadInfo()
                .setDownloadUrl(realDownloadUrl)
                .setFileSize(item.getSize())
                .setAuthInfo(authInfo);
        return downloadInfo;
    }

}
