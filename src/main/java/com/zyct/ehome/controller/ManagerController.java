package com.zyct.ehome.controller;

import com.zyct.ehome.dto.ManagerDto;
import com.zyct.ehome.entity.Manager;
import com.zyct.ehome.service.ManagerService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-23 14:46
 */
@RequestMapping("/manager")
@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;


    /**
     * 用于业委会和物业登录
     * @param manager
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResponseMessage login(@RequestBody Manager manager){

        Boolean exits = managerService.isExits(manager);
        if (exits.equals(false)){
            System.out.println("用户不存在");
            return new ResponseMessage("-1","用户不存在");
        }else{
            Boolean login = managerService.login(manager);
            if (login.equals(false)){
                System.out.println("密码不正确");
                return new ResponseMessage("-1","密码不正确");
            }else {
                Map<String,Object> map = new HashMap<>();
                ManagerDto managerDto = managerService.getManager();
                map.put("managerDto",managerDto);
                ResponseMessage responseMessage = new ResponseMessage("0", "登录成功");
                responseMessage.setData(map);
                return responseMessage;
            }
        }

    }



}
