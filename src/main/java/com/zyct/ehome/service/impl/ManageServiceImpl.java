package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.ManageMapper;
import com.zyct.ehome.entity.Manage;
import com.zyct.ehome.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author JGZ
 * @Classname ManageServiceImpl
 * @Date 2019/11/4 20:26
 * @Email 1945282561@qq.com
 */
@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private ManageMapper manageMapper;

    @Override
    public Manage getManageByLoginName(String loginName) {
        return manageMapper.getManageByLoginName(loginName);
    }

    public ManageMapper getManageMapper() {
        return manageMapper;
    }

    public void setManageMapper(ManageMapper manageMapper) {
        this.manageMapper = manageMapper;
    }
}
