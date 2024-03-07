package pp.weiba.thirdparty.baidu.web.client;

import lombok.extern.log4j.Log4j2;

/**
 * Api 客户端抽象类
 *
 * @author weiba
 * @date 2024/3/7 9:51
 */
@Log4j2
public abstract class AbstractApiClient {

    protected final IHttpClient httpClient;

    public AbstractApiClient(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }

}
