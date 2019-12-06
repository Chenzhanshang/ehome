package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Region;
import com.zyct.ehome.service.RegionService;
import com.zyct.ehome.utils.RedisUtil;
import com.zyct.ehome.utils.RegionTree;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/6 15:18
 * Email 1945282561@qq.com
 */
@Controller
public class RegionController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private RedisUtil redisUtil;
    /**
     * 获取地区列表
     * @return
     */
    @RequestMapping(value = "/admin/regionList",method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage regionList(){
        Object regionList = redisUtil.get("regionList");
        ResponseMessage responseMessage ;
//        System.out.println(regionTrees);
        if (regionList != null){

            responseMessage = new ResponseMessage("0", "请求成功");
            responseMessage.getData().put("regionList",regionList);
            return responseMessage;
        }
        responseMessage = new ResponseMessage("-1","请求失败");
        return responseMessage;
    }
}
