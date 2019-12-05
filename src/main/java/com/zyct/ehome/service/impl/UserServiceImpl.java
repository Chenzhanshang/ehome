package com.zyct.ehome.service.impl;

import com.zyct.ehome.config.weixin.RawData;
import com.zyct.ehome.dao.UserMapper;
import com.zyct.ehome.entity.Owner;
import com.zyct.ehome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-03 17:07
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String insertUser(String openId) {
        //查询用户是否存在
        Owner owner = userMapper.selectUserByWeiXinId(openId);
        //生成唯一标识
        String uuid = UUID.randomUUID().toString();
        //如果用户不存在就插入新用户
        if (owner == null){
            Owner newOwner = new Owner();
            newOwner.setOwnerId(uuid);
            newOwner.setOwnerWeixinId(openId);
            userMapper.insertUser(newOwner);
        }else {
            return owner.getOwnerId();
        }
        return uuid;
    }
}
