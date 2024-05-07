package pp.weiba.thirdparty.aliyun.web.client.authentication.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 二维码生成
 *
 * @author weiba
 * @date 2024/5/7 14:43
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenerateQrCodeResponse {

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
             * t
             */
            @JSONField(name = "t")
            private Long t;
            /**
             * codeContent
             */
            @JSONField(name = "codeContent")
            private String codeContent;
            /**
             * ck
             */
            @JSONField(name = "ck")
            private String ck;
            /**
             * resultCode
             */
            @JSONField(name = "resultCode")
            private Integer resultCode;
            /**
             * processFinished
             */
            @JSONField(name = "processFinished")
            private Boolean processFinished;
        }
    }
}
