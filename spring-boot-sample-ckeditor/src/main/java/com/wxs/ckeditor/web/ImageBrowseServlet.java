package com.wxs.ckeditor.web;

import com.ckfinder.connector.ConnectorServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @Description:
 * @Author:Wuxinshui
 * @Date:2017/4/12 18:05
 */
@WebServlet(urlPatterns = "/ckfinder/core/connector/java/connector.java", initParams = {
		@WebInitParam(name = "XMLConfig", value = "classpath:/static/ckfinder.xml"),
		@WebInitParam(name = "debug", value = "false"),
		@WebInitParam(name = "configuration", value = "com.wxs.ckeditor.config.CKFinderConfig")
})
public class ImageBrowseServlet extends ConnectorServlet {
}
