package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Promise;
import com.zyct.ehome.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * @author JGZ
 * CreateTime 2019/12/3 18:03
 * Email 1945282561@qq.com
 */
@Mapper
public interface PromiseMapper {

    /**
     * 通过角色获取权限
     * @param roles
     * @return
     */
    public Set<Promise> getPromiseByRole(Set<Role> roles);
}
