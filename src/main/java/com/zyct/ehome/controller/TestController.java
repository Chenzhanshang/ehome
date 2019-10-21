package com.zyct.ehome.controller;

import com.zyct.ehome.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JGZ
 * @Classname TestController
 * @Date 2019/10/21 10:22
 * @Email 1945282561@qq.com
 */
@Controller
public class TestController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/hello")
    public @ResponseBody
    String hello(){
        return "你好";
    }

}
