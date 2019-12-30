package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.HouseMapper;
import com.zyct.ehome.entity.House;
import com.zyct.ehome.service.HouseManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void addHouse(House house) {
        house.setHouseId(UUID.randomUUID().toString().replaceAll("-",""));
        houseMapper.insert(house);
    }

    @Override
    public List<House> getHouseListByCommunityId(String communityId) {
        return houseMapper.getHouseListByCommunityId(communityId);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void updateHouse(House house) {
        houseMapper.updateHouseNameById(house);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void deleteHouse(House house) {
        houseMapper.deleteHouseById(house);
    }
}
