package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.House;
import com.zyct.ehome.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-06 10:25
 */
@Mapper
public interface CommunityMapper {

    /**
     * 通过城市查找小区列表
     * @param city
     * @return
     */
    List<Community> getCommunityList(@Param("city") String city);

    /**
     * 通过小区id查找楼栋列表
     * @param communityId
     * @return
     */
    List<House> getHouseListByCommunityId(@Param("communityId")String communityId);

    /**
     * 通过楼栋id查找楼栋列表
     * @param houseId
     * @return
     */
    List<Room> getRoomListByHouseId(String houseId);
}
