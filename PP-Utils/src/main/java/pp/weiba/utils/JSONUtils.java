package pp.weiba.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/10/10
 **/

@Log4j2
public class JSONUtils {
    public static SerializeConfig config = new SerializeConfig();
    static {
        config.put(Date.class, DateFixDeserializer.instance);
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
        return JSON.toJSONString(result, config, SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue,
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

    private static class DateFixDeserializer implements ObjectSerializer {
        public static final DateFixDeserializer instance = new DateFixDeserializer();

        private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
            if (object == null) {
                serializer.writeNull();
                return;
            }

            Date date = (Date) object;
            // 毫秒时间戳长度应为13
            long time = MathUtils.padZero(date.getTime(), 13);
            // 转为可读的时间格式
            serializer.write(FORMAT.format(new Date(time)));
        }
    }

    public static Supplier<?> formatJson(String format, Object... args) {
        return formatJson(format, false, args);
    }

    public static Supplier<?> formatJson(String format, boolean newline, Object... args) {
        return () -> {
            Object[] argsTemp = ArrayUtils.argsToJsonStr(newline, args);
            if (argsTemp != null && argsTemp.length > 0) {
                return StrUtil.format(format, argsTemp);
            }
            return format;
        };
    }
}
