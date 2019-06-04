package com.wxs.excel.controller;

import com.wxs.excel.utils.ExcelUtil;
import com.wxs.excel.vo.UploadVo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/6/4 14:44
 */
@RestController
public class DownloadController {

    @GetMapping("/download")
    public ResponseEntity<Resource> upload() {
        //OutputStream os = response.getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String filename = URLEncoder.encode("下来吧", StandardCharsets.UTF_8);

        //response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename + ".xls"));
        //response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        //response.setHeader("Expires", "0");
        //response.setHeader("Pragma", "no-cache");
        //response.setDateHeader("Expires", 0);
        Map<String, Collection<?>> dataMap = new LinkedHashMap<>();
        Map<String, Map<String, String>> headMap = new LinkedHashMap<>();


        Map<String, String> userIdMap = new LinkedHashMap<>();
        userIdMap.put("name", "姓名");
        userIdMap.put("age", "年龄");
        userIdMap.put("sex", "性别");

        String product = "用户列表";

        headMap.put(product, userIdMap);
        dataMap.put(product, List.of(new UploadVo("12", "23", "45")));

        ExcelUtil.exportExcel(dataMap, headMap, null, byteArrayOutputStream);
        //os.flush();
        //os.close();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("charset", "utf-8");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", filename + ".xls"));

        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
