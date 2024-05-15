package pp.weiba.service.download.client.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * @author xiaoweiba1028@gmail.com
 * @description 返回错误信息
 * @date 2023/5/31 23:32
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
@SuperBuilder
public class Aria2ErrorResponse {


    private String id;
    private String jsonrpc;
    private ErrorResponse error;

    @NoArgsConstructor
        @AllArgsConstructor
        @Data
    public static class ErrorResponse {
        private int code;
        private String message;
    }

}
