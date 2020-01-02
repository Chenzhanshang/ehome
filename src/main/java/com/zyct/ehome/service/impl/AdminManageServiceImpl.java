package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.AdminManageMapper;
import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.entity.Role;
import com.zyct.ehome.service.AdminManageService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author CZS
 * CreateTime 2019/12/10 15:05
 * Email 642125256@qq.com
 */
@Service
public class AdminManageServiceImpl implements AdminManageService {
    @Autowired
    private AdminManageMapper adminManageMapper;

    @Override
    public List<Admin> getAdminList() {
        List<Admin> list = adminManageMapper.getAdminList();
        return list;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void updateAdmin(Admin admin) {
        //加密密码
        ByteSource bytes = ByteSource.Util.bytes(admin.getAdminAccount());
        SimpleHash simpleHash = new SimpleHash("MD5",admin.getAdminPassword(),bytes,1024);
        admin.setAdminPassword(simpleHash.toString());
        adminManageMapper.updateAdmin(admin);
        adminManageMapper.updateAdminRole(admin.getAdminId(),admin.getRoles().iterator().next().getRoleId());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void deleteAdmin(String adminId) {
        System.out.println(adminId);
        adminManageMapper.deleteAdminRole(adminId);
        adminManageMapper.deleteAdmin(adminId);


    }

    @Override
    public void deleteAdminRole(String adminId) {
        adminManageMapper.deleteAdminRole(adminId);
    }

    @Override
    public Admin getAdminByAdminAccount(String adminId) {
        Admin admin = adminManageMapper.getAdminByAdminAccount(adminId);
        return admin;
    }

    @Override
    public List<Role> getAllRole() {
        List<Role> roles = adminManageMapper.getAllRole();
        return roles;
    }
}
