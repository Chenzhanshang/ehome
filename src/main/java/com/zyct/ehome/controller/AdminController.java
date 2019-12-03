package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.utils.ResponseMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JGZ
 * CreateTime 2019/12/3 14:29
 * Email 1945282561@qq.com
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * 用户登录控制层
     * @param admin
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage login(Admin admin){
        System.out.println(admin);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(admin.getAdminAccount(),admin.getAdminPassword());
        if (!subject.isAuthenticated()){
            //如果用户未登录，执行登录逻辑
            try {
                // 执行认证提交
                subject.login(usernamePasswordToken);
                return new ResponseMessage("success","登录成功");
            }
            catch (UnknownAccountException e){
                return new ResponseMessage("error",e.getMessage());
            }
            catch (AuthenticationException e) {
//                e.printStackTrace();
                return new ResponseMessage("error","密码错误");
            }
        }
        else{
            //如果用户已经登录则无需执行登录轮逻辑
            return new ResponseMessage("success","用户已登陆，无需重复登录");
        }

    }


}
