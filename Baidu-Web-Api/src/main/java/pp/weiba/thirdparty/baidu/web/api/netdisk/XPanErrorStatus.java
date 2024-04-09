package pp.weiba.thirdparty.baidu.web.api.netdisk;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 百度网盘 开放平台 Response StatusCode
 *
 * @author weiba
 * @date 2024/3/7 13:43
 */
public class XPanErrorStatus {
    private static final Map<Integer, String> statusCodeMap;

    static {
        statusCodeMap = new HashMap<>();
        statusCodeMap.put(-1, "权益已过期");
        statusCodeMap.put(-3, "文件不存在");
        statusCodeMap.put(-6, "身份验证失败 1.access_token 是否有效;\n\n2.授权是否成功；\n\n3.参考接入授权FAQ；\n\n4.阅读文档《使用入门->接入授权》章节。");
        statusCodeMap.put(-7, "文件或目录名错误或无权访问");
        statusCodeMap.put(-8, "文件或目录已存在");
        statusCodeMap.put(-9, "文件或目录不存在");
        statusCodeMap.put(0, "请求成功");
        statusCodeMap.put(2, "参数错误 1.检查必选参数是否都已填写；\n\n2.检查参数位置，有的参数是在url里，有的是在body里；\n\n3.检查每个参数的值是否正确");
        statusCodeMap.put(6, "不允许接入用户数据");
        statusCodeMap.put(10, "转存文件已经存在");
        statusCodeMap.put(11, "用户不存在(uid不存在)");
        statusCodeMap.put(12, "批量转存出错");
        statusCodeMap.put(111, "有其他异步任务正在执行");
        statusCodeMap.put(133, "播放广告");
        statusCodeMap.put(255, "转存数量太多");
        statusCodeMap.put(2131, "该分享不存在");
        statusCodeMap.put(31023, "参数错误 1.检查必选参数是否都已填写；\n\n2.检查参数位置，有的参数是在url里，有的是在body里；\n\n3.检查每个参数的值是否正确");
        statusCodeMap.put(31024, "没有访问权限");
        statusCodeMap.put(31034, "命中接口频控");
        // 下载大于20MB的文件时，可能会报这个错误
        statusCodeMap.put(31045, "access_token验证未通过");
        statusCodeMap.put(31061, "文件已存在");
        statusCodeMap.put(31062, "文件名无效");
        statusCodeMap.put(31064, "上传路径错误");
        statusCodeMap.put(31066, "文件名不存在");
        statusCodeMap.put(31190, "文件不存在 1.block_list参数是否正确；\n\n2.一般是分片上传阶段有问题；\n\n3.检查分片上传阶段，分片传完了么；\n\n4.size大小对不对，跟实际文件是否一致，跟预上传接口的size是否一致；\n\n5.对照文档好好检查一下每一步相关的参数及值是否正确。");
        statusCodeMap.put(31299, "第一个分片的大小小于4MB");
        statusCodeMap.put(31301, "非音视频文件");
        statusCodeMap.put(31304, "视频格式不支持播放");
        statusCodeMap.put(31326, "命中防盗链");
        statusCodeMap.put(31338, "当前视频码率太高暂不支持流畅播放");
        statusCodeMap.put(31339, "非法媒体文件");
        statusCodeMap.put(31341, "视频正在转码");
        statusCodeMap.put(31346, "视频转码失败");
        statusCodeMap.put(31347, "当前视频太长，暂不支持在线播放");
        statusCodeMap.put(31355, "参数异常");
        statusCodeMap.put(31360, "url过期");
        statusCodeMap.put(31362, "签名错误");
        statusCodeMap.put(31363, "分片缺失 1.分片是否全部上传；每个上传的分片是否正确；\n\n2.size大小是否正确，跟实际文件是否一致，跟预上传接口的size是否一致；\n\n3.对照文档好好检查一下每一步相关的参数及值是否正确");
        statusCodeMap.put(31364, "超出分片大小限制");
        statusCodeMap.put(31365, "文件总大小超限");
        statusCodeMap.put(31649, "字幕不存在");
        statusCodeMap.put(42202, "文件个数超过相册容量上限");
        statusCodeMap.put(42203, "相册不存在");
        statusCodeMap.put(42210, "部分文件添加失败");
        statusCodeMap.put(42211, "获取图片分辨率失败");
        statusCodeMap.put(42212, "共享目录文件上传者信息查询失败");
        statusCodeMap.put(42213, "共享目录鉴权失败");
        statusCodeMap.put(42214, "获取文件详情失败");
        statusCodeMap.put(42905, "查询用户名失败");
        statusCodeMap.put(50002, "播单id不存在");
    }

    public static String getMessage(int statusCode) {
        String msg = statusCodeMap.get(statusCode);
        if (StrUtil.isBlank(msg)) {
            msg = "未知状态码：" + statusCode;
        }
        return msg;
    }
}
