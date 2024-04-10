package pp.weiba.thirdparty.baidu.web.api.netdisk;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.KeyValue;
import pp.weiba.framework.core.client.*;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.resource.model.OrderType;
import pp.weiba.framework.resource.model.SortType;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.BaiduNetDiskWebQueryShareFileParams;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.QueryShareOrderType;
import pp.weiba.thirdparty.baidu.web.api.netdisk.request.ShareExpireTime;
import pp.weiba.thirdparty.baidu.web.api.netdisk.response.*;
import pp.weiba.thirdparty.baidu.web.api.netdisk.utils.BaiduNetDiskWebScript;
import pp.weiba.utils.JSONUtils;

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
     * 查询我分享的列表, 注意此接口不返回的密码
     *
     * @param orderType : orderType 排序规则
     * @param desc      : desc: 1 降序 0 升序
     * @param pageSize  : 每页数量
     * @param pageNo    : 页码，从1开始。
     * @return 分享结果
     * @author weiba
     * @date 2024/3/20 16:52
     */
    public WebBaiduNetDiskQueryMyShareFilesResponse queryMyShareFiles(OrderType orderType, SortType desc, Integer pageSize, Integer pageNo) {
        if (orderType == null) orderType = OrderType.CREATE_TIME;
        if (desc == null) desc = SortType.DESC;
        if (pageSize == null) pageSize = 100;
        if (pageNo == null) pageNo = 1;
        return execute(StrUtil.format(UrlConstants.GET_QUERY_MY_SHARE_FILES_URL, pageSize, pageNo, orderType, desc), new TypeReference<WebBaiduNetDiskQueryMyShareFilesResponse>() {
        });
    }

    /**
     * 根据我的分享id获取分享信息, 可查询分享密码
     *
     * @param shareId 分享id
     * @return 分享信息
     * @author weiba
     * @date 2024/3/20 16:47
     */
    public MyShareResponse getMyShareDetailById(String shareId) {
        String sign = BaiduNetDiskWebScript.makeMD5(shareId + "_sharesurlinfo!@#");
        return execute(new TypeReference<MyShareResponse>() {
        }, UrlConstants.GET_MY_SHARE_DETAIL_BY_ID, shareId, sign);
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
        return postExecute(UrlConstants.POST_CANCEL_SHARE_FILES, new HashMap<String, Object>() {{
            put("shareid_list", StrUtil.format("[{}]", shareIds));
        }}, new TypeReference<CancelSharesResponse>() {
        });
    }

    /**
     * 验证分享地址及密码,获取分享页面bdclndCookie
     *
     * @param shareLink : shareUrl
     * @param password  : password
     * @return 返回认证信息
     * @author xiaoweiba1028@gmail.com
     * @date 2022/10/11 17:19
     */
    public KeyValue verify(String shareLink, String password) {
        if (StrUtil.isBlank(shareLink) || StrUtil.isBlank(password)) {
            throw new IllegalArgumentException("shareUrl 和 password 不能为空！");
        }
        // 短链
        String surl = buildShortUrl(shareLink);
        HttpResponse httpResponse = postExecuteResponse(StrUtil.format(UrlConstants.POST_SHARE_FILE_VERIFY_URL, surl, new Date().getTime()), new HashMap<String, Object>() {{
            put("pwd", password);
            put("vcode", "");
            put("vcode_str", "");
        }});
        String bdclndCookieStr = httpResponse.getCookieValue("BDCLND");
        if (StrUtil.isBlank(bdclndCookieStr)) {
            log.error("分享地址校验失败！shareUrl:{}, password:{}", surl, password);
            throw new RuntimeException("分享地址校验失败！");
        }
        return new KeyValue("BDCLND", bdclndCookieStr);
    }

    public BaiduNetDiskWebShareFileDetailResponse getDetail(String shareLink, KeyValue verifyCookie) {
        if (StrUtil.isBlank(shareLink) || verifyCookie == null || StrUtil.isBlank(verifyCookie.getValue()) || StrUtil.isBlank(verifyCookie.getKey())) {
            throw new IllegalArgumentException("shareUrl 和 verifyCookie 不能为空！");
        }
        String shortUrl = buildShortUrl(shareLink);
        String url = StrUtil.format(UrlConstants.GET_SHARE_FILE_PAGE_TEMPLATE_URL, shortUrl);
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(url).addCookie(verifyCookie.getKey(), verifyCookie.getValue());
        String body = executeResponse(httpRequest).getBody();
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


    /**
     * 根据分享id获取分享中的子集文件信息
     *
     * @param params 查询参数
     * @return 返回子文件信息
     * @author weiba
     * @date 2024/3/20 16:59
     */
    public BaiduNetDiskWebQueryShareFileResponse getDetailChild(BaiduNetDiskWebQueryShareFileParams params) {
        Assert.notNull(params, "参数不能为空！");
        Assert.notNull(params.getShareId(), "分享id 不能为空！");
        Assert.notNull(params.getShareUk(), "分享者id 不能为空！");
        Assert.notNull(params.getVerifyCookie(), "verifyCookie 不能为空！");
        Assert.notNull(params.getVerifyCookie().getKey(), "verifyCookie.key 不能为空！");
        Assert.notNull(params.getVerifyCookie().getValue(), "verifyCookie.value 不能为空！");
        Assert.notNull(params.getDir(), "dir 不能为空！");

        if (params.getOrderType() == null) params.setOrderType(QueryShareOrderType.DEFULLT);
        if (params.getSortType() == null) params.setSortType(SortType.DEFULLT);
        if (params.getPageNo() == null) params.setPageNo(1);
        if (params.getPageSize() == null) params.setPageSize(100);

        KeyValue verifyCookie = params.getVerifyCookie();
        String url = StrUtil.format(UrlConstants.GET_QUERY_SHARE_FILES_URL,
                RandomUtil.randomDouble(0, 1),
                params.getShareId(), params.getShareUk(), params.getDir(), params.getOrderType().getValue(), params.getSortType().getValue(), params.getPageNo(), params.getPageSize());
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(url).addCookie(verifyCookie.getKey(), verifyCookie.getValue());

        return execute(httpRequest,
                new TypeReference<BaiduNetDiskWebQueryShareFileResponse>() {
                });
    }

    private String buildShortUrl(String shareLink) {
        String surl = shareLink;
        if (surl.startsWith(UrlConstants.SHARE_PREFIX)) {
            surl = surl.substring(UrlConstants.SHARE_PREFIX.length());
        } else if (surl.startsWith("1")) { // 短链 没有1， 1是固定模板
            surl = surl.substring(1);
        }
        return surl;
    }

    public BaiduNetDiskWebShareFileTransferResponse shareFileTransferExecute(String shareId, String shareUk, String myUk, KeyValue verifyCookie, String dstDirPath, List<String> fsIds) {
        if (CollUtil.isEmpty(fsIds)) {
            log.error("shareFileTransfer 失败! 分享文件不存在！");
            throw new IllegalArgumentException("shareFileTransfer 失败! 分享文件不存在！");
        }

        if (shareUk.equals(myUk)) {
            log.error("shareFileTransfer 失败! 不能转存自己的文件！");
            throw new IllegalArgumentException("shareFileTransfer 失败! 不能转存自己的文件！");
        }
        String url = StrUtil.format(UrlConstants.POST_SHARE_FILE_TRANSFER_URL, shareId, shareUk, verifyCookie.getValue());
        HttpRequest httpRequest = HttpRequest.urlFormatBuilder(Method.POST, url).requestParams(new HashMap<String, Object>() {{
            put("fsidlist", StrUtil.format("[{}]", StrUtil.join(",", fsIds)));
            put("path", dstDirPath);
        }}).addCookie(verifyCookie.getKey(), verifyCookie.getValue());
        // url中会传入bdstoken
        return execute(httpRequest, new TypeReference<BaiduNetDiskWebShareFileTransferResponse>() {
        });
    }
}
