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
    public Owner insertUser(String openId, RawData data) {
        //查询用户是否存在
        Owner owner = userMapper.selectUserByWeiXinId(openId);
        //生成唯一标识
        String uuid = UUID.randomUUID().toString();
        //如果用户不存在就插入新用户
        Owner newOwner = new Owner();
        if (owner == null){
            newOwner.setOwnerId(uuid);
            newOwner.setOwnerName(data.getNickName());
            newOwner.setOwnerWeixinId(openId);
            newOwner.setAvatar(data.getAvatarUrl());
            newOwner.setAvatarUrl(data.getAvatarUrl());
            userMapper.insertUser(newOwner);
        }else {
            owner.setAvatar(data.getAvatarUrl());
            owner.setAvatarUrl(data.getAvatarUrl());
            this.updateUser(owner);
            return owner;
        }
        return newOwner;
    }

    @Override
    public void getAvatar(String avatar, String avatarUrl, String ownerId){
        Owner owner = userMapper.selectUserByOwnerId(ownerId);
        owner.setAvatarUrl(avatarUrl);
        owner.setAvatar(avatar);
        this.updateUser(owner);
    }

    @Override
    public Owner selectUserByOwnerId(String ownerId) {
        return userMapper.selectUserByOwnerId(ownerId);
    }

    @Override
    public void updateUser(Owner owner) {
        userMapper.updateUser(owner);
    }


}
