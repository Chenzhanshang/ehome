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
}
