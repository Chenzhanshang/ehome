package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.FileMapper;
import com.zyct.ehome.entity.File;
import com.zyct.ehome.service.FileManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JGZ
 * CreateTime 2019/12/12 16:09
 * Email 1945282561@qq.com
 */
@Service
public class FileManageServiceImpl implements FileManageService {

    @Autowired
    private FileMapper fileMapper;
    @Override
    public File getFileById(String fileId) {
        return fileMapper.getFileById(fileId);
    }
}
