package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Owner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/16 11:13
 * Email 1945282561@qq.com
 */
@Mapper
public interface OwnerMapper {

    /**
     * 通过小区id获取业主列表
     * @param communityId
     * @return
     */
    List<Owner> getOwnerListByCommunityId(String communityId);
}
