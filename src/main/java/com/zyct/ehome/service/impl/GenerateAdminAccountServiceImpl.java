package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.GenerateAdminAccountMapper;
import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.service.GenerateAdminAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CZS
 * CreateTime 2019/12/9 17:35
 * Email 642125256@qq.com
 */
@Service
public class GenerateAdminAccountServiceImpl implements GenerateAdminAccountService {

    @Autowired
    private GenerateAdminAccountMapper generateAdminAccountMapper;
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void insertAdmin(Admin admin) {
        generateAdminAccountMapper.insertAdmin(admin);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public void insertAdminAndRole(String adminId, String roleId) {
        generateAdminAccountMapper.insertAdminAndRole(adminId, roleId);
    }
}
