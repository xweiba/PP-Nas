package pp.weiba.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static cn.hutool.core.io.file.PathUtil.walkFiles;

/**
 * @author xiaoweiba1028@gmail.com
 * @date 2022/10/10
 **/

@Log4j2
public class FileUtils {

    /**
     * 获取分片文件的md5值
     *
     * @param file : file
     * @param size : size 分片字节大小
     * @return java.lang.String
     * @author xiaoweiba1028@gmail.com
     * @date 2022/10/10 16:06
     */
    public static String getSliceMd5(File file, int size) {
        BufferedInputStream inputStream = FileUtil.getInputStream(file);
        byte[] lastByte = new byte[size];
        try {
            inputStream.read(lastByte, 0, size);
        } catch (IOException e) {
            log.error("getSliceMd5 read file error: {}", file.getAbsolutePath());
            throw new RuntimeException("getSliceMd5 error");
        }
        return SecureUtil.md5(new ByteArrayInputStream(lastByte));
    }

    public static byte[] getSliceFile(File file, int off, int size) {
        BufferedInputStream inputStream = FileUtil.getInputStream(file);
        byte[] lastByte = new byte[size];
        try {
            inputStream.skip(off);
            inputStream.read(lastByte);
        } catch (IOException e) {
            log.error("getSliceFile read file error: {}", file.getAbsolutePath());
            throw new RuntimeException("getSliceMd5 error");
        }
        return lastByte;
    }

    public static String encryptToBase64(byte[] b) {
        if (b == null || b.length == 0) {
            return null;
        }
        return Base64.getEncoder().encodeToString(b);
    }

    public static String formatPath(String path) {
        String separator = "/";
        if (path.indexOf("\\") >= 0) path = path.replaceAll("\\\\", separator);
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }


    public static List<File> loopFiles(String path, FileFilter fileFilter) {
        return loopFiles(FileUtil.file(path).toPath(), -1, fileFilter);
    }

    public static List<File> loopFiles(Path path, int maxDepth, FileFilter fileFilter) {
        final List<File> fileList = new ArrayList<>();

        if (null == path || !Files.exists(path)) {
            return fileList;
        } else if (!FileUtil.isDirectory(path)) {
            final File file = path.toFile();
            if (null == fileFilter || fileFilter.accept(file)) {
                fileList.add(file);
            }
            return fileList;
        }

        walkFiles(path, maxDepth, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs)
                    throws IOException {
                final File file = path.toFile();
                if (null == fileFilter || fileFilter.accept(file)) {
                    log.debug("About to visit file {}\n", path);
                    fileList.add(file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path path, IOException e)
                    throws IOException {
                log.error("Visiting failed for {}\n", path);
                return FileVisitResult.SKIP_SUBTREE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path path,
                                                     BasicFileAttributes attrs)
                    throws IOException {
                final File file = path.toFile();
                if (null == fileFilter || fileFilter.accept(file)) {
                    log.debug("About to visit directory {}\n", path);
                    fileList.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return fileList;
    }

    @SneakyThrows
    public static Date getCreateTime(File file) {
        if (file == null) return null;
        BasicFileAttributes fileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        FileTime creationTime = fileAttributes.creationTime();
        return new Date(creationTime.toMillis());
    }

    public static Date lastModifiedTime(File file) {
        if (file == null) return null;
        return FileUtil.lastModifiedTime(file);
    }

    public static Long size(File file) {
        if (file == null) return 0L;
        return FileUtil.size(file);
    }

    public static String readString(String filePath) {
        return readString(new File(filePath));
    }

    public static String readString(File file) {
        if (FileUtil.isEmpty(file)) return "";
        return FileUtil.readString(file, StandardCharsets.UTF_8);
    }

    public static String getResourcePath(String resourcePath) {
        URL resource = FileUtils.class.getClassLoader().getResource(resourcePath);
        if (resource == null || StringUtils.isBlank(resource.getPath())) return "";
        return resource.getPath();
    }
}
