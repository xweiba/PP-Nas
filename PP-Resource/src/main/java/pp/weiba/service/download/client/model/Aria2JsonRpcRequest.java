package pp.weiba.service.download.client.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.apache.logging.log4j.util.Base64Util;

import java.util.*;

/**
 * @author xiaoweiba1028@gmail.com
 * @description Aria2请求信息
 * @date 2023/5/29 0:03
 */
@Accessors(chain = true)
@Data
@SuperBuilder
public class Aria2JsonRpcRequest {

    /* 协议版本 */
    @Builder.Default
    private String jsonrpc = "2.0";

    /* 请求类型 */
    private Aria2Method method;

    /* 随机id */
    @Builder.Default
    private String id = String.valueOf(new Date().getTime());

    /* 业务参数 */
    @Builder.Default
    private List<Object> params = new ArrayList<>();

    public static final List<String> unmodifiableLQueryListParams = Collections.unmodifiableList(Arrays.asList(
            "gid",
            "totalLength",
            "completedLength",
            "uploadSpeed",
            "downloadSpeed",
            "connections",
            "numSeeders",
            "seeder",
            "status",
            "errorCode",
            "verifiedLength",
            "verifyIntegrityPending",
            "files",
            "bittorrent",
            "infoHash"));

    public Aria2JsonRpcRequest addDefaultParams(boolean addDefaultParams) {
        if (addDefaultParams) this.params.add(Aria2JsonRpcRequest.unmodifiableLQueryListParams);
        return this;
    }

    public Aria2JsonRpcRequest initParams(String token) {
        this.params.add("token:" + token);
        return this;
    }

    public Aria2JsonRpcRequest addParams(List<Object> params) {
        this.params.addAll(params);
        return this;
    }

    public Aria2JsonRpcRequest addParam(Object param) {
        this.params.add(param);
        return this;
    }

}
