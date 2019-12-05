package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.PromiseMapper;
import com.zyct.ehome.entity.Promise;
import com.zyct.ehome.entity.Role;
import com.zyct.ehome.service.PromiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author JGZ
 * CreateTime 2019/12/3 18:01
 * Email 1945282561@qq.com
 */
@Service
public class PromiseServiceImpl implements PromiseService {

    @Autowired
    private PromiseMapper promiseMapper;

    @Override
    public Set<Promise> getPromiseByRole(Set<Role> roles) {
        return promiseMapper.getPromiseByRole(roles);
    }
}
