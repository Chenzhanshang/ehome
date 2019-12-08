package com.zyct.ehome.controller;

import com.zyct.ehome.utils.GenerateAdminAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CZS
 * CreateTime 2019/12/6 12:26
 * Email 642125256@qq.com
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    GenerateAdminAccount generateAdminAccount;
    @RequestMapping("/testGenerateAccount")
    @ResponseBody
    public void testGenerateAccount(){
        generateAdminAccount.generateAccount();
    }
}