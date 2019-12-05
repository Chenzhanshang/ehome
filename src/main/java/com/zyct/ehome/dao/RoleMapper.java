package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * @author JGZ
 * CreateTime 2019/12/3 17:49
 * Email 1945282561@qq.com
 */
@Mapper
public interface RoleMapper {

    /**
     * 通过Adminid获取admin的角色
     * @param Adminid
     * @return
     */
    public Set<Role> getRoleByAdminId(String Adminid);

}
