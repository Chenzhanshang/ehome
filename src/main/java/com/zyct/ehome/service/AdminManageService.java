package com.zyct.ehome.service;

import com.zyct.ehome.entity.Admin;

import java.util.List;

/**
 * @author CZS
 * CreateTime 2019/12/10 15:04
 * Email 642125256@qq.com
 */
public interface AdminManageService {
    /**
     * 获取管理员列表
     * @author CZS
     * @return
     */
    public List<Admin> getAdminList();

    /**
     * 更新管理员信息
     * @author CZS
     * @param admin
     * @return
     */
    public void updateAdmin(Admin admin);

    /**
     * 删除管理员
     * @author CZS
     * @param adminId
     * @return
     */
    public void delectAdmin(String adminId);

    /**
     * 删除管理员-角色
     * @author CZS
     * @param adminId
     * @return
     */
    public void delectAdminRole(String adminId);

    /**
     * 根据账号查询管理员ID
     * @author CZS
     * @param adminId
     * @return
     */
    public Admin getAdminByAdminAccount(String adminId);

}
