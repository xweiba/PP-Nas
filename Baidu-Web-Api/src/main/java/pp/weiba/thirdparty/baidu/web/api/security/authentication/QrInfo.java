package pp.weiba.thirdparty.baidu.web.api.security.authentication;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 二维码信息
 *
 * @author weiba
 * @date 2024/3/26 10:44
 */
@Data
@Accessors(chain = true)
public class QrInfo {

    private String imgurl;

    private String sign;

    private int errno;

    private String prompt;

}
