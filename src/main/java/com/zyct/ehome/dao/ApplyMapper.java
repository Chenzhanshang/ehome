package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Apply;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-09 14:28
 */
@Mapper
public interface ApplyMapper {


    void insertApply(Apply apply);

}
