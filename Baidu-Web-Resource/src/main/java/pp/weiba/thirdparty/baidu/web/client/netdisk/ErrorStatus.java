package pp.weiba.thirdparty.baidu.web.client.netdisk;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 百度网盘 Response StatusCode
 *
 * @author weiba
 * @date 2024/3/7 13:43
 */
public class ErrorStatus {
    private static final Map<Integer, String> statusCodeMap;

    static {
        statusCodeMap = new HashMap<>();
        // web端获取 20230520
        statusCodeMap.put(-1, "由于您分享了违反相关法律法规的文件，分享功能已被禁用，之前分享出去的文件不受影响。");
        statusCodeMap.put(-2, "用户不存在,请刷新页面后重试");
        statusCodeMap.put(-3, "文件不存在,请刷新页面后重试");
        statusCodeMap.put(-4, "登录信息有误，请重新登录试试");
        statusCodeMap.put(-5, "host_key和user_key无效");
        statusCodeMap.put(-6, "请重新登录");
        statusCodeMap.put(-7, "该分享已删除或已取消");
        statusCodeMap.put(-8, "该分享已经过期");
        statusCodeMap.put(-9, "访问密码错误");
        statusCodeMap.put(-10, "分享外链已经达到最大上限100000条，不能再次分享");
        statusCodeMap.put(-11, "验证cookie无效");
        statusCodeMap.put(-14, "对不起，短信分享每天限制20条，你今天已经分享完，请明天再来分享吧！");
        statusCodeMap.put(-15, "对不起，邮件分享每天限制20封，你今天已经分享完，请明天再来分享吧！");
        statusCodeMap.put(-16, "对不起，该文件已经限制分享！");
        statusCodeMap.put(-17, "文件分享超过限制");
        statusCodeMap.put(-19, "验证码输入错误，请重试");
        statusCodeMap.put(-20, "请求验证码失败，请重试");
        statusCodeMap.put(-21, "未绑定手机或邮箱，没有权限私密分享");
        statusCodeMap.put(-22, "被分享的文件无法重命名，移动等操作");
        statusCodeMap.put(-30, "文件已存在");
        statusCodeMap.put(-31, "文件保存失败");
        statusCodeMap.put(-32, "你的空间不足了哟，赶紧购买空间吧");
        statusCodeMap.put(-33, "一次支持操作999个，减点试试吧");
        statusCodeMap.put(-40, "热门推荐失败");
        statusCodeMap.put(-60, "相关推荐数据异常");
        statusCodeMap.put(-62, "密码输入次数达到上限");
        statusCodeMap.put(-64, "描述包含敏感词");
        statusCodeMap.put(-70, "你分享的文件中包含病毒或疑似病毒，为了你和他人的数据安全，换个文件分享吧");
        // web端获取 20230520

        statusCodeMap.put(0, "成功");
        statusCodeMap.put(1, "服务器错误");
        statusCodeMap.put(2, "参数错误");
        statusCodeMap.put(3, "未登录或帐号无效");
        statusCodeMap.put(4, "存储好像出问题了，请稍后再试");
        statusCodeMap.put(12, "批量处理错误");
        statusCodeMap.put(14, "网络错误，请稍候重试");
        statusCodeMap.put(15, "操作失败，请稍候重试");
        statusCodeMap.put(16, "网络错误，请稍候重试");
        statusCodeMap.put(105, "创建链接失败，请重试");
        statusCodeMap.put(106, "文件读取失败，请刷新页面后重试");
        statusCodeMap.put(108, "文件名有敏感词，优化一下吧");
        statusCodeMap.put(110, "分享次数超出限制，可以到“我的分享”中查看已分享的文件链接");
        statusCodeMap.put(112, "页面已过期，请刷新后重试");
        statusCodeMap.put(113, "外链签名有误");
        statusCodeMap.put(114, "当前任务不存在，保存失败");
        statusCodeMap.put(115, "该文件禁止分享");
        statusCodeMap.put(2126, "文件名中含有敏感词");
        statusCodeMap.put(2161, "文件中含有违规内容");
        statusCodeMap.put(2162, "对方加你为好友之后才能发送");
        statusCodeMap.put(2135, "对方拒绝接收消息");
        statusCodeMap.put(2136, "对方拒接非好友消息");
        statusCodeMap.put(2102, "群组不存在");
        statusCodeMap.put(2103, "你已退出该群");
        statusCodeMap.put(2101, "你已达到创建2000群上限");
        statusCodeMap.put(2100, "用户都已经被添加过");
        statusCodeMap.put(2119, "群成员已满");
        statusCodeMap.put(9100, "你的帐号存在违规行为，已被冻结，查看详情");
        statusCodeMap.put(9200, "你的帐号存在违规行为，已被冻结，查看详情");
        statusCodeMap.put(9300, "你的帐号存在违规行为，该功能暂被冻结，查看详情");
        statusCodeMap.put(9400, "你的帐号异常，需验证后才能使用该功能，立即验证");
        statusCodeMap.put(9500, "你的帐号存在安全风险，已进入保护模式，请修改密码后使用");
        statusCodeMap.put(31090, "打包文件过大");
    }

    public static String getMessage(int statusCode) {
        String msg = statusCodeMap.get(statusCode);
        if (StrUtil.isBlank(msg)) {
            msg = "未知状态码：" + statusCode;
        }
        return msg;
    }
}
