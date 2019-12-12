package com.zyct.ehome.service;

import com.zyct.ehome.entity.Admin;
import com.zyct.ehome.entity.Apply;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/11 15:04
 * Email 1945282561@qq.com
 */
public interface ApplyManageService {

    /**
     * 获取当前登录管理员能查看的申请列表
     * @param admin
     * @return
     */
    public List<Apply> getCurrentAdminExamineList(Admin admin);

    /**
     * 通过id获取审批信息
     * @param applyId
     * @return
     */
    Apply getApply(String applyId);
}
