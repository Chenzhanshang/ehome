package com.zyct.ehome.service;

import com.zyct.ehome.dto.FixDto;
import com.zyct.ehome.entity.Fix;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-23 10:06
 */
public interface TenService {

    /**
     * 插入维修信息
     * @param fix
     */
    void insertFix(Fix fix);

    /**
     * 通过小区id查询待维修列表
     * @param communityId
     * @return
     */
    List<FixDto> fixList(String communityId);

    /**
     * 更新维修信息
     * @param fix
     */
    void updateFix(Fix fix);


}
