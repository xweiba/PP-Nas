package pp.weiba.framework.test;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * 测试统一配置类
 *
 * @author xiaoweiba1028@gmail.com
 * @date 2023/5/16 2:02
 */
@Log4j2
public class DefaultTest {

    static {
        Configurator.setAllLevels("", Level.INFO);
        Configurator.setAllLevels("", Level.DEBUG);
    }

}
