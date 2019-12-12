package com.zyct.ehome.service;

import com.zyct.ehome.entity.File;

/**
 * @author JGZ
 * CreateTime 2019/12/12 16:08
 * Email 1945282561@qq.com
 */
public interface FileManageService {

    /**
     * 通过id获取文件
     * @param fileId
     * @return
     */
    File getFileById(String fileId);
}
