package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CZS
 * CreateTime 2019/12/10 14:55
 * Email 642125256@qq.com
 */
@Mapper
public interface AdminManageMapper {
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
     * 更新管理员-角色信息
     * @author CZS
     * @param adminId
     * @param roleId
     * @return
     */
    public void updateAdminRole(String adminId, String roleId);

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


    /**
     * 获得角色列表
     * @author CZS
     * @return
     */
    List<Role> getAllRole();
}
