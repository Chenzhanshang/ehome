package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.utils.GenerateAccountEntity;
import com.zyct.ehome.utils.GenerateAdminAccount;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author CZS
 * CreateTime 2019/12/6 12:26
 * Email 642125256@qq.com
 */
@Controller
@RequestMapping("/generate")
public class GenerateAdminAccountController {
    @Autowired
    GenerateAdminAccount generateAdminAccount;

    @RequestMapping(value = "/generateAccount",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage GenerateAccount(@RequestBody GenerateAccountEntity generateAccountEntity){
        Admin admin = generateAdminAccount.generateAccount(generateAccountEntity.getAccountType(),generateAccountEntity.getRegionId());
        System.out.println(generateAccountEntity);

        if(admin != null){
            ResponseMessage responseMessage = new ResponseMessage("0","生成管理账号成功");
            responseMessage.getData().put("admin",admin);
            return responseMessage;
        }
        else {
            return new ResponseMessage("-1","生成管理账号失败");
        }
    }
}