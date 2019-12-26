package com.zyct.ehome.service;

import com.zyct.ehome.dto.FixDto;
import com.zyct.ehome.dto.ManagerDto;
import com.zyct.ehome.entity.Manager;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-23 15:07
 */
public interface ManagerService {

    /**
     * 查询该用户是否存在
     * @param manager
     * @return
     */
     Boolean isExits(Manager manager);

    /**
     * 执行登录逻辑
     * @param manager
     * @return
     */
     Boolean login(Manager manager);

     ManagerDto getManager();

    /**
     * 获取某个小区的管理账号
     * @param communityId
     * @return
     */
    List<Manager> selectByCommunityId(String communityId);
}
