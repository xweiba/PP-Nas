package pp.weiba.thirdparty.baidu.web.client.netdisk.utils;

import cn.hutool.core.util.RandomUtil;

public class BaiduWebApiUtils {

    public static String getDpLogId() {
        return RandomUtil.randomNumbers(20);
    }


}
