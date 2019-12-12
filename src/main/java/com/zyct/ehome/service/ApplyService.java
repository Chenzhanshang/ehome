package com.zyct.ehome.service;

import com.zyct.ehome.entity.Apply;
import com.zyct.ehome.entity.File;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-09 10:55
 */
public interface ApplyService {

    /**
     * 插入一个申请
     * @param apply
     */
     String insertApply(Apply apply);

    /**
     * 将文件信息数据库
     * @param file
     * @return
     */
    void insertFile(File file);


    List<File> selectFileUrls();

    String insertGroupApply(File file, String ownerId, String communityId);
}
