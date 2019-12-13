package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CZS
 * CreateTime 2019/12/9 17:36
 * Email 642125256@qq.com
 */

@Mapper
public interface GenerateAdminAccountMapper {
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
