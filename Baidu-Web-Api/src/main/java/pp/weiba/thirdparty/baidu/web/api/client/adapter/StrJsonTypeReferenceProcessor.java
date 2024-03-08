package pp.weiba.thirdparty.baidu.web.api.client.adapter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import pp.weiba.thirdparty.baidu.web.api.client.ITypeReferenceProcessor;
import pp.weiba.thirdparty.baidu.web.api.client.TypeReference;

/**
 * Json 类型转换器
 *
 * @author weiba
 * @date 2024/3/7 14:39
 */
@Log4j2
public class StrJsonTypeReferenceProcessor implements ITypeReferenceProcessor<String> {

    @Override
    public <T> T process(String data, TypeReference<T> typeReference) {
        if (StrUtil.isNotBlank(data)) {
            return JSON.parseObject(data, typeReference.getType());
        }
        return null;
    }
}