package com.zyct.ehome.controller;

import com.zyct.ehome.dto.FixDto;
import com.zyct.ehome.entity.Fix;
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



}
