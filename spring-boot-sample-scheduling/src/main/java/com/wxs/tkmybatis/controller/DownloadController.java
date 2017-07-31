package com.wxs.tkmybatis.controller;


import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import static com.wxs.tkmybatis.util.ExcelUtil.createExcel;

/**
 * @Description:
 * @Author:Wuxinshui
 * @Date:2017/4/20 10:41
 */
@RestController
@RequestMapping("/download")
public class DownloadController {

	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile() {
		ByteArrayOutputStream bos = null;
		String filename = "测试.xlsx";
		try {
			Workbook workbook = createExcel();
			bos = new ByteArrayOutputStream();
			workbook.write(bos);
			workbook.close();

			HttpHeaders headers = new HttpHeaders();
			headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
			headers.add("Pragma", "no-cache");
			headers.add("Expires", "0");
			headers.add("charset", "utf-8");
			//设置下载文件名
			filename = URLEncoder.encode(filename, "UTF-8");
			headers.add("Content-Disposition", "attachment;filename=\"" + filename + "\"");

			Resource resource = new InputStreamResource(new ByteArrayInputStream(bos.toByteArray()));

			return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);

		} catch (IOException e) {
			if (null != bos) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
}
