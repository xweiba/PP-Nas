package pp.weiba.thirdparty.aliyun.web.client.authentication.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author weiba
 * @date 2024/5/7 13:57
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class LonginMainConfig {

    /**
     * agreeDialog
     */
    @JSONField(name = "agreeDialog")
    private Boolean agreeDialog;
    /**
     * api
     */
    @JSONField(name = "api")
    private ApiResponse api;
    /**
     * autoSendSmsCode
     */
    @JSONField(name = "autoSendSmsCode")
    private Boolean autoSendSmsCode;
    /**
     * checkedProtocol
     */
    @JSONField(name = "checkedProtocol")
    private Boolean checkedProtocol;
    /**
     * defaultView
     */
    @JSONField(name = "defaultView")
    private String defaultView;
    /**
     * errorTipPosition
     */
    @JSONField(name = "errorTipPosition")
    private String errorTipPosition;
    /**
     * inputDeleteEnable
     */
    @JSONField(name = "inputDeleteEnable")
    private Boolean inputDeleteEnable;
    /**
     * labelType
     */
    @JSONField(name = "labelType")
    private String labelType;
    /**
     * loginTypes
     */
    @JSONField(name = "loginTypes")
    private List<LoginTypesResponse> loginTypes;
    /**
     * machineVerifyType
     */
    @JSONField(name = "machineVerifyType")
    private String machineVerifyType;
    /**
     * newSimLogin
     */
    @JSONField(name = "newSimLogin")
    private Boolean newSimLogin;
    /**
     * openCountryCodeSearch
     */
    @JSONField(name = "openCountryCodeSearch")
    private Boolean openCountryCodeSearch;
    /**
     * openPreCheckParam
     */
    @JSONField(name = "openPreCheckParam")
    private Boolean openPreCheckParam;
    /**
     * pwaEffect
     */
    @JSONField(name = "pwaEffect")
    private Boolean pwaEffect;
    /**
     * recommendLatestLoginType
     */
    @JSONField(name = "recommendLatestLoginType")
    private Boolean recommendLatestLoginType;
    /**
     * regStyle
     */
    @JSONField(name = "regStyle")
    private String regStyle;
    /**
     * registerAgreementCheck
     */
    @JSONField(name = "registerAgreementCheck")
    private Boolean registerAgreementCheck;
    /**
     * rsaExponent
     */
    @JSONField(name = "rsaExponent")
    private String rsaExponent;
    /**
     * rsaModulus
     */
    @JSONField(name = "rsaModulus")
    private String rsaModulus;
    /**
     * showAgreeToast
     */
    @JSONField(name = "showAgreeToast")
    private Boolean showAgreeToast;
    /**
     * smsCodeLength
     */
    @JSONField(name = "smsCodeLength")
    private Integer smsCodeLength;
    /**
     * smsInputBlurCheckMobileFormat
     */
    @JSONField(name = "smsInputBlurCheckMobileFormat")
    private Boolean smsInputBlurCheckMobileFormat;
    /**
     * smsSendBtnEnableCheck
     */
    @JSONField(name = "smsSendBtnEnableCheck")
    private Boolean smsSendBtnEnableCheck;
    /**
     * stopQrcodeWhenLoggingIn
     */
    @JSONField(name = "stopQrcodeWhenLoggingIn")
    private Boolean stopQrcodeWhenLoggingIn;
    /**
     * toastErrorStyle
     */
    @JSONField(name = "toastErrorStyle")
    private Boolean toastErrorStyle;
    /**
     * toastKeepTime
     */
    @JSONField(name = "toastKeepTime")
    private Integer toastKeepTime;
    /**
     * tryAppNativeLogin
     */
    @JSONField(name = "tryAppNativeLogin")
    private Boolean tryAppNativeLogin;
    /**
     * useBaxiaNc
     */
    @JSONField(name = "useBaxiaNc")
    private Boolean useBaxiaNc;
    /**
     * useFeedbackTip
     */
    @JSONField(name = "useFeedbackTip")
    private Boolean useFeedbackTip;
    /**
     * viewCfg
     */
    @JSONField(name = "viewCfg")
    private ViewCfgResponse viewCfg;
    /**
     * viewStyle
     */
    @JSONField(name = "viewStyle")
    private String viewStyle;

    /**
     * ApiResponse
     */
    @NoArgsConstructor
        @AllArgsConstructor
        @Data
    public static class ApiResponse {
        /**
         * smsLoginApi
         */
        @JSONField(name = "smsLoginApi")
        private String smsLoginApi;
        /**
         * loginApi
         */
        @JSONField(name = "loginApi")
        private String loginApi;
        /**
         * simLoginApi
         */
        @JSONField(name = "simLoginApi")
        private String simLoginApi;
        /**
         * preCheckApi
         */
        @JSONField(name = "preCheckApi")
        private String preCheckApi;
        /**
         * checkLoginApi
         */
        @JSONField(name = "checkLoginApi")
        private String checkLoginApi;
        /**
         * smsLoginRegApi
         */
        @JSONField(name = "smsLoginRegApi")
        private String smsLoginRegApi;
        /**
         * aKeyCheckApi
         */
        @JSONField(name = "aKeyCheckApi")
        private String aKeyCheckApi;
        /**
         * tokenContinueLoginApi
         */
        @JSONField(name = "tokenContinueLoginApi")
        private String tokenContinueLoginApi;
        /**
         * getQRCodeApi
         */
        @JSONField(name = "getQRCodeApi")
        private String getQRCodeApi;
        /**
         * emailLoginApi
         */
        @JSONField(name = "emailLoginApi")
        private String emailLoginApi;
        /**
         * conLoginApi
         */
        @JSONField(name = "conLoginApi")
        private String conLoginApi;
        /**
         * sendSmsApi
         */
        @JSONField(name = "sendSmsApi")
        private String sendSmsApi;
        /**
         * hasLoginApi
         */
        @JSONField(name = "hasLoginApi")
        private String hasLoginApi;
        /**
         * sendEmailApi
         */
        @JSONField(name = "sendEmailApi")
        private String sendEmailApi;
        /**
         * recommendLoginFlowApi
         */
        @JSONField(name = "recommendLoginFlowApi")
        private String recommendLoginFlowApi;
        /**
         * checkQRCodeApi
         */
        @JSONField(name = "checkQRCodeApi")
        private String checkQRCodeApi;
        /**
         * accountCheckApi
         */
        @JSONField(name = "accountCheckApi")
        private String accountCheckApi;
        /**
         * aKeyPushApi
         */
        @JSONField(name = "aKeyPushApi")
        private String aKeyPushApi;
    }

    /**
     * ViewCfgResponse
     */
    @NoArgsConstructor
        @AllArgsConstructor
        @Data
    public static class ViewCfgResponse {
        /**
         * password
         */
        @JSONField(name = "password")
        private PasswordResponse password;
        /**
         * qrcode
         */
        @JSONField(name = "qrcode")
        private QrcodeResponse qrcode;

        /**
         * PasswordResponse
         */
        @NoArgsConstructor
        @Data
        public static class PasswordResponse {
            /**
             * moduleCfg
             */
            @JSONField(name = "moduleCfg")
            private ModuleCfgResponse moduleCfg;
            /**
             * showMobileLogin
             */
            @JSONField(name = "showMobileLogin")
            private Boolean showMobileLogin;

            /**
             * ModuleCfgResponse
             */
            @NoArgsConstructor
            @Data
            public static class ModuleCfgResponse {
                /**
                 * blockCfg
                 */
                @JSONField(name = "blockCfg")
                private BlockCfgResponse blockCfg;
                /**
                 * blockClassMapping
                 */
                @JSONField(name = "blockClassMapping")
                private BlockClassMappingResponse blockClassMapping;
                /**
                 * blockLayout
                 */
                @JSONField(name = "blockLayout")
                private BlockLayoutResponse blockLayout;

                /**
                 * BlockCfgResponse
                 */
                @NoArgsConstructor
                @Data
                public static class BlockCfgResponse {
                    /**
                     * blankLink
                     */
                    @JSONField(name = "blankLink")
                    private BlankLinkResponse blankLink;
                    /**
                     * qrLoginLink
                     */
                    @JSONField(name = "qrLoginLink")
                    private QrLoginLinkResponse qrLoginLink;
                    /**
                     * title
                     */
                    @JSONField(name = "title")
                    private TitleResponse title;

                    /**
                     * BlankLinkResponse
                     */
                    @NoArgsConstructor
                    @Data
                    public static class BlankLinkResponse {
                        /**
                         * href
                         */
                        @JSONField(name = "href")
                        private String href;
                        /**
                         * text
                         */
                        @JSONField(name = "text")
                        private String text;
                        /**
                         * type
                         */
                        @JSONField(name = "type")
                        private String type;
                    }

                    /**
                     * QrLoginLinkResponse
                     */
                    @NoArgsConstructor
                    @Data
                    public static class QrLoginLinkResponse {
                        /**
                         * className
                         */
                        @JSONField(name = "className")
                        private String className;
                        /**
                         * href
                         */
                        @JSONField(name = "href")
                        private String href;
                        /**
                         * onClick
                         */
                        @JSONField(name = "onClick")
                        private String onClick;
                        /**
                         * text
                         */
                        @JSONField(name = "text")
                        private String text;
                        /**
                         * type
                         */
                        @JSONField(name = "type")
                        private String type;
                    }

                    /**
                     * TitleResponse
                     */
                    @NoArgsConstructor
                    @Data
                    public static class TitleResponse {
                        /**
                         * className
                         */
                        @JSONField(name = "className")
                        private String className;
                        /**
                         * text
                         */
                        @JSONField(name = "text")
                        private String text;
                        /**
                         * type
                         */
                        @JSONField(name = "type")
                        private String type;
                    }
                }

                /**
                 * BlockClassMappingResponse
                 */
                @NoArgsConstructor
                @Data
                public static class BlockClassMappingResponse {
                    /**
                     * block10
                     */
                    @JSONField(name = "block10")
                    private String block10;
                    /**
                     * block7
                     */
                    @JSONField(name = "block7")
                    private String block7;
                    /**
                     * block8
                     */
                    @JSONField(name = "block8")
                    private String block8;
                    /**
                     * block9
                     */
                    @JSONField(name = "block9")
                    private String block9;
                }

                /**
                 * BlockLayoutResponse
                 */
                @NoArgsConstructor
                @Data
                public static class BlockLayoutResponse {
                    /**
                     * block0
                     */
                    @JSONField(name = "block0")
                    private List<String> block0;
                    /**
                     * block7
                     */
                    @JSONField(name = "block7")
                    private List<String> block7;
                    /**
                     * block8
                     */
                    @JSONField(name = "block8")
                    private List<?> block8;
                }
            }
        }

        /**
         * QrcodeResponse
         */
        @NoArgsConstructor
        @Data
        public static class QrcodeResponse {
            /**
             * interval
             */
            @JSONField(name = "interval")
            private Integer interval;
            /**
             * moduleCfg
             */
            @JSONField(name = "moduleCfg")
            private ModuleCfgResponse moduleCfg;
            /**
             * size
             */
            @JSONField(name = "size")
            private Integer size;
            /**
             * useImgQrCode
             */
            @JSONField(name = "useImgQrCode")
            private Boolean useImgQrCode;

            /**
             * ModuleCfgResponse
             */
            @NoArgsConstructor
            @Data
            public static class ModuleCfgResponse {
                /**
                 * blockCfg
                 */
                @JSONField(name = "blockCfg")
                private BlockCfgResponse blockCfg;
                /**
                 * blockClassMapping
                 */
                @JSONField(name = "blockClassMapping")
                private BlockClassMappingResponse blockClassMapping;
                /**
                 * blockLayout
                 */
                @JSONField(name = "blockLayout")
                private BlockLayoutResponse blockLayout;

                /**
                 * BlockCfgResponse
                 */
                @NoArgsConstructor
                @Data
                public static class BlockCfgResponse {
                    /**
                     * passportLoginLink
                     */
                    @JSONField(name = "passportLoginLink")
                    private PassportLoginLinkResponse passportLoginLink;
                    /**
                     * title
                     */
                    @JSONField(name = "title")
                    private TitleResponse title;

                    /**
                     * PassportLoginLinkResponse
                     */
                    @NoArgsConstructor
                    @Data
                    public static class PassportLoginLinkResponse {
                        /**
                         * className
                         */
                        @JSONField(name = "className")
                        private String className;
                        /**
                         * href
                         */
                        @JSONField(name = "href")
                        private String href;
                        /**
                         * onClick
                         */
                        @JSONField(name = "onClick")
                        private String onClick;
                        /**
                         * text
                         */
                        @JSONField(name = "text")
                        private String text;
                        /**
                         * type
                         */
                        @JSONField(name = "type")
                        private String type;
                    }

                    /**
                     * TitleResponse
                     */
                    @NoArgsConstructor
                    @Data
                    public static class TitleResponse {
                        /**
                         * className
                         */
                        @JSONField(name = "className")
                        private String className;
                        /**
                         * text
                         */
                        @JSONField(name = "text")
                        private String text;
                        /**
                         * type
                         */
                        @JSONField(name = "type")
                        private String type;
                    }
                }

                /**
                 * BlockClassMappingResponse
                 */
                @NoArgsConstructor
                @Data
                public static class BlockClassMappingResponse {
                }

                /**
                 * BlockLayoutResponse
                 */
                @NoArgsConstructor
                @Data
                public static class BlockLayoutResponse {
                    /**
                     * block0
                     */
                    @JSONField(name = "block0")
                    private List<String> block0;
                }
            }
        }
    }

    /**
     * LoginTypesResponse
     */
    @NoArgsConstructor
        @AllArgsConstructor
        @Data
    public static class LoginTypesResponse {
        /**
         * title
         */
        @JSONField(name = "title")
        private String title;
        /**
         * type
         */
        @JSONField(name = "type")
        private String type;
    }
}
