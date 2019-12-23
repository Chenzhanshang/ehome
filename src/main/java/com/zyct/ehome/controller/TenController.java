package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Fix;
import com.zyct.ehome.entity.Ten;
import com.zyct.ehome.service.TenService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

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



}
