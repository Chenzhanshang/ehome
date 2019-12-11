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
        try{
            //获取缓存中的地区列表
            Object regionList = redisUtil.get("regionList");
            if (regionList != null){
                //返回数据
                ResponseMessage responseMessage = new ResponseMessage("0", "获取地区列表成功");
                responseMessage.getData().put("regionList",regionList);
                return responseMessage;
            }
            //如果等于空
            return new ResponseMessage("-1","获取地区列表失败");
        }catch (Exception e){
            e.printStackTrace();
            //抛出异常也返回获取失败
            return new ResponseMessage("-1","获取地区列表失败");
        }

    }
}
