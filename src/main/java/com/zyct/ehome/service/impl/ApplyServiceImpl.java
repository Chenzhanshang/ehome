package com.zyct.ehome.service.impl;

import com.zyct.ehome.controller.UploadFileController;
import com.zyct.ehome.dao.ApplyMapper;
import com.zyct.ehome.dao.UploadFileMapper;
import com.zyct.ehome.entity.*;
import com.zyct.ehome.service.ApplyService;
import com.zyct.ehome.service.CommunityService;
import com.zyct.ehome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserService userService;

    private List<com.zyct.ehome.entity.File> fileList = new ArrayList<>();

    private Apply userApply;

    /**
     * 插入一个申请
     * @param apply
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String insertApply(Apply apply) {
        userApply = apply;
        applyMapper.insertApply(apply);
        for (File file1 : fileList) {
            file1.setApply(apply);
        }
        uploadFileMapper.insertFiles(fileList);
        fileList.clear();
        return apply.getApplyId();
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

    /**
     * 插入筹备小组申请信息
     * @param file
     * @param ownerId
     * @param communityId
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String insertGroupApply(File file, String ownerId, String communityId) {
        Apply apply = new Apply();
        String uuid = UUID.randomUUID().toString();
        apply.setApplyId(uuid);
        Owner owner = userService.selectUserByOwnerId(ownerId);
        apply.setOwner(owner);
        Community community = communityService.getCommunityByCommunityId(communityId);
        apply.setCommunity(community);
        apply.setFlowNode(new FlowNode(1,"用户提交申请",null));
        List<File> files = new ArrayList<>();
        files.add(file);
        for (File file1 : files) {
            file1.setApply(apply);
        }
        apply.setFiles(files);
        applyMapper.insertApply(apply);
        uploadFileMapper.insertFiles(files);
        return apply.getApplyId();
    }
}
