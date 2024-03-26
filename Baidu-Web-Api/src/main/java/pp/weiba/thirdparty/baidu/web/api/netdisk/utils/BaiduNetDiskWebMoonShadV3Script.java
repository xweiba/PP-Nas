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
public class BaiduNetDiskWebMoonShadV3Script {

    private static final String SCRIPT_LIB = "Moonshad";

    private static final String BAIDU_NETDISC_SCRIPT_LIB_DIR = "js/lib/baidu";
    private static final Map<String, List<String>> methodParamNameMap = new HashMap<>();
    private static CompiledScript lib;
    private static ThreadLocal<ScriptContext> context;

    static {
        try {
            lib = ScriptCompiledUtils.getLibCompiledScript(SCRIPT_LIB, BAIDU_NETDISC_SCRIPT_LIB_DIR);
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
        String scriptKey = SCRIPT_LIB + "." + methodName;

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

    public static int init(String... params) {
        try {
            return runScript(params);
        } catch (Exception e) {
            log.error("OOOO00 error! exception: {}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("OOOO00 error");
        }
    }

    public static String moonshad5moonsh2(String... params) {
        try {
            return runScript(params);
        } catch (Exception e) {
            log.error("OOOO00 error! exception: {}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("OOOO00 error");
        }
    }

    public static String OOO00O(String... params) {
        try {
            return runScript(params);
        } catch (Exception e) {
            log.error("OOO00O error! exception: {}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("OOO00O error");
        }
    }

    public static String OOO000(String... params) {
        try {
            return runScript(params);
        } catch (Exception e) {
            log.error("OOO000 error! exception: {}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("OOO000 error");
        }
    }

    public static String OOO0OO(String... params) {
        try {
            return runScript(params);
        } catch (Exception e) {
            log.error("OOO0OO error! exception: {}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("OOO0OO error");
        }
    }

    public static String O0OOO0(String... params) {
        try {
            return runScript(params);
        } catch (Exception e) {
            log.error("O0OOO0 error! exception: {}", ExceptionUtil.getMessage(e));
            throw new RuntimeException("O0OOO0 error");
        }
    }

}
