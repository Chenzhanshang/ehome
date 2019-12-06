package com.zyct.ehome.service;

import com.zyct.ehome.entity.Promise;
import com.zyct.ehome.entity.Role;

import java.util.Set;

/**
 * @author JGZ
 * CreateTime 2019/12/3 17:58
 * Email 1945282561@qq.com
 */
public interface PromiseService {

    /**
     * 通过角色获取权限
     * @param roles
     * @return
     */
    public Set<Promise> getPromiseByRole(Set<Role> roles);
}
