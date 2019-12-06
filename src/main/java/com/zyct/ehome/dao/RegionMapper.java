package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Region;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/6 15:24
 * Email 1945282561@qq.com
 */
@Mapper
public interface RegionMapper {

    /**
     * 获取一级地区列表
     * @return
     */
    public List<Region> selectOneLevelList();
    /**
     * 获取二地区列表
     * @return
     */
    public List<Region> selectTwoLevelList();
    /**
     * 获取三级地区列表
     * @return
     */
    public List<Region> selectThreeLevelList();
    /**
     * 获取s四级地区列表
     * @return
     */
    public List<Region> selectFourLevelList();
}
