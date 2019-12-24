package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.service.AdminService;
import com.zyct.ehome.service.Auditservice;
import com.zyct.ehome.utils.AuditEntity;
import com.zyct.ehome.utils.ResponseMessage;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CZS
 * CreateTime 2019/12/16 16:13
 * Email 642125256@qq.com
 */

@Controller
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    private Auditservice auditservice;

    @Autowired
    private AdminService adminService;

    /**
     * 处理处理申请的请求
     * @author CZS
     * @param auditEntity
     * @return
     */
    @RequestMapping(value = "/dispose", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage dispose(@RequestBody AuditEntity auditEntity){
        //获取当前管理员账号密码
        Admin admin = (Admin) SecurityUtils.getSubject().getPrincipal();
        //根据管理员账号得到管理员
        admin = adminService.getAdminByAccount(admin.getAdminAccount());
        //获得管理员id
        String adminId = admin.getAdminId();

        auditEntity.setAdminId(adminId);
        try {
            auditservice.insertAudit(auditEntity);
            return new ResponseMessage("1","提交成功");
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("0","提交失败");
        }
    }
}
