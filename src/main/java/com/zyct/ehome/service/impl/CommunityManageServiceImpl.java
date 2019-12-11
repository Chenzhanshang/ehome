package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.CommunityMapper;
import com.zyct.ehome.entity.Community;
import com.zyct.ehome.service.CommunityManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author JGZ
 * CreateTime 2019/12/6 14:30
 * Email 1945282561@qq.com
 */
@Service
public class CommunityManageServiceImpl implements CommunityManageService {

    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public Community addCommunity(Community community) {
        String id = UUID.randomUUID().toString().replaceAll("-","");
        //设置id
        community.setCommunityId(id);
        //插入用户
        communityMapper.insert(community);
        //查询信息

        return communityMapper.selectByCommunityId(id);
    }

    @Override
    public List<Community> getList() {
        return communityMapper.getList();
    }

    @Override
    public void deleteCommunity(Community community) {
        communityMapper.deleteCommunityById(community);
    }

    @Override
    public void updateCommunity(Community community) {
        communityMapper.updateCommunityById(community);

    }
}
