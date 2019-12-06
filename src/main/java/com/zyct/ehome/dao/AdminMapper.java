package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JGZ
 * CreateTime 2019/12/3 14:42
 * Email 1945282561@qq.com
 */
@Mapper
public interface AdminMapper {

    /**
     * 通过管理员账户获得账户信息
     * @param account
     * @return
     */
    public Admin getAdminByAccount(String account);

    /**
     * 通过AdminAccount修改密码
     * @param admin
     */
    void updateByAdminAccount(Admin admin);
}
