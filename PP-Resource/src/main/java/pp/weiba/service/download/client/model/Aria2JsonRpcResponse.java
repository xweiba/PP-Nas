package pp.weiba.service.download.client.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * @author xiaoweiba1028@gmail.com
 * @description Aria2 Json Rpc Response
 * @date 2023/5/29 0:41
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
@SuperBuilder
public class Aria2JsonRpcResponse<T> {


    @JSONField(name = "id")
    private String id;
    @JSONField(name = "jsonrpc")
    private String jsonrpc;
    @JSONField(name = "result")
    private T result;

}
