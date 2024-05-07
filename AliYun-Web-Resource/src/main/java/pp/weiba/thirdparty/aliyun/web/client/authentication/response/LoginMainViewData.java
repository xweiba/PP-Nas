package pp.weiba.thirdparty.aliyun.web.client.authentication.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * 登录初始化页面属性
 *
 * @author weiba
 * @date 2024/5/6 14:23
 */
@NoArgsConstructor
@Log4j2
@Data
@Accessors(chain = true)
public class LoginMainViewData {


    /**
     * appEntrance
     */
    @JSONField(name = "appEntrance")
    private String appEntrance;
    /**
     * appName
     */
    @JSONField(name = "appName")
    private String appName;
    /**
     * awscCdn
     */
    @JSONField(name = "awscCdn")
    private String awscCdn;
    /**
     * baxiaLang
     */
    @JSONField(name = "baxiaLang")
    private String baxiaLang;
    /**
     * countryAreaConfig
     */
    @JSONField(name = "countryAreaConfig")
    private CountryAreaConfigResponse countryAreaConfig;
    /**
     * currentTime
     */
    @JSONField(name = "currentTime")
    private String currentTime;
    /**
     * enableSmsAudio
     */
    @JSONField(name = "enableSmsAudio")
    private Boolean enableSmsAudio;
    /**
     * foreign
     */
    @JSONField(name = "foreign")
    private Boolean foreign;
    /**
     * isMobile
     */
    @JSONField(name = "isMobile")
    private Boolean isMobile;
    /**
     * lang
     */
    @JSONField(name = "lang")
    private String lang;
    /**
     * loginFormData
     */
    @JSONField(name = "loginFormData")
    private LoginFormDataResponse loginFormData;
    /**
     * mobile
     */
    @JSONField(name = "mobile")
    private Boolean mobile;
    /**
     * nocaptchaAppKey
     */
    @JSONField(name = "nocaptchaAppKey")
    private String nocaptchaAppKey;
    /**
     * officialAccountsSnapshotuser
     */
    @JSONField(name = "officialAccountsSnapshotuser")
    private Boolean officialAccountsSnapshotuser;
    /**
     * returnUrl
     */
    @JSONField(name = "returnUrl")
    private String returnUrl;
    /**
     * showAutioSlipCode
     */
    @JSONField(name = "showAutioSlipCode")
    private Boolean showAutioSlipCode;
    /**
     * trySilentHasLogin
     */
    @JSONField(name = "trySilentHasLogin")
    private Boolean trySilentHasLogin;
    /**
     * umidEncryptAppName
     */
    @JSONField(name = "umidEncryptAppName")
    private String umidEncryptAppName;
    /**
     * umidServer
     */
    @JSONField(name = "umidServer")
    private String umidServer;
    /**
     * umidServiceLocation
     */
    @JSONField(name = "umidServiceLocation")
    private String umidServiceLocation;
    /**
     * umidToken
     */
    @JSONField(name = "umidToken")
    private String umidToken;

    /**
     * CountryAreaConfigResponse
     */
    @NoArgsConstructor
    @Data
    public static class CountryAreaConfigResponse {
        /**
         * countryList
         */
        @JSONField(name = "countryList")
        private List<CountryListResponse> countryList;
        /**
         * defaultCountryCode
         */
        @JSONField(name = "defaultCountryCode")
        private String defaultCountryCode;

        /**
         * CountryListResponse
         */
        @NoArgsConstructor
        @Data
        public static class CountryListResponse {
            /**
             * areaName
             */
            @JSONField(name = "areaName")
            private String areaName;
            /**
             * checkKey
             */
            @JSONField(name = "checkKey")
            private String checkKey;
            /**
             * countryCode
             */
            @JSONField(name = "countryCode")
            private String countryCode;
            /**
             * phoneCode
             */
            @JSONField(name = "phoneCode")
            private String phoneCode;
        }
    }

    /**
     * LoginFormDataResponse
     */
    @NoArgsConstructor
    @Data
    public static class LoginFormDataResponse {
        /**
         * appName
         */
        @JSONField(name = "appName")
        private String appName;
        /**
         * appEntrance
         */
        @JSONField(name = "appEntrance")
        private String appEntrance;
        /**
         * csrfToken
         */
        @JSONField(name = "_csrf_token")
        private String csrfToken;
        /**
         * umidToken
         */
        @JSONField(name = "umidToken")
        private String umidToken;
        /**
         * hsiz
         */
        @JSONField(name = "hsiz")
        private String hsiz;
        /**
         * bizParams
         */
        @JSONField(name = "bizParams")
        private String bizParams;
        /**
         * mainPage
         */
        @JSONField(name = "mainPage")
        private Boolean mainPage;
        /**
         * isMobile
         */
        @JSONField(name = "isMobile")
        private Boolean isMobile;
        /**
         * lang
         */
        @JSONField(name = "lang")
        private String lang;
        /**
         * returnUrl
         */
        @JSONField(name = "returnUrl")
        private String returnUrl;
        /**
         * fromSite
         */
        @JSONField(name = "fromSite")
        private Integer fromSite;
    }
}
