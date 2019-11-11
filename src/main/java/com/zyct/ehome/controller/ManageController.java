package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Manage;
import com.zyct.ehome.realm.UserRealm;
import com.zyct.ehome.utils.ResponseMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JGZ
 * @Classname ManageController
 * @Date 2019/11/4 20:49
 * @Email 1945282561@qq.com
 */
@Controller
public class ManageController {

    @Autowired
    private UserRealm userRealm;

    @RequestMapping("/user/login")
    public @ResponseBody ResponseMessage login(@RequestBody Manage manage){
        Subject subject = SecurityUtils.getSubject();
        ResponseMessage responseMessage = null;
        //如果当前用户未通过验证则
        if (!subject.isAuthenticated()){
            UsernamePasswordToken usernamePasswordToken =
                    new UsernamePasswordToken(manage.getLoginName(),manage.getPassword());
            try {
                //登陆
                subject.login(usernamePasswordToken);
                //成功返回状态0
                responseMessage = new ResponseMessage("0","登陆成功");
            }catch (UnknownAccountException e){
                responseMessage = new ResponseMessage("-3",e.getMessage());
            }catch (LockedAccountException e){
                responseMessage = new ResponseMessage("-2",e.getMessage());
            }catch (AuthenticationException e){
                responseMessage = new ResponseMessage("-1","密码错误");
            }
        }

        return responseMessage;
    }



    public UserRealm getUserRealm() {
        return userRealm;
    }

    public void setUserRealm(UserRealm userRealm) {
        this.userRealm = userRealm;
    }
}
