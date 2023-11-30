package pp.weiba.framework.security;

public interface IAuthenticate {

    void login(IAuthenticateHandel authenticateHandel);

    void getAuthenticateInfo();

    void logout();

}
