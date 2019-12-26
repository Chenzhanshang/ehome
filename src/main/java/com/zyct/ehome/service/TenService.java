package com.zyct.ehome.service;

import com.zyct.ehome.dto.FixDto;
import com.zyct.ehome.entity.Fix;
import com.zyct.ehome.entity.Notice;

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
     * 通过小区id查询已维修列表，历史维修列表
     * @param communityId
     * @return
     */
    List<FixDto> fixedList(String communityId);

    /**
     * 更新维修信息
     * @param fix
     */
    void updateFix(Fix fix);


    /**
     * 发布公告
     * @param notice
     */
    void putNotice(Notice notice);

    /**
     * 通过小区id和类型获取通知列表
     * @param communityId
     * @param type
     * @return
     */
    List<Notice> getNoticeListByType(String communityId, Integer type);

    /**
     * 删除公告
     * @param notice
     */
    void deleteNotice(Notice notice);

    /**
     * 修改公告
     * @param notice
     */
    void updateNotice(Notice notice);
}
