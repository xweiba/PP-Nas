package pp.weiba.thirdparty.baidu.web.client.security.authentication.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录后返回数据
 *
 * @author weiba
 * @date 2024/3/27 10:47
 */
@NoArgsConstructor
@Data
public class LoginResponse {

    @JSONField(name = "traceid")
    private String traceid;
    @JSONField(name = "code")
    private String code;
    @JSONField(name = "errInfo")
    private ErrInfoDTO errInfo;
    @JSONField(name = "data")
    private DataDTO data;
    @JSONField(name = "message")
    private String message;

    @NoArgsConstructor
        @AllArgsConstructor
        @Data
    public static class ErrInfoDTO {
        @JSONField(name = "msg")
        private String msg;
        @JSONField(name = "no")
        private String no;
    }

    @NoArgsConstructor
        @AllArgsConstructor
        @Data
    public static class DataDTO {
        @JSONField(name = "u")
        private String u;
        @JSONField(name = "session")
        private SessionDTO session;
        @JSONField(name = "hao123Param")
        private String hao123Param;
        @JSONField(name = "bdusssign")
        private String bdusssign;
        @JSONField(name = "authtoken")
        private String authtoken;
        @JSONField(name = "userName")
        private String userName;
        @JSONField(name = "user")
        private UserDTO user;

        @NoArgsConstructor
        @Data
        public static class SessionDTO {
            @JSONField(name = "actionType")
            private String actionType;
            @JSONField(name = "authsid")
            private String authsid;
            @JSONField(name = "stoken")
            private String stoken;
            @JSONField(name = "canshare")
            private String canshare;
            @JSONField(name = "bduss")
            private String bduss;
            @JSONField(name = "needvcode")
            private String needvcode;
            @JSONField(name = "ptoken")
            private String ptoken;
            @JSONField(name = "version")
            private String version;
            @JSONField(name = "ubi")
            private String ubi;
            @JSONField(name = "stokenList")
            private String stokenList;
        }

        @NoArgsConstructor
        @Data
        public static class UserDTO {
            @JSONField(name = "livinguname")
            private String livinguname;
            @JSONField(name = "weakpass")
            private String weakpass;
            @JSONField(name = "portraitUrl")
            private String portraitUrl;
            @JSONField(name = "displayName")
            private String displayName;
            @JSONField(name = "portraitSign")
            private String portraitSign;
            @JSONField(name = "userId")
            private String userId;
            @JSONField(name = "username")
            private String username;
        }
    }
}
