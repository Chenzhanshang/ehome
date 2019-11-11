package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Manage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JGZ
 * @Classname ManageMapper
 * @Date 2019/11/4 20:30
 * @Email 1945282561@qq.com
 */
@Mapper
public interface ManageMapper {

    /**
     * 通过loginName获取Manage
     * @param loginName 管理员的登陆名
     * @return Manage只含有loginName和password
     */
    public Manage getManageByLoginName(String loginName);

}
