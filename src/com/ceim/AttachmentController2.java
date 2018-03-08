package com.ceim;

import com.ceim.common.util.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


/**
 * Created by sunbingrun on 17/12/10.
 * 附件controller
 */
public class AttachmentController2 {

    public static String fileDir = "C:\\\\Users\\\\administrator\\\\Desktop\\\\dev\\\\";

    /**
     * springboot 映射目录上传，准备使用这种
     */
    public static String fileUpload( MultipartFile file) throws IOException {
        String message = "{\"files\":[";
        String filename = "";
        String f_type = "f_type";//存数据库类型以及存储目录
        String f_positon = "f_positon";//用于回调显示上传文件回显位置
        String f_names = "f_names";//提交附件的name名称
        String fid = ("fid");

        String fileName = file.getOriginalFilename();

        String fileDir2 = fileDir.replaceAll("null", "");
        String f_type2 = f_type.replaceAll("null", "");

//        File outFile = new File(fileDir + f_type + File.separator);

        int split = fileName.lastIndexOf(".");
        filename = System.currentTimeMillis() + "." + fileName.substring(split + 1, fileName.length());//生成文件名
//         filename = System.currentTimeMillis() + "_" + Math.abs(file.getOriginalFilename().hashCode()) + "." + fileName.substring(split + 1, fileName.length());
        String path = File.separator + f_type2 + File.separator + filename;

        FileUtil.ifExists(fileDir2 + f_type2 + File.separator);

        file.transferTo(FileUtil.getFileByPath(fileDir2 + f_type2 + File.separator + filename));
//        String id = this.save(fileName, filename, f_type2, path, fid);
        String id = "abc";
        message += "{\"name\":\"" + fileName + "\",\"id\":\"" + id
                + "\",\"deleteUrl\":\"/attach/delete?id=" + id
                + "\",\"deleteType\":\"get\",\"url\":\"/attach/download?id=" + id + "\"}";
        message += "]";
        message += ",\"success\":1";
        message += ",\"f_positon\":\""+f_positon+"\"";
        message += ",\"f_names\":\""+f_names+"\"";
        message += "}";
        return message;

//        try (
//                InputStream in = file.getInputStream();
//                OutputStream ot = new FileOutputStream(fileDir2 + f_type2 + File.separator + filename)) {
//            byte[] buffer = new byte[1024];
//            int len;
//            while ((-1 != (len = in.read(buffer)))) {
//                ot.write(buffer, 0, len);
//            }
//
//            String id = this.save(fileName, filename, f_type2, path, fid);
//            message += "{\"name\":\"" + fileName + "\",\"id\":\"" + id
//                    + "\",\"deleteUrl\":\"/attach/delete?id=" + id
//                    + "\",\"deleteType\":\"get\",\"url\":\"/attach/download?id=" + id + "\"}";
//            message += "]";
//            message += ",\"success\":1";
//            message += ",\"f_positon\":\""+f_positon+"\"";
//            message += ",\"f_names\":\""+f_names+"\"";
//            message += "}";
//            return message;
//        } catch (IOException e) {
//            e.printStackTrace();
//            message = "{\"success\":2}";
//            return message;
//        }

    }

    public static void main(String[] args){

        try {
            fileUpload(null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
