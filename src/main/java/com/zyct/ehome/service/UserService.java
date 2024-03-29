package com.zyct.ehome.service;

import com.zyct.ehome.config.weixin.RawData;
import com.zyct.ehome.entity.Owner;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-03 17:01
 */
public interface UserService {

    Owner insertUser(String openId, RawData data);

    Owner selectUserByOwnerId(String ownerId);

    void updateUser(Owner owner);

    void getAvatar(String avatar,String avatarUrl,String openid);


}
