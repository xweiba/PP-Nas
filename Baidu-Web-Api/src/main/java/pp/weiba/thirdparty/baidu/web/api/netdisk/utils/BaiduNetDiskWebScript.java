package pp.weiba.thirdparty.baidu.web.api.netdisk.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import pp.weiba.utils.ScriptCompiledUtils;

import javax.script.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 百度网盘 js 脚本
 *
 * @author weiba
 * @date 2024/3/20 9:54
 */
@Log4j2
public class BaiduNetDiskWebScript {

    private static final String BAIDU_NETDISC_SCRIPT_LIB = "BaiduNetdisc";

    private static final String BAIDU_NETDISC_SCRIPT_LIB_DIR = "js/lib/baidu";
    private static final Map<String, List<String>> methodParamNameMap = new HashMap<>();
    private static CompiledScript lib;
    private static ThreadLocal<ScriptContext> context;

    static {
        try {
            lib = ScriptCompiledUtils.getLibCompiledScript(BAIDU_NETDISC_SCRIPT_LIB, BAIDU_NETDISC_SCRIPT_LIB_DIR);
            context = ThreadLocal.withInitial(() -> initContext(lib));
        } catch (Exception e) {
            log.error("BaiduNetdiscScript init error, exception: {}", ExceptionUtil.getMessage(e));
        }
    }

    private static ScriptContext initContext(CompiledScript lib) {
        ScriptContext context = new SimpleScriptContext();
        try {
            lib.eval(context);
        } catch (ScriptException e) {
            log.error("js 上下文初始化异常：{}", ExceptionUtil.getMessage(e));
        }
        return context;
    }

    private static <T> T runScript(String[] params) throws ScriptException {

        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        String scriptKey = BAIDU_NETDISC_SCRIPT_LIB + "." + methodName;

        CompiledScript script = ScriptCompiledUtils.getCompiledScript(scriptKey);

        List<String> methodParams = methodParamNameMap.get(scriptKey);
        if (CollUtil.isEmpty(methodParams)) {
            methodParams = new ArrayList<>();
            for (int i = 0; i < params.length; i++) {
                methodParams.add(String.valueOf((char) (97 + i)));
            }
            methodParamNameMap.put(scriptKey, methodParams);
        }
        if (script == null) {
            String ScriptStr = scriptKey + "(" + StrUtil.join(",", methodParams) + ")";
            script = ScriptCompiledUtils.getCompiledScript(scriptKey, ScriptStr);
        }

        if (script != null) {
            ScriptContext scriptContext = context.get();
            if (CollUtil.isNotEmpty(methodParams)) {
                Bindings bindings = scriptContext.getBindings(ScriptContext.ENGINE_SCOPE);
                for (int i = 0; i < methodParams.size(); i++) {
                    bindings.put(methodParams.get(i), params[i]);
                }
            }
            return ((T) script.eval(scriptContext));
        }

        return null;
    }

    /**
     * encryptMd5 百度文件MD5加密方法，与js中保持一致即可。
     *
     * @param params : params 参数列表
     * @return java.lang.String
     * @author xiaoweiba1028@gmail.com
     * @date 2022/9/22 13:29
     */
    public static String encryptMd5(String... params) {
        try {
            return runScript(params);
        } catch (Exception e) {
            log.error("encryptMd5 error! exception: {}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("encryptMd5 error");
        }
    }

    public static String batchFileSignParams(String... params) {
        try {
            return runScript(params);
        } catch (Exception e) {
            log.error("getDownloadSign error! exception: {}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("getDownloadSign error");
        }
    }

    public static String getLogId(String... params) {
        try {
            return runScript(params);
        } catch (Exception e) {
            log.error("getLogId error! exception: {}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("getLogId error");
        }
    }

    public static Double getDataOffset(String... params) {
        try {
            return runScript(params);
        } catch (Exception e) {
            log.error("getDataOffset error! exception: {}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("getDataOffset error");
        }
    }

    public static String makeMD5(String... params) {
        try {
            return runScript(params);
        } catch (Exception e) {
            log.error("makeMD5 error! exception: {}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("makeMD5 error");
        }
    }

    public static String loginGid(String... params) {
        try {
            return runScript(params);
        } catch (Exception e) {
            log.error("guideRandom error! exception: {}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("guideRandom error");
        }
    }

    public static String loginTraceId(String... params) {
        try {
            return runScript(params);
        } catch (Exception e) {
            log.error("loginTraceId error! exception: {}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("loginTraceId error");
        }
    }

    public static String getUniqueId(String... params) {
        try {
            return runScript(params);
        } catch (Exception e) {
            log.error("loginTraceId error! exception: {}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("loginTraceId error");
        }
    }


}
