package pp.weiba.thirdparty.baidu.web.resource.netdisk;

import org.junit.jupiter.api.Test;
import pp.weiba.framework.resource.IResourceOperation;
import pp.weiba.framework.resource.ResourceInfo;
import pp.weiba.thirdparty.baidu.web.api.netdisk.FileOperationApiClient;
import pp.weiba.thirdparty.baidu.web.resource.security.authentication.WebAuthenticationTest;

class ResourceOperationServiceTest extends WebAuthenticationTest {

    IResourceOperation resourceOperation = new ResourceOperationService(new FileOperationApiClient(httpClient));

    @Test
    void createDir() {
        ResourceInfo dir = resourceOperation.createDir("/test/319");
        System.out.println(dir.toString());
    }

    @Test
    void get() {

    }

    @Test
    void delete() {
    }

    @Test
    void rename() {
    }

    @Test
    void move() {
    }

    @Test
    void copy() {
    }
}
