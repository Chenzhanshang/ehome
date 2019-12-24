package com.zyct.ehome.service;

import com.zyct.ehome.entity.LeaveAudit;
import com.zyct.ehome.utils.AuditEntity;

import java.util.List;

/**
 * @author CZS
 * CreateTime 2019/12/16 15:37
 * Email 642125256@qq.com
 */
public interface Auditservice {
    /**
     * 插入申请完成信息
     * @author CZS
     * @param auditEntity
     * @return
     */
    void insertAudit(AuditEntity auditEntity);

    /**
     * 通过adminid获取该用户所有审批过的记录
     * @param adminId
     * @return
     */
    List<LeaveAudit> getHistoryExamineListByAdminId(String adminId);

    /**
     * 通过id获取审批信息
     * @param auditId
     * @return
     */
    LeaveAudit getAuditInfoByAuditId(String auditId);
}
