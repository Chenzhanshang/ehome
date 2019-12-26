package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Manager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-23 15:11
 */
@Mapper
public interface ManagerMapper {

    /**
     * 查询用户是否存在
     * @param manager
     * @return
     */
    Manager isExits(Manager manager);

    /**
     * 登录逻辑
     * @param manager
     * @return
     */
    Manager login(Manager manager);

    /**
     * 更新
     * @param manager
     */
    void update(Manager manager);

    /**
     *
     * 插入账号
     * @param m
     */
    void insert(Manager m);

    /**
     * 获取某个小区的管理账号
     * @param communityId
     * @return
     */
    List<Manager> selectByCommunityId(String communityId);
}
