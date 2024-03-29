package pp.weiba.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/10/10
 **/

@Log4j2
public class JSONUtils {

    static {
        ParserConfig globalInstance = ParserConfig.getGlobalInstance();
        globalInstance.putDeserializer(Date.class, new BaiduNetDiskDateDeserializer());

    }

    public static <T> T toBean(String body, Class<T> tClass) {
        return toBean(body, tClass, null);
    }

    public static <T> T toBean(String body, Class<T> tClass, String msg) {
        T result = JSON.parseObject(body, tClass);
        printLog(result, msg);
        return result;
    }

    public static <T> T toBean(String body, TypeReference<T> typeReference) {
        return toBean(body, typeReference, null);
    }

    public static <T> T toBean(String body, TypeReference<T> typeReference, String msg) {
        T result = JSON.parseObject(body, typeReference.getType());
        printLog(result, msg);
        return result;
    }

    private static void printLog(Object result, String msg) {
        String stackMethodName = StackTraceUtils.getStackMethodName();
        if (stackMethodName.startsWith("AbstractLogger.")) return;
        Log.debug(log, "result: {}\nmsg:{}", result, StringUtils.isBlank(msg) ? "" : msg);
    }

    public static String toJsonPrettyStr(Object result) {
        return JSON.toJSONString(result, SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
    }

    public static JSONObject toJSONObj(String text) {
        return JSON.parseObject(text);
    }

    public static String toJsonStr(Object obj) {
        return toJsonStr(obj, null);
    }

    public static String toJsonStr(Object obj, String msg) {
        String result = JSON.toJSONString(obj);
        printLog(result, msg);
        return result;
    }

    private static class BaiduNetDiskDateDeserializer implements ObjectDeserializer {
        @Override
        public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
            Long value = parser.parseObject(Long.class);
            if (value <= 9999999999L) {// 百度网盘时间都是秒，补成毫秒
                Date dateTime = new Date(value * 1000);
                return (T) dateTime;
            }
            return null;
        }
    }
}
