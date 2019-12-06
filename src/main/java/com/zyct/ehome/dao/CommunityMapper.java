package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Community;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author JGZ
 * CreateTime 2019/12/6 14:31
 * Email 1945282561@qq.com
 */
@Mapper
public interface CommunityMapper {

    /**
     * 添加社区
     * @param community
     * @return
     */
    public void insert(Community community);

    /**
     * 查询社区通过id
     * @param id
     * @return
     */
    public Community selectByCommunityId(String id);
}
