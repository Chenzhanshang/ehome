package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.RoleMapper;
import com.zyct.ehome.entity.Role;
import com.zyct.ehome.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author JGZ
 * CreateTime 2019/12/3 17:48
 * Email 1945282561@qq.com
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<Role> getRoleByAdminId(String adminId) {
        return roleMapper.getRoleByAdminId(adminId);
    }
}
