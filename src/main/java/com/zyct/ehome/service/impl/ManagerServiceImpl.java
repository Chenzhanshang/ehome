package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.CommunityMapper;
import com.zyct.ehome.dao.ManagerMapper;
import com.zyct.ehome.dto.FixDto;
import com.zyct.ehome.dto.ManagerDto;
import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.Manager;
import com.zyct.ehome.service.ManagerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-23 15:10
 */
@Service
public class ManagerServiceImpl implements ManagerService {


    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private CommunityMapper communityMapper;

    private Manager manager;


    /**
     * 查询该用户是否存在
     *
     * @param manager
     * @return
     */
    @Override
    public Boolean isExits(Manager manager) {
        Manager m = managerMapper.isExits(manager);
        if (m == null){
            return false;
        }else {
            return true;
        }

    }

    /**
     * 执行登录逻辑
     *
     * @param manager
     * @return
     */
    @Override
    public Boolean login(Manager manager) {
        Manager m = managerMapper.login(manager);
        if (m == null){
            return false;
        }else {
            this.manager = m;
            return true;
        }

    }

    @Override
    public ManagerDto getManager(){
        ManagerDto managerDto = new ManagerDto();
        BeanUtils.copyProperties(manager,managerDto);
        Community community = communityMapper.getCommunityByCommunityId(manager.getCommunityId());
        managerDto.setCommunity(community);
        return managerDto;
    }

    @Override
    public List<Manager> selectByCommunityId(String communityId) {
        return managerMapper.selectByCommunityId(communityId);
    }


}
