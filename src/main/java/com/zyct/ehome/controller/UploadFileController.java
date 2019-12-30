package com.zyct.ehome.controller;

import com.zyct.ehome.dao.UploadFileMapper;
import com.zyct.ehome.service.ApplyService;
import com.zyct.ehome.service.UserService;
import com.zyct.ehome.utils.ResponseMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-09 10:07
 * 用于接收上传的文件
 * @RequestParam("file")MultipartFile multipartFile
 */
@PropertySource("classpath:file.properties")
@Controller
public class UploadFileController {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private UserService userService;

    @Autowired
    private UploadFileMapper uploadFileMapper;

    @Value("${filepath}")
    String path;

    /**
     * 上传业主认证文件
     * @param multipartFile
     * @return
     */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();
        createFile(multipartFile);
        com.zyct.ehome.entity.File file = new com.zyct.ehome.entity.File();
        file.setFileId(UUID.randomUUID().toString());
        file.setFileName(multipartFile.getOriginalFilename());
        file.setFilePath(path + multipartFile.getOriginalFilename());
        applyService.insertFile(file);
        String[] str = {"http://localhost:8081/ehome/file/" + multipartFile.getOriginalFilename()};
        Map<String, Object> map = new HashMap<>();
        map.put("urls", str);
        return map;
    }

    /**
     * 上传成立业委会的文件
     * @param multipartFile
     * @param ownerId
     * @param filename
     * @param communityId
     * @return
     */
    @RequestMapping("/uploadApplyGroup")
    @ResponseBody
    public ResponseMessage uploadApplyGroup(@RequestParam("file") MultipartFile multipartFile,
                                            @Param("ownerId") String ownerId, @Param("filename") String filename,
                                            @Param("communityId") String communityId) {
        createFile(multipartFile);
        com.zyct.ehome.entity.File file = new com.zyct.ehome.entity.File();
        file.setFileId(UUID.randomUUID().toString());
        file.setFileName(filename);
        file.setFilePath(path + multipartFile.getOriginalFilename());
        String applyId = applyService.insertGroupApply(file, ownerId, communityId);
        ResponseMessage responseMessage = new ResponseMessage("success", "上传成功");
        Map<String,Object> map = new HashMap<>();
        map.put("applyId",applyId);
        responseMessage.setData(map);
        return responseMessage;
    }

    /**
     * 更换头像
     * @param multipartFile
     * @param ownerId
     */
    @RequestMapping("/uploadAvatar")
    @ResponseBody
    public Map<String, Object> uploadAvatar(@RequestParam("file") MultipartFile multipartFile,
                                            @RequestParam(value = "ownerId",required = false)String ownerId){
        createFile(multipartFile);
        com.zyct.ehome.entity.File file = new com.zyct.ehome.entity.File();
        file.setFileId(UUID.randomUUID().toString());
        String filename = multipartFile.getOriginalFilename();
        file.setFileName(filename);
        file.setFilePath(path + filename);
        List<com.zyct.ehome.entity.File> files = new ArrayList<>();
        files.add(file);
        uploadFileMapper.insertFiles(files);
        userService.getAvatar(filename,path + filename,ownerId);
        String[] str = {"http://localhost:8081/ehome/file/" + filename};
        Map<String, Object> map = new HashMap<>();
        map.put("urls", str);
        return map;

    }

    /**
     * 抽取重复代码
     * @param multipartFile
     */
    private void createFile(MultipartFile multipartFile) {
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

    }


}
