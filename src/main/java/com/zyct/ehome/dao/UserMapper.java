package com.zyct.ehome.dao;

import com.zyct.ehome.config.weixin.RawData;
import com.zyct.ehome.entity.Owner;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-03 17:07
 */
@Mapper
public interface UserMapper {

    public void insertUser(Owner owner) ;

    Owner selectUserByWeiXinId(String openid);
}
