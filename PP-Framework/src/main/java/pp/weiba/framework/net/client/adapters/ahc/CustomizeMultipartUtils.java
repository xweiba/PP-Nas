package pp.weiba.framework.net.client.adapters.ahc;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import org.asynchttpclient.request.body.multipart.*;
import org.asynchttpclient.request.body.multipart.part.*;
import org.asynchttpclient.util.Assertions;
import org.asynchttpclient.util.HttpUtils;
import org.asynchttpclient.util.MiscUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weiba
 * @date 2024/3/22 14:23
 */
public class CustomizeMultipartUtils extends MultipartUtils {

    public static MultipartBody newMultipartBody(List<Part> parts, MultipartFormData multipartFormData) {
        Assertions.assertNotNull(parts, "parts");

        String contentType = multipartFormData.getContentType();
        byte[] boundary = multipartFormData.getBoundary();

        List<MultipartPart<? extends Part>> multipartParts = generateMultipartParts(parts, boundary);
        return new MultipartBody(multipartParts, contentType, boundary);
    }

    public static MultipartFormData buildMultipartFormData(HttpHeaders requestHeaders) {
        String contentTypeHeader = requestHeaders.get(HttpHeaderNames.CONTENT_TYPE);
        byte[] boundary;
        String contentType;
        if (MiscUtils.isNonEmpty(contentTypeHeader)) {
            int boundaryLocation = contentTypeHeader.indexOf("boundary=");
            if (boundaryLocation != -1) {
                contentType = contentTypeHeader;
                boundary = contentTypeHeader.substring(boundaryLocation + "boundary=".length()).trim().getBytes(StandardCharsets.US_ASCII);
            } else {
                boundary = HttpUtils.computeMultipartBoundary();
                contentType = HttpUtils.patchContentTypeWithBoundaryAttribute(contentTypeHeader, boundary);
            }
        } else {
            boundary = HttpUtils.computeMultipartBoundary();
            contentType = HttpUtils.patchContentTypeWithBoundaryAttribute(HttpHeaderValues.MULTIPART_FORM_DATA, boundary);
        }
        return new MultipartFormData(boundary, contentType);
    }

    public static List<MultipartPart<? extends Part>> generateMultipartParts(List<Part> parts, byte[] boundary) {
        List<MultipartPart<? extends Part>> multipartParts = new ArrayList<>(parts.size());

        for (Part part : parts) {
            if (part instanceof FileChunkPart) {
                FileChunkPart fileChunkPart = (FileChunkPart) part;
                if (fileChunkPart.getChunk() == null) {
                    multipartParts.add(new FileMultipartPart(fileChunkPart, boundary));
                } else {
                    multipartParts.add(new FileMultipartChunkPart(fileChunkPart, boundary));
                }
            } else if (part instanceof FilePart) {
                multipartParts.add(new FileMultipartPart((FilePart) part, boundary));
            } else if (part instanceof ByteArrayPart) {
                multipartParts.add(new ByteArrayMultipartPart((ByteArrayPart) part, boundary));
            } else if (part instanceof StringPart) {
                multipartParts.add(new StringMultipartPart((StringPart) part, boundary));
            } else {
                if (!(part instanceof InputStreamPart)) {
                    throw new IllegalArgumentException("Unknown part type: " + part);
                }
                multipartParts.add(new InputStreamMultipartPart((InputStreamPart) part, boundary));
            }
        }

        multipartParts.add(new MessageEndMultipartPart(boundary));
        return multipartParts;
    }
}
