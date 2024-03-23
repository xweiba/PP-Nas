package pp.weiba.thirdparty.baidu.web.api.netdisk.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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

    private Result result;

    @Accessors(chain = true)
    @Data
    public static class Result {
        private String bdstoken;
        private String token;
        private Integer uk;
        private Integer isdocuser;
        private Integer servertime;
    }

}
