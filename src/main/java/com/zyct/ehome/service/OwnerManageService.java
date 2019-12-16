package com.zyct.ehome.service;

import com.zyct.ehome.entity.Owner;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/16 11:09
 * Email 1945282561@qq.com
 */
public interface OwnerManageService {

    /**
     * 获取小区的所有业主
     * @param communityId
     * @return
     */
    List<Owner> unCandidateOwnerList(String communityId);
}
