package com.zyct.ehome.utils;

import com.zyct.ehome.entity.Region;
import com.zyct.ehome.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/6 16:45
 * Email 1945282561@qq.com
 */
@Component
public class InitRegionToCache implements ApplicationRunner {

    @Autowired
    private RegionService regionService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<RegionTree> regionTrees = regionService.regionList();
        List<RegionTree> regionTreesForThree = regionService.regionListForThree();
        redisUtil.set("regionList",regionTrees);
        redisUtil.set("regionTreesForThree",regionTreesForThree);

    }
}
