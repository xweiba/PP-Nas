package pp.weiba.service.download.client.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author xiaoweiba1028@gmail.com
 * @description 下载中返回信息
 * @date 2023/6/1 0:20
 */
@NoArgsConstructor
@Accessors(chain = true)
@Data
@SuperBuilder
public class Aria2QueryStatusResponse {

    private Long completedLength;
    private String connections;
    private String downloadSpeed;
    private List<FilesResponse> files;
    private String gid;
    private String status;
    private Long totalLength;
    private String uploadSpeed;

    @NoArgsConstructor
    @Data
    public static class FilesResponse {
        private String completedLength;
        private String index;
        private String length;
        private String path;
        private String selected;
        private List<UrisResponse> uris;

        @NoArgsConstructor
        @Data
        public static class UrisResponse {
            private String status;
            private String uri;
        }
    }
}
