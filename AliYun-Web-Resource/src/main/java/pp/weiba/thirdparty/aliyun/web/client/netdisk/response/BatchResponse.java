package pp.weiba.thirdparty.aliyun.web.client.netdisk.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/*
删除：
{"responses":[{"id":"663db23c8caba03781a540b9b0848e1434db38be","status":204}]}
* */

/**
* 批处理响应
*
* @author weiba
* @date 2024/5/10 13:41
*/
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BatchResponse {


    /**
     * responses
     */
    @JSONField(name = "responses")
    private List<ResponsesResponse> responses;

    /**
     * ResponsesResponse
     */
    @NoArgsConstructor
    @Data
    public static class ResponsesResponse {
        /**
         * id
         */
        @JSONField(name = "id")
        private String id;
        /**
         * status
         */
        @JSONField(name = "status")
        private Integer status;
    }
}
