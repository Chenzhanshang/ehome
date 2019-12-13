package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.AdminManageMapper;
import com.zyct.ehome.dao.AdminMapper;
import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.service.AdminManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void updateAdmin(Admin admin) {
        adminManageMapper.updateAdmin(admin);
        adminManageMapper.updateAdminRole(admin.getAdminId(),admin.getRoles().iterator().next().getRoleId());
    }

    @Override
    public void delectAdmin(String adminId) {
        adminManageMapper.delectAdmin(adminId);
    }

    @Override
    public void delectAdminRole(String adminId) {
        adminManageMapper.delectAdminRole(adminId);
    }

    @Override
    public Admin getAdminByAdminAccount(String adminId) {
        Admin admin = adminManageMapper.getAdminByAdminAccount(adminId);
        return admin;
    }
}
