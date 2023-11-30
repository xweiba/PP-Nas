package pp.weiba.resource.file.network.disk.baidu;

import pp.weiba.framework.http.IHttpClient;
import pp.weiba.framework.resource.IResourceManager;
import pp.weiba.framework.resource.model.ResourceCapacity;

public class BaiduNetDiskResourceManager implements IResourceManager {

    private final IHttpClient baiduHttpClient;

    public BaiduNetDiskResourceManager(IHttpClient baiduHttpClient) {
        this.baiduHttpClient = baiduHttpClient;
    }

    @Override
    public ResourceCapacity getCapacity() {
        return null;
    }

    @Override
    public void add() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void query() {

    }

}
