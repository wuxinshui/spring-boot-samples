package com.wxs.excel.controller;

import com.wxs.excel.vo.UploadVo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/6/4 14:44
 */
@RestController
public class UploadController {

    @PostMapping("/upload")
    public ModelMap upload(UploadVo uploadVo, @RequestParam(required = false) MultipartFile file) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("code", "200");
        modelMap.put("msg", "success");
        modelMap.put("upload",uploadVo);
        modelMap.put("file",file.getOriginalFilename());
        System.out.println(modelMap);
        return modelMap;
    }
}
