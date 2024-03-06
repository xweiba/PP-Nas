package pp.weiba.thirdparty.baidu.web.client.filter;

import lombok.extern.log4j.Log4j2;
import org.asynchttpclient.Request;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.filter.FilterContext;
import org.asynchttpclient.filter.FilterException;
import org.asynchttpclient.filter.RequestFilter;

/**
 * 代理处理类
 *
 * @author weiba
 * @date 2024/3/6 17:32
 */
@Log4j2
public class ProxyServerRequestFilter implements RequestFilter {

    @Override
    public <T> FilterContext<T> filter(FilterContext<T> filterContext) throws FilterException {
        // 取出代理字段，proxyType， 根据proxyType 修改代理服务器
        Request request = filterContext.getRequest();
        RequestBuilder builder = new RequestBuilder(request);
        request = builder.build();
        return new FilterContext.FilterContextBuilder<>(filterContext).request(request).build();
    }
}
