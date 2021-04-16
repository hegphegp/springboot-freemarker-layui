package com.hegp.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.*;

public final class ResourcesUtils {

    /**
     * 读取文本资源,如果文件是以classpath:开头的,则去resources目录下读取,否则读取文件系统内的文件
     * @param path 文件路径
     * @return 字符串
     */
    public static String readFileToString(String path) throws IOException {
        if (path.startsWith("classpath:")) {
            path = path.substring(10);
            Resource resource = new ClassPathResource(path);
            resource.getFile();
            return FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream()));
        } else {
            return FileCopyUtils.copyToString(new FileReader(path));
        }
    }

    /**
     * 读取文本资源,如果文件是以classpath:开头的,则去resources目录下读取,否则读取文件系统内的文件
     * @param path 文件路径
     * @return 字符串
     */
    public static InputStream getInputStreamByPath(String path) throws IOException {
        if (path.startsWith("classpath:")) {
            path = path.substring(10);
            Resource resource = new ClassPathResource(path);
            return resource.getInputStream();
        } else {
            return new FileInputStream(path);
        }
    }
}