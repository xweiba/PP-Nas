package pp.weiba.framework.download.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import pp.weiba.framework.net.client.model.AuthInfo;

/**
 * 下载认证信息
 *
 * @author weiba
 * @date 2024/4/11 10:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class DownloadAuthInfo extends AuthInfo<DownloadAuthInfo> {

}
