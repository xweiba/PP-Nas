package pp.weiba.thirdparty.aliyun.web.client.authentication.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 二维码扫描登录检测
 *
 * @author weiba
 * @date 2024/5/7 15:03
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QrLonginCheckResponse {

    /**
     * content
     */
    @JSONField(name = "content")
    private ContentResponse content;
    /**
     * hasError
     */
    @JSONField(name = "hasError")
    private Boolean hasError;

    /**
     * ContentResponse
     */
    @NoArgsConstructor
        @AllArgsConstructor
        @Data
    public static class ContentResponse {
        /**
         * data
         */
        @JSONField(name = "data")
        private DataResponse data;
        /**
         * status
         */
        @JSONField(name = "status")
        private Integer status;
        /**
         * success
         */
        @JSONField(name = "success")
        private Boolean success;

        /**
         * DataResponse
         */
        @NoArgsConstructor
        @Data
        public static class DataResponse {
            /**
             * loginResult
             */
            @JSONField(name = "loginResult")
            private String loginResult;
            /**
             * loginSucResultAction
             */
            @JSONField(name = "loginSucResultAction")
            private String loginSucResultAction;
            /**
             * st
             */
            @JSONField(name = "st")
            private String st;
            /**
             * qrCodeStatus
             */
            @JSONField(name = "qrCodeStatus")
            private String qrCodeStatus;
            /**
             * loginType
             */
            @JSONField(name = "loginType")
            private String loginType;
            /**
             * bizExt
             */
            @JSONField(name = "bizExt")
            private String bizExt;
            /**
             * loginScene
             */
            @JSONField(name = "loginScene")
            private String loginScene;
            /**
             * resultCode
             */
            @JSONField(name = "resultCode")
            private Integer resultCode;
            /**
             * appEntrance
             */
            @JSONField(name = "appEntrance")
            private String appEntrance;
            /**
             * smartlock
             */
            @JSONField(name = "smartlock")
            private Boolean smartlock;
            /**
             * processFinished
             */
            @JSONField(name = "processFinished")
            private Boolean processFinished;
        }
    }
}
