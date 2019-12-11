package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Community;
import org.apache.ibatis.annotations.Mapper;


import com.zyct.ehome.entity.House;
import com.zyct.ehome.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/6 14:31
 * Email 1945282561@qq.com
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-06 10:25
 */
@Mapper
public interface CommunityMapper {

    /**
     * 添加社区
     * @param community
     * @return
     */
    public void insert(Community community);

    /**
     * 查询社区通过id
     * @param id
     * @return
     */
    public Community selectByCommunityId(String id);
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

    /**
     * 通过小区id查询小区
     * @param communityId
     * @return
     */
    @Select("select * from t_community where community_id = #{communityId}")
    Community getCommunityByCommunityId(@Param("communityId") String communityId);

    /**
     * 通过楼栋id查询楼栋
     * @param houseId
     * @return
     */
    @Select("select * from t_house where house_id = #{houseId}")
    House getHouseByHouseId(@Param("houseId") String houseId);

    @Select("select * from t_room where room_id = #{roomId}")
    Room getRoomByRoomId(@Param("roomId") String roomId);
    /**
     * 获取小区列表
     * @return
     */
    List<Community> getList();

    /**
     * 通过id删除小区
     * @param community
     */
    void deleteCommunityById(Community community);

    /**
     * 通过通过id修改小区
     * @param community
     */
    void updateCommunityById(Community community);
}
