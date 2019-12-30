package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.CommunityMapper;
import com.zyct.ehome.dao.ManagerMapper;
import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.Manager;
import com.zyct.ehome.service.CommunityManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
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

    @Autowired
    private ManagerMapper managerMapper;

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public Community addCommunity(Community community) {
        String id = UUID.randomUUID().toString().replaceAll("-","");
        //设置id
        community.setCommunityId(id);
        //插入
        communityMapper.insert(community);
        //生成小区的账号
        generateAccount(id,1);
        generateAccount(id,2);
        //查询信息
        return communityMapper.selectByCommunityId(id);
    }

    /**
     * 生成小区的账号 业委会号和物业号
     * @param id
     */
    private void generateAccount(String id,Integer type){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        Manager m = new Manager();
        while (true){
            //清空串
            stringBuilder.delete(0,stringBuilder.length());
            //生成八位随机数
            for (int i = 0;i < 8;i++){
                stringBuilder.append(random.nextInt(10));
            }
            m.setAccount(stringBuilder.toString());
            //如果用户不存在
            if(managerMapper.isExits(m) == null){
                //插入用户
                m.setManagerId(UUID.randomUUID().toString().replaceAll("-",""));
                //设置小区
                m.setCommunityId(id);
                //清空串
                stringBuilder.delete(0,stringBuilder.length());
                for (int i = 0;i < 6;i++){
                    stringBuilder.append(random.nextInt(10));
                }
                //设置密码
                m.setPassword(stringBuilder.toString());
                //设置账号类型
                m.setType(type);
                managerMapper.insert(m);
                break;
            }
        }


    }

    @Override
    public List<Community> getList() {
        return communityMapper.getList();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void deleteCommunity(Community community) {
        communityMapper.deleteCommunityById(community);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void updateCommunity(Community community) {
        communityMapper.updateCommunityById(community);

    }
}
