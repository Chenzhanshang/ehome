package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.utils.ErrorEnum;
import com.zyct.ehome.utils.ResponseMessage;
import com.zyct.ehome.utils.SuccessEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
                //返回登录成功信息
                return new ResponseMessage(SuccessEnum.S_LOGINSUCCESS.getSuccessCode()
                        ,SuccessEnum.S_LOGINSUCCESS.getSuccessMsg());
            }
            catch (UnknownAccountException e){
                //返回用户不存在
                return new ResponseMessage(ErrorEnum.E_UNKNOWACCOUNT.getErrorCode(),
                        ErrorEnum.E_UNKNOWACCOUNT.getErrorMsg());
            }
            catch (AuthenticationException e) {
                //返回密码错误信息
                return new ResponseMessage(ErrorEnum.E_PASSWORDERROR.getErrorCode(),
                        ErrorEnum.E_PASSWORDERROR.getErrorMsg());
            }
        }
        else{
            //如果用户已经登录则无需执行登录轮逻辑，直接返回登录成功
            return new ResponseMessage(SuccessEnum.S_LOGINED.getSuccessCode()
                    ,SuccessEnum.S_LOGINED.getSuccessMsg());
        }

    }

    @RequiresPermissions("查看")
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public
    @ResponseBody String hello(){
        return "hello";
    }

    @RequiresPermissions("删除")
    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    public
    @ResponseBody String hello2(){
        return "hello";
    }


}
