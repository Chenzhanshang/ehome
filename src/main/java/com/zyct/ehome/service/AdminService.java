package com.zyct.ehome.service;

import com.zyct.ehome.entity.Admin;

/**
 * @author JGZ
 * CreateTime 2019/12/3 14:37
 * Email 1945282561@qq.com
 */
public interface AdminService {

    /**
     * 通过管理员账户获取密码
     * @param account
     * @return
     */
    public Admin getAdminByAccount(String account);

    /**
     * 通过账户修改密码
     * @param admin
     */
    void updatePasswordByAdminAccount(Admin admin);
}
