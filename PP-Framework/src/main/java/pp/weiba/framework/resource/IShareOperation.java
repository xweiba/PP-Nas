package pp.weiba.framework.resource;

import pp.weiba.framework.KeyValue;
import pp.weiba.framework.resource.model.*;

import java.util.List;
import java.util.Map;

/**
 * 分享管理接口
 *
 * @author weiba
 * @date 2024/3/19 16:14
 */
public interface IShareOperation {

    /**
     * 查询我分享的列表
     *
     * @param orderType 排序类型
     * @param desc      排序方式
     * @param pageSize  页大小
     * @param pageNo    页
     * @return 返回我分享的信息
     * @author weiba
     * @date 2024/4/9 9:30
     */
    List<ShareInfo> query(OrderType orderType, SortType desc, Integer pageSize, Integer pageNo);


    /**
     * 查询我分享的密码
     *
     * @param shareId 分享id
     * @return 密码
     * @author weiba
     * @date 2024/4/9 10:02
     */
    String getSharePassword(String shareId);



    /**
     * 创建分享
     *
     * @param ids       资源id集
     * @param password  分享密码
     * @param expireDay 分享有效天
     * @return 返回分享信息
     * @author weiba
     * @date 2024/4/8 14:21
     */
    ShareInfo create(String ids, String password, Integer expireDay);

    /**
     * 取消分享
     *
     * @param shareIds 分享id集
     * @return 执行状态
     * @author weiba
     * @date 2024/4/8 14:30
     */
    boolean cancel(String shareIds);

    /**
     * 验证分享密码，获取对应凭证
     *
     * @param shareLink 分享地址
     * @param password 分享密码
     * @return 认证信息, key: type, value: 对应值
     * @author weiba
     * @date 2024/4/9 10:30
     */
    Map<String, List<KeyValue>> verify(String shareLink, String password);

    /**
     * 获取分享信息详细信息
     *
     * @param shareLink 分享地址
     * @param verifyMap 认证信息
     * @return 分享详细信息
     * @author weiba
     * @date 2024/4/8 14:24
     */
    ShareDetailInfo getDetail(String shareLink, Map<String, List<KeyValue>> verifyMap);


    List<ResourceInfo> getDetailChild(ShareQueryParams params);

    /**
     * 保存分享
     *
     * @param saveShareParams 保存信息
     * @author weiba
     * @date 2024/4/8 14:26
     */
    boolean save(SaveShareParams saveShareParams);

}
