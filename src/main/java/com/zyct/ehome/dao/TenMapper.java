package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.Fix;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-23 10:08
 */
@Mapper
public interface TenMapper {

    /**
     * 插入一条维修信息
     * @param fix
     */
    void insertFix(Fix fix);

    /**
     * 通过小区id查询维修列表
     * @param communityId
     * @return
     */
    List<Fix> selectFixListByCommunityId(String communityId);
}
