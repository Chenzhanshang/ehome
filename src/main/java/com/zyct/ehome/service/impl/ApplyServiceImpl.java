package com.zyct.ehome.service.impl;

import com.zyct.ehome.controller.UploadFileController;
import com.zyct.ehome.dao.ApplyMapper;
import com.zyct.ehome.dao.UploadFileMapper;
import com.zyct.ehome.entity.Apply;
import com.zyct.ehome.entity.File;
import com.zyct.ehome.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-09 10:57
 */
@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private UploadFileMapper uploadFileMapper;

    @Autowired
    private ApplyMapper applyMapper;

    private List<com.zyct.ehome.entity.File> fileList = new ArrayList<>();

    private Apply userApply;

    /**
     * 插入一个申请
     * @param apply
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertApply(Apply apply) {
        userApply = apply;
        applyMapper.insertApply(apply);
        for (File file1 : fileList) {
            file1.setApply(apply);
        }
        uploadFileMapper.insertFiles(fileList);

    }

    /**
     * 将文件信息放进List
     * @param file
     * @return
     */
    @Override
    public void insertFile(File file) {
        fileList.add(file);
    }

    @Override
    public List<File> selectFileUrls(){
        List<File> files = uploadFileMapper.selectFileByApplyId(userApply.getApplyId());
        return files;
    }
}
