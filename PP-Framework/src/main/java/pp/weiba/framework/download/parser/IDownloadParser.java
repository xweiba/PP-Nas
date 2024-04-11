package pp.weiba.framework.download.parser;

import pp.weiba.framework.download.model.DownloadInfo;

/**
 * 下载地址解析器
 *
 * @author weiba
 * @date 2024/4/11 9:26
 */
public interface IDownloadParser<T> {

    DownloadInfo parse(T source) throws UrlParseException;

}
