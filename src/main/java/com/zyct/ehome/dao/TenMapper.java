package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Advise;
import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.Fix;
import com.zyct.ehome.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-23 10:08
 */
@Mapper
public interface TenMapper {

    /**
     * 插入一条维修信息
     * @param fix
     */
    void insertFix(Fix fix);

    /**
     * 通过小区id查询维修列表
     * @param communityId
     * @return
     */
    List<Fix> selectFixListByCommunityId(String communityId);

    /**
     * 更新维修状态
     * @param fix
     */
    void updateFix(Fix fix);

    List<Fix> selectFixedListByCommunityId(String communityId);

    /**
     * 将通知信息插入数据库
     * @param notice
     */
    void insertNotice(Notice notice);

    /**
     * 通过小区id查询通知列表
     * @param communityId
     * @return
     */
    List<Notice> selectNoticeListByCommunityId(String communityId);

    /**
     * 通过小区id和通知类型查询通知列表
     * @param communityId
     * @param type
     * @return
     */
    List<Notice> selectNoticeListByCommunityIdAndType(@Param("communityId") String communityId,@Param("type") Integer type);


    /**
     * 删除公告
     * @param notice
     */
    void deleteNotice(Notice notice);

    /**
     * 更新公告
     * @param notice
     */
    void updateNotice(Notice notice);

    /**
     * 插入一条投诉信息
     * @param advise
     */
    void insertAdvise(Advise advise);

    /**
     * 通过小区id获取投诉列表
     * @param communityId
     * @return
     */
    List<Advise> selectAdviseListByCommunityId(String communityId);
}
