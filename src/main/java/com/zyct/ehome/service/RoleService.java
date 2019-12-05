package com.zyct.ehome.service;

import com.zyct.ehome.entity.Role;

import java.util.Set;

/**
 * @author JGZ
 * CreateTime 2019/12/3 17:46
 * Email 1945282561@qq.com
 */
public interface RoleService {

    /**
     * 通过adminid获取角色
     * @param adminId
     * @return
     */
    public Set<Role> getRoleByAdminId(String adminId);

}
