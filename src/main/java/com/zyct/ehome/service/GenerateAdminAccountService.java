package com.zyct.ehome.service;

import com.zyct.ehome.entity.Admin;

/**
 * @author CZS
 * CreateTime 2019/12/9 17:33
 * Email 642125256@qq.com
 */
public interface GenerateAdminAccountService {
    /**
     * 保存管理员信息
     * @author CZS
     * @param admin
     * @return
     */
    public void insertAdmin(Admin admin);

    /**
     * 保存管理员-角色信息
     * @author CZS
     * @param adminId 管理员id
     * @param roleId 角色id
     * @return
     */
    public void insertAdminAndRole(String adminId, String roleId);
}
