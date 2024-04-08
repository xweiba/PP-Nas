package pp.weiba.framework.resource;

/**
 * 分享管理接口
 *
 * @author weiba
 * @date 2024/3/19 16:14
 */
public interface IShareOperation {

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
     * 获取分享信息
     *
     * @param shareKey 分享键
     * @param password 分享密码
     * @return 分享信息
     * @author weiba
     * @date 2024/4/8 14:24
     */
    ShareInfo get(String shareKey, String password);


    /**
     * 保存分享
     *
     * @param shareKey 分享键
     * @param password 分享密码
     * @param saveKey  保存键
     * @author weiba
     * @date 2024/4/8 14:26
     */
    void save(String shareKey, String password, String saveKey);




}
