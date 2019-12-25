package com.zyct.ehome.controller;

import com.zyct.ehome.dto.FixDto;
import com.zyct.ehome.entity.Fix;
import com.zyct.ehome.entity.Notice;
import com.zyct.ehome.entity.Ten;
import com.zyct.ehome.service.TenService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-23 09:55
 */
@RequestMapping("/ten")
@Controller
public class TenController {


    @Autowired
    private TenService tenService;

    /**
     * 用户进行维修
     * @param fix
     * @return
     */
    @RequestMapping("/fix")
    @ResponseBody
    public ResponseMessage fix(@RequestBody Fix fix){
        String uuid = UUID.randomUUID().toString();
        fix.setFixId(uuid);
        fix.setFlag(0);
        System.out.println(fix.toString());
        tenService.insertFix(fix);
        return new ResponseMessage("0","报修成功");
    }

    /**
     * 获取待维修列表
     * @param communityId
     * @return
     */
    @RequestMapping(value = "/fixList",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage fixList(@RequestParam("communityId")String communityId){
        //通过小区id查询待维修列表
        List<FixDto> fixDtoList = tenService.fixList(communityId);
        ResponseMessage responseMessage = new ResponseMessage("0", "请求成功");
        Map<String,Object> map = new HashMap<>();
        map.put("fixDtoList",fixDtoList);
        responseMessage.setData(map);
        return responseMessage;
    }

    /**
     * 获取已维修列表，历史维修列表
     * @param communityId
     * @return
     */
    @RequestMapping(value = "/fixedList",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage fixedList(@RequestParam("communityId")String communityId){
        //通过小区id查询待维修列表
        List<FixDto> fixedDtoList = tenService.fixedList(communityId);
        ResponseMessage responseMessage = new ResponseMessage("0", "请求成功");
        Map<String,Object> map = new HashMap<>();
        map.put("fixedDtoList",fixedDtoList);
        responseMessage.setData(map);
        return responseMessage;
    }

    /**
     * 已通知维修人员上门维修
     * @param fix
     * @return
     */
    @RequestMapping("/doJob")
    @ResponseBody
    public ResponseMessage doJob(@RequestBody Fix fix){
        //将维修状态更新
        fix.setFlag(1);
        tenService.updateFix(fix);
        return new ResponseMessage("0","操纵成功");
    }

    /**
     * 发布公告
     * @param notice
     * @return
     */
    @RequestMapping("/putNotice")
    @ResponseBody
    public ResponseMessage putNotice(@RequestBody Notice notice){

        return null;
    }



}
