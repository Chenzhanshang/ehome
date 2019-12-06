package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.RegionMapper;
import com.zyct.ehome.entity.Region;
import com.zyct.ehome.service.RegionService;
import com.zyct.ehome.utils.RegionTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/6 15:23
 * Email 1945282561@qq.com
 */
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<RegionTree> regionList() {
        List<Region> oneLevelList = regionMapper.selectOneLevelList();
        List<Region> twoLevelList = regionMapper.selectTwoLevelList();
        List<Region> threeLevelList = regionMapper.selectThreeLevelList();
        List<Region> fourLevelList = regionMapper.selectFourLevelList();

        List<RegionTree> regionTrees = new ArrayList<>();

        for (Region region1:oneLevelList) {
            RegionTree t1 = new RegionTree(region1.getRegionId()
                    ,region1.getFullName());
            regionTrees.add(t1);

            for (Region region2:twoLevelList) {
                if (region2.getParent().equals(t1.getValue())){
                    RegionTree t2 = new RegionTree(region2.getRegionId()
                            ,region2.getFullName());
                    t1.getChildren().add(t2);

                    for (Region region3:threeLevelList) {
                        if (region3.getParent().equals(t2.getValue())){
                            RegionTree t3 = new RegionTree(region3.getRegionId()
                                    ,region3.getFullName());
                            t2.getChildren().add(t3);
                            for (Region region4:fourLevelList) {
                                if (region4.getParent().equals(t3.getValue())){
                                    RegionTree t4 = new RegionTree(region4.getRegionId()
                                            ,region4.getFullName());
                                    t4.setChildren(null);
                                    t3.getChildren().add(t4);
                                }
                            }
                        }

                    }

                }
            }
        }

//        System.out.println(regionTrees);

        return regionTrees;
    }
}
