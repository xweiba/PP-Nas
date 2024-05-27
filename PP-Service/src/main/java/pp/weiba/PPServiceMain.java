package pp.weiba;
import lombok.extern.log4j.Log4j2;
import org.noear.solon.Solon;

/**
* 
*
* @author weiba
* @date 2024/5/27 10:51
*/
@Log4j2
public class PPServiceMain {

    public static void main(String[] args) {
        Solon.start(PPServiceMain.class, args);
    }
    
}
