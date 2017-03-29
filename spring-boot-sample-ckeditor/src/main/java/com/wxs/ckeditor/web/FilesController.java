package com.wxs.ckeditor.web;

import com.wxs.ckeditor.enums.FileType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * <p>FilesController</p>
 *
 * @author wuxinshui
 */
@Controller
@RequestMapping("/files")
public class FilesController {

    private Path rootLocation=Paths.get("F:\\data\\file\\image");

    Logger logger= org.apache.log4j.Logger.getLogger(FilesController.class);

    @Value(value = "${ckeditor.storage.image.path}")
    private String ckeditorStorageFilePath;

    private static final String SERVER_IMAGE_PATH ="http://localhost:8180/public/image/" ;



    @RequestMapping(value = "/upload/image",method = RequestMethod.POST)
    @ResponseBody
    public String uploadImage(@RequestParam("upload") MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        String name = "";
        if (!file.isEmpty()) {
            try {
                response.setContentType("text/html; charset=UTF-8");
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("X-Frame-Options", "SAMEORIGIN");
                PrintWriter out = response.getWriter();

                String fileClientName = file.getOriginalFilename();
                Path path= Paths.get(rootLocation.toString()+"/"+fileClientName);

                if (logger.isInfoEnabled()) {
                    logger.info("Begin uploading: " + file.getName());
                }

                // 为了客户端已经设置好了图片名称在服务器继续能够明确识别，这里不改名称
                // 获取目录
//                File floder = buildFolder(request, FileType.IMAGE);
                File floder = path.getParent().toFile();
                if (null == floder) {
                    logger.info("folder is null");
                    return null;
                }

                File newfile = new File(floder, fileClientName);
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newfile));
                stream.write(bytes);
                stream.close();

                if (logger.isInfoEnabled()) {
                    logger.info("Uploading done，floder: " + newfile.getPath());
                }

                // 组装返回url，以便于ckeditor定位图片
//                String fileUrl = SERVER_IMAGE_PATH+ File.separator + newfile.getName();
                String fileUrl = SERVER_IMAGE_PATH+ File.separator + newfile.getName();

                // 将上传的图片的url返回给ckeditor
                String callback = request.getParameter("CKEditorFuncNum");
                String script = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction(" + callback + ", '" + fileUrl + "');</script>";

                out.println(script);
                out.flush();
                out.close();
            } catch (Exception e) {
                logger.info("You failed to upload " + name + " => " + e.getMessage());
            }
        } else {
            logger.info("You failed to upload " + name + " because the file was empty.");
        }
        return "SUCCESS";
    }

    private File buildFolder(HttpServletRequest request, FileType type) {
        String folderdir = ckeditorStorageFilePath;
        //如果不存在，创建
        File floder = new File(folderdir);
        if (!floder.exists()) {
            if (!floder.mkdirs()) {
                logger.error("Create folder failed！ path=" + folderdir);
                return null;
            }
        }
        return floder;
    }
}
