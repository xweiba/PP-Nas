package pp.weiba.framework.http;

public interface IHttpClient {

    void setProxy(String proxy);

    void setTimeout(int timeout);

    void setConnectionTimeout(int timeout);

    void execute();

}
