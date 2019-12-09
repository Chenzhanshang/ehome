package com.zyct.ehome.service;

import com.zyct.ehome.entity.Community;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/6 14:28
 * Email 1945282561@qq.com
 */
public interface CommunityManageService {

    /**
     * 添加小区
     * @param community
     * @return
     */
    public Community addCommunity(Community community);

    /**
     * 获取小区列表
     * @return
     */
    public List<Community> getList();
}
