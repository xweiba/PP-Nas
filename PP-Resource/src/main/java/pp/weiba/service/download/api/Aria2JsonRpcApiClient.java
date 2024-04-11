package pp.weiba.service.download.api;

import lombok.extern.log4j.Log4j2;

/**
 * Aria2 Api 客户端
 *
 * @author weiba
 * @date 2024/4/11 13:48
 */
@Log4j2
public class Aria2JsonRpcApiClient {

    private final String jsonRpcUrl;

    private final String token;

    public Aria2JsonRpcApiClient(String jsonRpcUrl, String token) {
        this.jsonRpcUrl = jsonRpcUrl;
        this.token = token;
    }
    
}
