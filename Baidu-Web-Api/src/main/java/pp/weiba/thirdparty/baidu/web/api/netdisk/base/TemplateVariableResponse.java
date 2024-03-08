package pp.weiba.thirdparty.baidu.web.api.netdisk.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import pp.weiba.thirdparty.baidu.web.api.netdisk.ApiResponse;

/**
 * 页面基础信息
 *
 * @author weiba
 * @date 2024/3/7 17:02
 */
@Accessors(chain = true)
@Getter
@Setter
public class TemplateVariableResponse extends ApiResponse {

    private ResultBO result;

    @Accessors(chain = true)
    @Getter
    @Setter
    public static class ResultBO {
        private String bdstoken;
        private String token;
        private Integer uk;
        private Integer isdocuser;
        private Integer servertime;
    }

}
