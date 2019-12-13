package com.zyct.ehome.dao;

import com.zyct.ehome.entity.File;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JGZ
 * CreateTime 2019/12/12 16:10
 * Email 1945282561@qq.com
 */
@Mapper
public interface FileMapper {

    /**
     * 通过文件id获取文件
     * @param fileId
     * @return
     */
    File getFileById(String fileId);
}
