package com.zyct.ehome.service;

import com.zyct.ehome.utils.RegionTree;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/6 15:20
 * Email 1945282561@qq.com
 */
public interface RegionService {

    /**
     * 获取地区列表
     * @return
     */
    public List<RegionTree> regionList();

    /**
     * 获取三级地区列表
     * @return
     */
    public List<RegionTree> regionListForThree();
}
