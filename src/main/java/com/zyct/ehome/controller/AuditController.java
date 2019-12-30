package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.service.AdminService;
import com.zyct.ehome.entity.File;
import com.zyct.ehome.entity.LeaveAudit;
import com.zyct.ehome.service.Auditservice;
import com.zyct.ehome.utils.AuditEntity;
import com.zyct.ehome.utils.ErrorEnum;
import com.zyct.ehome.utils.RedisUtil;
import com.zyct.ehome.utils.ResponseMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 处理处理申请的请求,保存处理结果
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
        if(redisUtil.get("fileName") != null ){
            auditEntity.setFileName(redisUtil.get("fileName").toString());
        }
        if(redisUtil.get("filePath") != null){
            auditEntity.setFilePath(redisUtil.get("filePath").toString());
        }

        auditEntity.setAdminId(adminId);
        try {
            auditservice.insertAudit(auditEntity);
            return new ResponseMessage("1","提交成功");
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("0","提交失败");
        }
        finally {
            redisUtil.del("fileName","filePath");
        }
    }


    /**
     * 获取历史审批列表
     * @return
     */
    @RequestMapping(value = "/getHistoryExamineList",method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getHistoryExamineList(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            //如果用户登录
            try {
                //获取登录的用户
                Admin principal = (Admin) subject.getPrincipal();
                List<LeaveAudit> leaveAuditList = auditservice.getHistoryExamineListByAdminId(principal.getAdminId());
                ResponseMessage responseMessage = new ResponseMessage("0","获取成功");
                responseMessage.getData().put("AuditList",leaveAuditList);
                return responseMessage;
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseMessage("-1","获取失败");
            }
        }
        else{
            //如果用户未登录
            //返回用户未登录
            return new ResponseMessage(ErrorEnum.E_UNAUTHENTICATED);
        }
    }

    @RequestMapping(value = "/getAuditInfo/{auditId}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getAuditInfo(@PathVariable("auditId") String auditId, HttpServletRequest request){
        try {
            LeaveAudit leaveAudit = auditservice.getAuditInfoByAuditId(auditId);
            leaveAudit.setFilePath(leaveAudit.getFilePath() + leaveAudit.getFileName() );
            //文件路径
            if(leaveAudit.getApply()!=null){
                if(leaveAudit.getApply().getFiles()!=null){
                    for (File f:leaveAudit.getApply().getFiles()) {
                        f.setFilePath("http://localhost:8081" + request.getContextPath() + "/file/" + f.getFileName());
                    }
                }
            }
            ResponseMessage responseMessage = new ResponseMessage("0","获取成功");
            responseMessage.getData().put("leaveAudit",leaveAudit);
            return responseMessage;
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","获取失败");
        }
    }
}
