package pp.weiba.thirdparty.baidu.web.api.netdisk;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.client.*;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.BaiduNetDiskWebQueryShareFileParams;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.QueryShareOrderType;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.ShareExpireTime;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.SortType;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.*;
import pp.weiba.thirdparty.baidu.web.api.netdisk.utils.BaiduNetDiskWebScript;
import pp.weiba.utils.JSONUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;

/**
 * 分享管理客户端
 *
 * @author weiba
 * @date 2024/3/20 16:23
 */
@Log4j2
public class ShareOperationApiClient extends AbstractApiHttpClient {

    public ShareOperationApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 分享文件
     *
     * @param fsIds           : fsIds 分享文件标识集合, ',' 分隔
     * @param shareExpireTime : shareFileTime 分享时间
     * @param password        : password 指定密码，4位
     * @return 分享信息
     * @author weiba
     * @date 2024/4/8 14:33
     */
    public ShareFileResponse shareFiles(String fsIds, String password, ShareExpireTime shareExpireTime) {
        if (StrUtil.isBlank(fsIds) || StrUtil.isBlank(password) || password.length() != 4) {
            log.error("参数错误！ fsIds: {}, sharePeriod: {}, password: {}", JSONUtils.toJsonStr(fsIds), shareExpireTime, password);
            throw new IllegalArgumentException("参数错误！");
        }
        ShareExpireTime expireTime = shareExpireTime == null ? ShareExpireTime.DAY1 : shareExpireTime;
        ShareFileResponse responseShareFileBO = postExecute(UrlConstants.POST_SHARE_FILES, new HashMap<String, Object>() {{
            put("channel_list", "[]");
            put("period", expireTime.getValue());
            put("pwd", password);
            put("schannel", "4");
            put("fid_list", StrUtil.format("[{}]", fsIds));
        }}, new TypeReference<ShareFileResponse>() {
        });
        responseShareFileBO.setLink(responseShareFileBO.getLink() + "?pwd=" + password);
        return responseShareFileBO;
    }

    /**
     * 根据分享id取消分享
     *
     * @param shareIds 分享id，',' 分隔
     * @return 取消分享结果
     * @author weiba
     * @date 2024/3/20 16:46
     */
    public CancelSharesResponse cancelShareFiles(String shareIds) {
        return execute(UrlConstants.POST_CANCEL_SHARE_FILES, new HashMap<String, Object>() {{
            put("shareid_list", Arrays.toString(shareIds.split(",")));
        }}, new TypeReference<CancelSharesResponse>() {
        });
    }


    /**
     * 根据我的分享id获取分享信息
     *
     * @param shareId 分享id
     * @return 分享信息
     * @author weiba
     * @date 2024/3/20 16:47
     */
    public MyShareResponse getMyShareDetailById(String shareId) {
        String sign = BaiduNetDiskWebScript.makeMD5(shareId + "_sharesurlinfo!@#");
        return execute(StrUtil.format(UrlConstants.GET_MY_SHARE_DETAIL_BY_ID, shareId, sign), new TypeReference<MyShareResponse>() {
        });
    }


    /**
     * 根据分享id获取分享中的子集文件信息
     *
     * @param params
     * @return
     * @author weiba
     * @date 2024/3/20 16:59
     */
    public BaiduNetDiskWebQueryShareFileResponse queryShareFiles(BaiduNetDiskWebQueryShareFileParams params) {
        if (params.getOrder() == null) params.setOrder(QueryShareOrderType.DEFULLT);
        if (params.getDesc() == null) params.setDesc(SortType.DEFULLT);
        if (params.getPageNo() == null) params.setPageNo(1);
        if (params.getPageSize() == null) params.setPageSize(100);
        return execute(StrUtil.format(UrlConstants.GET_QUERY_SHARE_FILES_URL,
                        RandomUtil.randomDouble(0, 1),
                        params.getShareId(), params.getDir(), params.getOrder().getValue(), params.getDesc().getValue(), params.getPageNo(), params.getPageSize()
                ),
                new TypeReference<BaiduNetDiskWebQueryShareFileResponse>() {
                });
    }


    /**
     * 查询我分享的列表
     *
     * @param orderType : orderType 排序规则
     * @param desc      : desc: 1 降序 0 升序
     * @param pageSize  : 每页数量
     * @param pageNo    : 页码，从1开始。
     * @return 分享结果
     * @author weiba
     * @date 2024/3/20 16:52
     */
    public WebBaiduNetDiskQueryMyShareFilesResponse queryMyShareFiles(ShareOrderType orderType, SortType desc, Integer pageSize, Integer pageNo) {
        if (orderType == null) orderType = ShareOrderType.TIME;
        if (desc == null) desc = SortType.DESC;
        if (pageSize == null) pageSize = 100;
        if (pageNo == null) pageNo = 1;
        return execute(StrUtil.format(UrlConstants.GET_QUERY_MY_SHARE_FILES_URL, pageSize, pageNo, orderType, desc), new TypeReference<WebBaiduNetDiskQueryMyShareFilesResponse>() {
        });
    }

    /**
     * 验证分享地址及密码,获取分享页面bdclndCookie
     *
     * @param shortUrl : shareUrl
     * @param password : password
     * @return java.lang.String
     * @author xiaoweiba1028@gmail.com
     * @date 2022/10/11 17:19
     */
    public String shareFileVerify(String shortUrl, String password) {
        if (StrUtil.isBlank(shortUrl) || StrUtil.isBlank(password)) {
            throw new IllegalArgumentException("shareUrl 和 password 不能为空！");
        }
        if (shortUrl.startsWith("1")) {
            shortUrl = shortUrl.substring(1);
        }
        HttpResponse httpResponse = postExecuteResponse(StrUtil.format(UrlConstants.POST_SHARE_FILE_VERIFY_URL, shortUrl, new Date().getTime()), new HashMap<String, Object>() {{
            put("pwd", password);
            put("vcode", "");
            put("vcode_str", "");
        }});
        String bdclndCookieStr = httpResponse.getCookieValue("BDCLND");
        if (StrUtil.isBlank(bdclndCookieStr)) {
            log.error("分享地址校验失败！shareUrl:{}, password:{}", shortUrl, password);
            throw new RuntimeException("分享地址校验失败！");
        }
        log.debug("BDCLND: {}", bdclndCookieStr);
        return bdclndCookieStr;
    }

    public BaiduNetDiskWebShareFileDetailResponse getShareFileDetail(String shortUrl, String password, String bdclndCookie) {
        if (StrUtil.isBlank(shortUrl) || StrUtil.isBlank(password)) {
            throw new IllegalArgumentException("shareUrl 和 password 不能为空！");
        }

        String url = StrUtil.format(UrlConstants.GET_SHARE_FILE_PAGE_TEMPLATE_URL, shortUrl, password);
        log.debug("获取分享页详情，url: {}", url);
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(url).addheader("Cookie", bdclndCookie);
        String body = executeResponse(httpRequest).getBody();
        log.debug("body: {}", body);
        Matcher matcher = UrlConstants.SHARE_INIT_PAGE_PATTERN_REGEX.matcher(body);
        if (matcher.find()) {
            String group = matcher.group();
            log.debug("shareInit result: {}", group);
            return JSONUtils.toBean(group, BaiduNetDiskWebShareFileDetailResponse.class);
        } else {
            log.error("分享地址异常！shareUrl: {}", url);
            throw new RuntimeException("分享地址异常！");
        }
    }

    public BaiduNetDiskWebShareFileTransferResponse shareFileTransferExecute(String shareId, String shareUk, String myUk, String bdclndCookieStr, String dstDirPath, List<String> fsIds) {
        if (CollUtil.isEmpty(fsIds)) {
            log.error("shareFileTransfer 失败! 分享文件不存在！");
            throw new IllegalArgumentException("shareFileTransfer 失败! 分享文件不存在！");
        }

        if (shareUk.equals(myUk)) {
            log.error("shareFileTransfer 失败! 不能转存自己的文件！");
            throw new IllegalArgumentException("shareFileTransfer 失败! 不能转存自己的文件！");
        }
        String[] split = bdclndCookieStr.split("=");
        String url = StrUtil.format(UrlConstants.POST_SHARE_FILE_TRANSFER_URL, shareId, shareUk, split[1]);
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.POST, url).requestParams(new HashMap<String, Object>() {{
            put("fsidlist", JSONUtils.toJsonStr(fsIds));
            put("path", dstDirPath);
        }}).addCookie(split[0], split[1]); // url中会传入bdstoken
        return execute(httpRequest, new TypeReference<BaiduNetDiskWebShareFileTransferResponse>() {
        });
    }
}
