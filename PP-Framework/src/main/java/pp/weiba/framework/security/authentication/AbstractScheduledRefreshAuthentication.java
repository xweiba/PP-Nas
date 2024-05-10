package pp.weiba.framework.security.authentication;
import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.security.authentication.credential.ICredential;
import pp.weiba.utils.IAbstractScheduled;

/**
* 支持定时刷新的认证管理器
*
* @author weiba
* @date 2024/5/9 11:23
*/
@Log4j2
public abstract class AbstractScheduledRefreshAuthentication<T> extends AbstractAuthentication<T> implements IAbstractScheduled {

    private String scheduledId;

    public AbstractScheduledRefreshAuthentication(String authenticationId, String authenticationType, ICredential<T> credential) {
        super(authenticationId, authenticationType, credential);
    }

    /**
     * 定时刷新认证信息时执行
     *
     * @author weiba
     * @date 2024/5/9 11:29
     */
    protected void scheduledRefreshAuthenticationRun() {
        refreshAuthenticationToManager();
    };

    @Override
    protected void completeAuthenticationInformation() {
        super.completeAuthenticationInformation();
        refreshAuthentication();
    }

    @Override
    public void logout() {
        super.logout();
        scheduledCancel(scheduledBusinessId());
    }


    private void refreshAuthentication() {
        this.scheduledRefreshToken();
    }

    protected void scheduledRefreshToken() {
        this.scheduledId = scheduledStart();
    }

    @Override
    public void scheduledRun() {
        scheduledRefreshAuthenticationRun();
    }

    @Override
    public String scheduledBusinessType() {
        return "refresh_authentication_" + authenticationType;
    }

    @Override
    public String scheduledBusinessId() {
        return authenticationId;
    }
}
