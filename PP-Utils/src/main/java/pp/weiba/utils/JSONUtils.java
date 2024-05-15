package pp.weiba.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
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
    static {
        SerializeConfig serializeConfig = SerializeConfig.global;
        serializeConfig.put(Date.class, DateFixSerializer.instance);
        ParserConfig parserConfig = ParserConfig.global;
        parserConfig.putDeserializer(Date.class, DateFixDeserializer.instance);
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
        return toBean(body, typeReference.getType(), msg);
    }

    public static <T> T toBean(String body, Type type) {
        return toBean(body, type, null);
    }

    public static <T> T toBean(String body, Type type, String msg) {
        T result = JSON.parseObject(body, type);
        printLog(result, msg);
        return result;
    }

    private static void printLog(Object result, String msg) {
        LogUtils.debug(log, "result: {}\nmsg:{}", result, StringUtils.isBlank(msg) ? "" : msg);
    }

    public static String toJsonPrettyStr(Object result) {
        if (isStrJSONValid(result)) {
            result = JSON.parseObject((String) result);
        }
        // SerializerFeature.WriteEnumUsingToString 将Enum类型使用toString()方法，默认使用name()方法
        return JSON.toJSONString(result, SerializerFeature.WriteEnumUsingToString, SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteDateUseDateFormat);
    }

    public static boolean isStrJSONValid(Object test) {
        if (!(test instanceof String) || StringUtils.isBlank(test.toString())) {
            return false;
        }

        String checkStr = (String) test;

        // 去除字符串前后的空白字符
        checkStr = checkStr.trim();

        // 检查字符串是否以左花括号或左方括号开头，以右花括号或右方括号结尾
        return (checkStr.startsWith("{") && checkStr.endsWith("}")) || (checkStr.startsWith("[") && checkStr.endsWith("]"));
    }

    public static JSONObject toJSONObj(String text) {
        return JSON.parseObject(text);
    }

    public static String toJsonStr(Object obj) {
        return toJsonStr(obj, null);
    }

    public static String toJsonStr(Object obj, String msg) {
        if (isStrJSONValid(obj)) {
            return (String)obj;
        }
        String result = JSON.toJSONString(obj);
        printLog(result, msg);
        return result;
    }

    private static class DateFixSerializer implements ObjectSerializer {
        public static final DateFixSerializer instance = new DateFixSerializer();

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

    private static class DateFixDeserializer implements ObjectDeserializer {
        public static final DateFixDeserializer instance = new DateFixDeserializer();

        @Override
        public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
            // 从parser中获取日期字符串
            String value = parser.parseObject(String.class);
            long srcTime = Long.parseLong(value);
            long newTime = MathUtils.padZero(srcTime, 13);
            // 自定义解析日期字符串为Date对象
            return (T) new Date(newTime);
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
