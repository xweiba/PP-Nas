package pp.weiba.restful;
import lombok.extern.log4j.Log4j2;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;

/**
* 配置类
*
* @author weiba
* @date 2024/5/27 10:54
*/
@Log4j2
@Controller
@Configuration
public class ConfigContorller {

    @Mapping("/hello")
    public String hello(){
        return "Hello world!";
    }


    
}
