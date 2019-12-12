package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.entity.Region;
import com.zyct.ehome.service.AdminService;
import com.zyct.ehome.utils.ErrorEnum;
import com.zyct.ehome.utils.ResponseMessage;
import com.zyct.ehome.utils.SuccessEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    private AdminService adminService;
    /**
     * 用户登录控制层
     * @param admin
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage login(@RequestBody Admin admin){
        System.out.println(admin);
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            //如果用户未登录，执行登录逻辑
            try {
                UsernamePasswordToken usernamePasswordToken =
                        new UsernamePasswordToken(admin.getAdminAccount(),admin.getAdminPassword());
                // 执行认证提交
                subject.login(usernamePasswordToken);
                //返回登录成功信息
                ResponseMessage responseMessage = new ResponseMessage(SuccessEnum.S_LOGIN_SUCCESS);
                //获取用户的地址信息
                Admin principal = (Admin) subject.getPrincipal();
                Region region = principal.getRegion();
                //如果不为空
                responseMessage.getData().put("region",
                        region.getFullName()==null?"":region.getFullName());
                //将角色返回
                responseMessage.getData().put("roles",
                        principal.getRoles());
                responseMessage.getData().put("adminModify",
                        principal.getAdminModify());
                System.out.println( principal.getRoles());
                return responseMessage;
            }
            catch (UnknownAccountException e){
                e.printStackTrace();
                //返回用户不存在
                return new ResponseMessage(ErrorEnum.E_UNKNOWN_ACCOUNT);
            }
            catch (IncorrectCredentialsException e){
                e.printStackTrace();
                //返回密码错误信息
                return new ResponseMessage(ErrorEnum.E_PASSWORD_ERROR);
            }
            catch (AuthenticationException e) {
                e.printStackTrace();
                //返回密码错误信息
                return new ResponseMessage(ErrorEnum.E_PASSWORD_ERROR);
            }
        }
        else{
            //如果用户已经登录则无需执行登录轮逻辑，直接返回登录成功
            return new ResponseMessage(SuccessEnum.S_LOGINED);
        }

    }

    /**
     * 管理员修改密码
     * @param admin
     * @return
     */
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage updatePassword(@RequestBody Admin admin){
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        //如果用户登录
        if(subject.isAuthenticated()){
            //获取用户信息
            Admin principal = (Admin) subject.getPrincipal();
            //设置用户id
            admin.setAdminId(principal.getAdminId());
            //设置用户账户
            admin.setAdminAccount(principal.getAdminAccount());
            try {
                //修改密码
                adminService.updatePasswordByAdminAccount(admin);
                //修改成功用户登出
                subject.logout();
                //返回信息
                return new ResponseMessage("0","修改密码成功");
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseMessage("-1","修改密码失败");
            }
        }
        else {
            //返回信息
            return new ResponseMessage(ErrorEnum.E_UNAUTHENTICATED);
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
