package pp.weiba.restful.core;
import lombok.extern.log4j.Log4j2;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;
import org.smartboot.http.common.utils.StringUtils;
import pp.weiba.framework.KeyValue;
import pp.weiba.framework.utils.UserInfoUtils;

/**
* 
*
* @author weiba
* @date 2024/5/29 16:31
*/
@Log4j2
@Component(index = 0)
public class RequestUserInfoFilter implements Filter {

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        String userId = ctx.param("userId");
        if (StringUtils.isBlank(userId)) {
            throw new IllegalArgumentException("userId 不能为空");
        }
        UserInfoUtils.setCurrentThreadUserInfo(new KeyValue("user", userId));
        chain.doFilter(ctx);
    }
}
