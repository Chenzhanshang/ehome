package com.zyct.ehome.service;

import com.zyct.ehome.entity.House;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/9 15:04
 * Email 1945282561@qq.com
 */
public interface HouseManageService {


    /**
     * 添加楼栋
     * @param house
     */
    void addHouse(House house);

    /**
     * 通过社区id获取楼栋
     * @param communityId
     * @return
     */
    List<House> getHouseListByCommunityId(String communityId);
}
