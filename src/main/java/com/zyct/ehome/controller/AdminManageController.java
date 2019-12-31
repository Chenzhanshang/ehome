package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.entity.Role;
import com.zyct.ehome.service.AdminManageService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CZS
 * CreateTime 2019/12/10 14:41
 * Email 642125256@qq.com
 */
@Controller
@RequestMapping("/admin")
public class AdminManageController {
    @Autowired
    private AdminManageService adminManageService;

    @RequestMapping("/adminList")
    @ResponseBody
    public List<Admin> getAdminList(){
        List<Admin> list = adminManageService.getAdminList();
        System.out.println(list);
        return list;
    }

    @RequestMapping("/deleteAdmin/{adminId}")
    public @ResponseBody
    ResponseMessage deleteAdmin(@PathVariable("adminId") String adminId){

        try{
            adminManageService.delectAdminRole(adminId);
            adminManageService.delectAdmin(adminId);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("1","删除失败!");
        }

        return new ResponseMessage("0","删除成功!");

    }

    @RequestMapping(value = "/updateAdmin",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage updateAdmin(@RequestBody Admin admin){
        System.out.println(admin);

        try{
            adminManageService.updateAdmin(admin);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("1","修改失败！");
        }
        admin = adminManageService.getAdminByAdminAccount(admin.getAdminId());
        ResponseMessage responseMessage = new ResponseMessage("0","修改成功！");
        responseMessage.getData().put("admin",admin);
        return responseMessage;

    }

    @RequestMapping("/getAllRole")
    public @ResponseBody
    ResponseMessage getAllRole(){
        try{
            List<Role> roles = adminManageService.getAllRole();
            ResponseMessage responseMessage = new ResponseMessage("0","获取角色列表成功");
            responseMessage.getData().put("roles",roles);
            return responseMessage;
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","获取角色列表失败");
        }

    }
}
