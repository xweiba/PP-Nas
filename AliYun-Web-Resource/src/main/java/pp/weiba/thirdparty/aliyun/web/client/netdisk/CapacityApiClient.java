package pp.weiba.thirdparty.aliyun.web.client.netdisk;

import lombok.extern.log4j.Log4j2;
import pp.weiba.framework.core.convert.TypeReference;
import pp.weiba.framework.net.client.AbstractApiHttpClient;
import pp.weiba.framework.net.client.IHttpClient;
import pp.weiba.thirdparty.aliyun.web.client.UrlConstants;
import pp.weiba.thirdparty.aliyun.web.client.netdisk.response.PersonalInfoResponse;

import java.util.HashMap;

/**
 * 基础信息
 *
 * @author weiba
 * @date 2024/3/7 9:50
 */
@Log4j2
public class CapacityApiClient extends AbstractApiHttpClient {

    public CapacityApiClient(IHttpClient httpClient) {
        super(httpClient);
    }

    /**
     * 获取网盘容量信息
     *
     * @return 容量信息
     * @author weiba
     * @date 2024/3/7 14:57
     */
    public PersonalInfoResponse getPersonalInfo() {
        return postExecute(UrlConstants.POST_PERSONAL_INFO_URL, new HashMap<>(), new TypeReference<PersonalInfoResponse>() {
        });
    }

}
