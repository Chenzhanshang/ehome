package com.zyct.ehome.dao;

import com.zyct.ehome.entity.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-09 10:58
 */
@Mapper
public interface UploadFileMapper {

    /**
     * 将文件信息存入数据库
     */
    void insertFiles(List<File> file);


    void updateFile(File file);

    @Select("select * from file where apply_id = #{applyId}")
    List<File> selectFileByApplyId(@Param("applyId") String applyId);


}
