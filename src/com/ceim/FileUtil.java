package com.ceim;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ceim.common.exception.CeimException;
import com.ceim.common.exception.CeimExceptionEnum;

public class FileUtil {


    public static File getFileByPath(String filePath) throws FileNotFoundException {
//        String[] slist = {"../","..%2F","/%c0%ae%c0%ae/","%2e%2e%2f","..\\","..//","..","/","\\","、"};
        if(filePath.indexOf("..")>0
                || filePath.indexOf("..%2F")>0
                || filePath.indexOf("/%c0%ae%c0%ae/")>0
                || filePath.indexOf("%2e%2e%2f")>0
                || filePath.indexOf("..\\\\")>0
                || filePath.indexOf("..\\")>0
                || filePath.indexOf("..//")>0
                || filePath.indexOf("/")>0
                || filePath.indexOf("\\\\")>0
                ) {
            throw new FileNotFoundException();
        }
        return StringUtils.isBlank(filePath) ? null : new File(filePath);
    }

    /**
     * 判断目录是否存在
     */
    public static void ifExists(String dirPath) throws IOException {
        ifExists2(getFileByPath(dirPath));
    }

//    private static File getFileByPath(String filePath) {
//        return StringUtils.isBlank(filePath) ? null : new File(filePath);
//    }

    /**
     * 判断目录是否存在
     */
    public static void ifExists2(File file) throws IOException {
        Validate.notNull(file);
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new IOException("There is a file exists " + file);
            }
        } else {
            file.mkdirs();
        }
    }

}