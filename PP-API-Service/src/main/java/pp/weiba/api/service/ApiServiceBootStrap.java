package pp.weiba.api.service;

import org.noear.solon.Solon;
import org.noear.solon.annotation.SolonMain;

@SolonMain
public class ApiServiceBootStrap {
    public static void main(String[] args) {
        Solon.start(ApiServiceBootStrap.class, args);
    }
}