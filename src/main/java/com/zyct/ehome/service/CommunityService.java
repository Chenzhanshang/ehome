package com.zyct.ehome.service;

import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.House;
import com.zyct.ehome.entity.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-06 10:43
 */
public interface CommunityService {

    List<Community> getCommunityList(String city);

    /**
     * 通过小区id查找楼栋列表
     * @param communityId
     * @return
     */
    List<House> getHouseListByCommunityId(String communityId);

    /**
     * 通过楼栋id查找楼栋列表
     * @param houseId
     * @return
     */
    List<Room> getRoomListByHouseId(String houseId);

}
