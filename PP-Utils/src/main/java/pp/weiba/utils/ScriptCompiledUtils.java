package pp.weiba.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import javax.script.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/9/22
 **/

public class ScriptCompiledUtils {

    public static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    private static final ConcurrentHashMap<String, CompiledScript> scripts = new ConcurrentHashMap<>();

    /**
     * 装载js库文件，返回编译后的js库对象。
     *
     * @param libName    : libName 库名称
     * @param libDirPath : libDirPath 库所在的文件夹路径
     * @return javax.script.CompiledScript
     * @author xiaoweiba1028@gmail.com
     * @date 2022/9/22 13:31
     */
    public static CompiledScript getLibCompiledScript(String libName, String libDirPath) throws ScriptException {
        String key = libDirPath + "/" + libName;
        return getCompiledScriptByPath(key, key + ".js");
    }

    public static CompiledScript getCompiledScriptByPath(String key, String scriptFilePath) throws ScriptException {
        if (!FileUtil.isFile(scriptFilePath)) {
            throw new ScriptException("JavaScript File empty");
        }
        return getCompiledScript(scriptFilePath, FileUtil.readString(scriptFilePath, StandardCharsets.UTF_8));
    }

    /**
     * 通过key或script 获取编译后的 js 对象
     *
     * @param key    : key
     * @param script : script
     * @return javax.script.CompiledScript
     * @author xiaoweiba1028@gmail.com
     * @date 2022/9/22 13:33
     */
    public static CompiledScript getCompiledScript(String key) throws ScriptException {
        return getCompiledScript(key, null);
    }

    public static CompiledScript getCompiledScript(String key, String script) throws ScriptException {

        if (StrUtil.isBlank(key)) {
            throw new ScriptException("key 为空");
        }

        CompiledScript compiledScript = scripts.get(key);
        if (compiledScript == null && StrUtil.isNotBlank(script)) {
            Compilable compilable = (Compilable) engine;
            compiledScript = compilable.compile(script);
            scripts.put(key, compiledScript);
        }
        return compiledScript;
    }
}
