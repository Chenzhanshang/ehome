package com.zyct.ehome.service;

import com.zyct.ehome.entity.Manage;

/**
 * @author JGZ
 * @Classname ManageService
 * @Date 2019/11/4 20:25
 * @Email 1945282561@qq.com
 */
public interface ManageService {

    /**
     * 通过登陆名获取管理账号的密码
     * @param loginName
     * @return
     */
    Manage getManageByLoginName(String loginName);
}
