package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.HouseMapper;
import com.zyct.ehome.entity.House;
import com.zyct.ehome.service.HouseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author JGZ
 * CreateTime 2019/12/9 15:06
 * Email 1945282561@qq.com
 */
@Service
public class HouseManageServiceImpl implements HouseManageService {

    @Autowired
    private HouseMapper houseMapper;

    @Override
    public void addHouse(House house) {
        house.setHouseId(UUID.randomUUID().toString().replaceAll("-",""));
        houseMapper.insert(house);
    }

    @Override
    public List<House> getHouseListByCommunityId(String communityId) {
        return houseMapper.getHouseListByCommunityId(communityId);
    }
}
