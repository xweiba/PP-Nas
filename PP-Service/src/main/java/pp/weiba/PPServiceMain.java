package pp.weiba;
import lombok.extern.log4j.Log4j2;
import org.noear.solon.Solon;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.solon.web.webdav.FileSystem;
import org.noear.solon.web.webdav.WebdavAbstractHandler;
import org.noear.solon.web.webdav.impl.LocalFileSystem;

/**
* 
*
* @author weiba
* @date 2024/5/27 10:51
*/
@Log4j2
public class PPServiceMain {

    public static void main(String[] args) {


        FileSystem fileSystem = new LocalFileSystem("C:\\Users\\admin\\Documents\\Tools");

        Handler handler = new WebdavAbstractHandler(true) {
            @Override
            public String user(Context ctx) {
                return "admin";
            }

            @Override
            public FileSystem fileSystem() {
                return fileSystem;
            }

            @Override
            public String prefix() {
                return "/webdav";
            }
        };
        Solon.start(PPServiceMain.class, args, app -> {
            app.http("/webdav/*", handler);
        });
    }
    
}
