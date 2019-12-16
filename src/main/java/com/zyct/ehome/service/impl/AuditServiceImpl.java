package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.AuditMapper;
import com.zyct.ehome.entity.LeaveAudit;
import com.zyct.ehome.service.Auditservice;
import com.zyct.ehome.utils.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author CZS
 * CreateTime 2019/12/16 15:37
 * Email 642125256@qq.com
 */
@Service
public class AuditServiceImpl implements Auditservice {

    @Autowired
    private AuditMapper auditMapper;
    @Override
    public void insertAudit(AuditEntity auditEntity) {
        auditEntity.setAuditId(UUID.randomUUID().toString().replaceAll("-",""));

        //获取当前时间毫秒值
        auditEntity.setAuditDate(System.currentTimeMillis());
        //调用存储
        auditMapper.insertAudit(auditEntity);
        //更新申请表
        auditMapper.updateApply(auditEntity);
    }
}
