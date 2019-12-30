package com.zyct.ehome.service;

import com.zyct.ehome.entity.Committee;

/**
 * @author JGZ
 * CreateTime 2019/12/25 14:21
 * Email 1945282561@qq.com
 */
public interface CommitteeManageService {
    /**
     * 创建业委会
     * @param communityId
     * @param count
     */
    void createCommittee(String communityId, Integer count);

    /**
     * 获取小区的业委会
     * @param communityId
     * @return
     */
    Committee getCommitteeById(String communityId);
}
