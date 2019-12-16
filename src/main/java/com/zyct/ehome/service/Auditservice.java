package com.zyct.ehome.service;

import com.zyct.ehome.entity.LeaveAudit;
import com.zyct.ehome.utils.AuditEntity;

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
}
