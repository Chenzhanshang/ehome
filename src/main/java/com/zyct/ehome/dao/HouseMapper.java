package com.zyct.ehome.dao;

import com.zyct.ehome.entity.House;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/9 15:06
 * Email 1945282561@qq.com
 */
@Mapper
public interface HouseMapper {

    /**
     * 插入楼栋
     * @param house
     */
    public void insert(House house);

    /**
     * 通过社区id获取小区列表
     * @param communityId
     * @return
     */
    List<House> getHouseListByCommunityId(String communityId);
}
