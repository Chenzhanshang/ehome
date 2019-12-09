package com.zyct.ehome.controller;

import com.zyct.ehome.service.ApplyService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-09 10:07
 * 用于接收上传的文件
 * @RequestParam("file")MultipartFile multipartFile
 */
@Controller
public class UploadFileController {

    @Autowired
    private ApplyService applyService;



    @RequestMapping("/uploadFile")
    @ResponseBody
    public ResponseMessage uploadFile(@RequestParam("file")MultipartFile multipartFile){
        String filename = multipartFile.getOriginalFilename();
        String path = "/Users/litianfu/Desktop/upload/";
        File f = new File(path);
        //如果不存在该路径就创建
        if (!f.exists()) {
            f.mkdir();
        }
        File dir = new File(path + multipartFile.getOriginalFilename());
        // 文件写入
        try {
            multipartFile.transferTo(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        com.zyct.ehome.entity.File file = new com.zyct.ehome.entity.File();
        file.setFileId(UUID.randomUUID().toString());
        file.setFileName(multipartFile.getOriginalFilename());
        file.setFilePath(path+multipartFile.getOriginalFilename());
//        file.setApply();

        applyService.insertFile(file);
        //将文件信息存入数据库
        return null;
    }

}
