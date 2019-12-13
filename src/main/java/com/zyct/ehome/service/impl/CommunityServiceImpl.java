package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.CommunityMapper;
import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.House;
import com.zyct.ehome.entity.Room;
import com.zyct.ehome.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-06 10:43
 */
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public List<Community> getCommunityList(String city) {

        String cityStr = "%"+city+"%";

        List<Community> communityList = communityMapper.getCommunityList(cityStr);
        return communityList;
    }

    /**
     * 通过小区id查找楼栋列表
     *
     * @param communityId
     * @return
     */
    @Override
    public List<House> getHouseListByCommunityId(String communityId) {
        List<House> houseList = communityMapper.getHouseListByCommunityId(communityId);
        return houseList;
    }

    /**
     * 通过楼栋id查找楼栋列表
     *
     * @param houseId
     * @return
     */
    @Override
    public List<Room> getRoomListByHouseId(String houseId) {
        List<Room> roomList = communityMapper.getRoomListByHouseId(houseId);
        return roomList;
    }

    /**
     * 通过小区id查询小区
     *
     * @param communityId
     * @return
     */
    @Override
    public Community getCommunityByCommunityId(String communityId) {
        return communityMapper.getCommunityByCommunityId(communityId);
    }

    /**
     * 通过楼栋id查询楼栋
     *
     * @param houseId
     * @return
     */
    @Override
    public House getHouseByHouseId(String houseId) {
        return communityMapper.getHouseByHouseId(houseId);
    }

    /**
     * 通过房子id查询房子
     *
     * @param roomId
     * @return
     */
    @Override
    public Room getRoomByRoomId(String roomId) {
        return communityMapper.getRoomByRoomId(roomId);
    }

    /**
     * 获取该用户所有已认证小区
     *
     * @param ownerId
     * @return
     */
    @Override
    public List<Community> getCommunityListByOwnerId(String ownerId) {
        return communityMapper.getCommunityListByOwnerId(ownerId);
    }


}
