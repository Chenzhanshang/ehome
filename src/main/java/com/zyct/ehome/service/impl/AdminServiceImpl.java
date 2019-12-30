package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.AdminMapper;
import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.service.AdminService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JGZ
 * CreateTime 2019/12/3 14:38
 * Email 1945282561@qq.com
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdminByAccount(String account) {

        return adminMapper.getAdminByAccount(account);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void updatePasswordByAdminAccount(Admin admin) {
        //利用Account做盐值进行1024次加密
        SimpleHash simpleHash = new SimpleHash("MD5", admin.getAdminPassword(),
                admin.getAdminAccount(), 1024);
        //设置密码
        admin.setAdminPassword(simpleHash.toString());

        adminMapper.updateByAdminAccount(admin);

    }


    public AdminMapper getAdminMapper() {
        return adminMapper;
    }

    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }
}
