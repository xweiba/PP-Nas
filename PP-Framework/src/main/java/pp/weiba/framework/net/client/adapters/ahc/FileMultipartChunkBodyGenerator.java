package pp.weiba.framework.net.client.adapters.ahc;

import lombok.extern.log4j.Log4j2;
import org.asynchttpclient.request.body.Body;
import org.asynchttpclient.request.body.generator.BodyGenerator;
import org.asynchttpclient.request.body.multipart.Part;

import java.util.List;

/**
 * @author weiba
 * @date 2024/3/22 14:16
 */
@Log4j2
public class FileMultipartChunkBodyGenerator implements BodyGenerator {


    private final List<Part> bodyParts;

    private final MultipartFormData multipartFormData;

    public FileMultipartChunkBodyGenerator(List<Part> bodyParts, MultipartFormData multipartFormData) {
        this.bodyParts = bodyParts;
        this.multipartFormData = multipartFormData;
    }

    @Override
    public Body createBody() {
        return CustomizeMultipartUtils.newMultipartBody(bodyParts, multipartFormData);
    }
}
