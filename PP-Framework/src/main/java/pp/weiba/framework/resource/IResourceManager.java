package pp.weiba.framework.resource;

import pp.weiba.framework.resource.model.ResourceCapacity;

public interface IResourceManager {

    ResourceCapacity getCapacity();

    void add();

    void delete();

    void edit();

    void query();

}
