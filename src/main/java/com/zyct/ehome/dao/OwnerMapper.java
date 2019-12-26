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

    /**
     * 通过业主id获取业主
     * @param ownerId
     * @return
     */
    Owner getOwnerByOwnerId(String ownerId);

    /**
     * 修改业主所属业委会和入职时间
     * @param ownerList
     */
    void updateCommitteeIdAndOfficeTrimeById(List<Owner> ownerList);

    /**
     * 设置所属业委会为空
     * @param committeeId
     */
    void setCommitteeIdIsNullByCommitteeId(String committeeId);
     /*
     * 修改业认证状态
     * @param owner
     * @return
     */
    void updateOwnerFlag( Owner owner);
}
