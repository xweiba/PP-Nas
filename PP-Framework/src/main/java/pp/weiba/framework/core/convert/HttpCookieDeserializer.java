package pp.weiba.framework.core.convert;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;

public class HttpCookieDeserializer implements ObjectDeserializer {
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        //实现对HttpCookie反序列化的逻辑
        //此处需要有你的反序列化逻辑

        Map<String, HttpCookie> cookieMap = new HashMap<>();
        JSONObject jsonObject = parser.parseObject();
        for (Map.Entry<String, Object> item : jsonObject.entrySet()) {
            JSONObject itemTemp = (JSONObject) item.getValue();
            HttpCookie httpCookie = new HttpCookie(item.getKey(), itemTemp.getString("value"));
            httpCookie.setDiscard(itemTemp.getBoolean("discard"));
            httpCookie.setPath(itemTemp.getString("path"));
            httpCookie.setMaxAge(itemTemp.getInteger("maxAge"));
            httpCookie.setDomain(itemTemp.getString("domain"));
            httpCookie.setDomain(itemTemp.getString("domain"));
            httpCookie.setHttpOnly(itemTemp.getBoolean("httpOnly"));
            httpCookie.setSecure(itemTemp.getBoolean("secure"));
            httpCookie.setVersion(itemTemp.getInteger("version"));
            cookieMap.put(item.getKey(), httpCookie);
        }
        return (T) cookieMap;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
