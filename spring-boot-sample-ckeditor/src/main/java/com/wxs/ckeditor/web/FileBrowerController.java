package com.wxs.ckeditor.web;

import com.wxs.ckeditor.constants.MueasConstants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>FileBrowerController</p>
 *
 * @author wuxinshui
 */
@Controller
@RequestMapping("/files")
public class FileBrowerController {
    protected final Logger logger = Logger.getLogger(getClass());

    private static final String IMAGE_DIR = MueasConstants.FILE_UPLOAD_DIR + MueasConstants.FILE_UPLOAD_SUB_IMG_DIR;

    @RequestMapping(value = "/browse/image", method = RequestMethod.GET)
    public void processBrowerPost(HttpServletRequest request, HttpServletResponse response) {

        String floderName = request.getParameter("folder");

        String realPath ="C:\\apache\\apache-tomcat-7.0.73\\webapps\\data\\file\\image\\";

        File folder = new File(realPath);
        if (!folder.exists()) {
            return;
        }

        // 存储子目录
        List<String> subFolderSet = new ArrayList<String>();
        // 存储文件夹
        List<String> subFileerSet = new ArrayList<String>();

        File[] subFiles = folder.listFiles();
        if (null != subFiles && 0 < subFiles.length) {
            for (int i = 0; i < subFiles.length; i++) {
                File _file = subFiles[i];
                if (_file.isDirectory()) {
                    subFolderSet.add(getDefaultFolderFromMueasRunningData(_file));
                } else {
                    subFileerSet.add(getFileName(_file.getName()));
                }
            }
        }

        String callback = request.getParameter("CKEditorFuncNum");
        PrintWriter out;

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String outs = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>服务器文件</title>"
                + "</head>"
                + "<body>";
        try {
            out = response.getWriter();

            outs += "<style type=‘text/css‘>"
                    + "li:hover{"
                    + "    color: #34ac83;"
                    + "    font-size: 19px;"
                    + " cursor: pointer;"
                    + "}"
                    + "</style>";

            outs += "<script type=‘text/javascript‘>";

            // 定义点击选择js
            String choose = "function choose(obj){"
                    + "window.opener.CKEDITOR.tools.callFunction(" + callback + ", obj);"
                    + "window.close();"
                    + "}";
            outs += choose;
            //logger.info(choose);

            // 定义文件夹点击响应js
            String view = "function view(obj){"
                    + "window.location.href=‘/files/browse/image?CKEditorFuncNum=" + callback + "&folder=‘ + obj;"
                    + "}";
            //logger.info(view);
            outs += view;
            outs += "</script>";

            // 这里显示一个返回顶级目录，也就是返回mueas-running-data目录
            String div1 = "<div style=‘width:100%;float:left;word-break:break-all;‘ "
                    + "onclick =view(‘" + URLEncoder.encode(MueasConstants.APP_RUNNING_FILES_DIR + MueasConstants.FILE_UPLOAD_DIR) + "‘)>"
                    + "<li>根级目录</li>"
                    + "</div>";
            //logger.info(div1);
            outs += div1;

            // 如果是子文件夹，显示上级目录链接
            if (!StringUtils.isEmpty(floderName) && !checkIsRoot(folder)) {
                String parent = getDefaultFolderFromMueasRunningData(folder.getParentFile());
                String div2 = "<div style=‘width:100%;float:left;word-break:break-all;‘ onclick =view(‘" + URLEncoder.encode(parent) + "‘)>"
                        + "<li>上级目录: " + parent + "</li>"
                        + "</div>";
                //logger.info(div2);
                outs += div2;
                if (logger.isDebugEnabled()) {
                    logger.debug("Parent folder exists: " + parent);
                }
            }

            // 如果是文件夹，则显示文件夹并且可以再次触发下级和上级目录
            if (0 < subFolderSet.size()) {
                Iterator<String> subFolderSetIndex = subFolderSet.iterator();
                while (subFolderSetIndex.hasNext()) {
                    String ftemp = subFolderSetIndex.next();
                    // 这里url传递的时候，需要转义
                    String div3 = "<div style=‘width:100%;float:left;word-break:break-all;‘ onclick =view(‘" + URLEncoder.encode(ftemp) + "‘)>"
                            + "<li>下级目录: " + ftemp + "</li>"
                            + "</div>";
                    //logger.info(div3);
                    outs += div3;

                    if (logger.isDebugEnabled()) {
                        logger.debug("Adding sub folder： " + ftemp);
                    }
                }
            }

            // 如果是文件，则点击就选择文件到控件中
            if (0 < subFileerSet.size()) {
                Iterator<String> subFileerSetIndex = subFileerSet.iterator();
                while (subFileerSetIndex.hasNext()) {
                    String ftemp = subFileerSetIndex.next();
                    String f = getDefaultFolderFromMueasRunningData(folder);
                    String fileUrl = "http://localhost:8080/data/file/image" + File.separator + ftemp;
//                    fileUrl = StringUtils.replace(fileUrl, "//", "/");
                    String div4 = "<div style=‘width:150px;height:150px;float:left;word-break:break-all;padding:5px;background:#666699;margin:5px;‘>"
                            + "<a href=‘javascript:void(0)‘ onclick=choose(" + fileUrl + ")>"
                            + "<img style=‘border:none;width:145px;height:145px;‘ src=" + fileUrl + " title=‘" + fileUrl + "‘/>"
                            + "</a>"
                            + "</div>";
                    //logger.info(div4);
                    outs += div4;

                    if (logger.isDebugEnabled()) {
                        logger.debug("Adding file： " + fileUrl);
                    }
                }
            }
            outs += "</body></html>";
            logger.info(outs);
            out.println(outs);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从/mueas-running-data这一级开始获取文件夹路径
     *
     * @return
     */
    private static String getDefaultFolderFromMueasRunningData(File folder) {
        String path = folder.getPath();
        path = path.substring(path.indexOf(MueasConstants.APP_RUNNING_FILES_DIR));
        return path;
    }

    /**
     * 判断是否是根目录
     *
     * @param folder
     * @return
     */
    private static boolean checkIsRoot(File folder) {
        String name = getFileName(folder.getName());
        return StringUtils.equalsIgnoreCase("/" + name, MueasConstants.FILE_UPLOAD_DIR);
    }

    /**
     * 获取输入文件路径或者目录路径中最后一级的名字，即可能是一个文件名，也可能是一个子目录的名字
     *
     * @param file
     * @return
     */
    private static String getFileName(String file) {
        String temp = new String(file);
        temp = temp.replace("//", "/");
        String items[] = file.split("/");
        if (items.length > 0) {
            return items[items.length - 1];
        } else {
            return null;
        }
    }
}
