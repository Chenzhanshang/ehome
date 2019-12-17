package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Apply;
import com.zyct.ehome.entity.LeaveAudit;
import com.zyct.ehome.utils.AuditEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CZS
 * CreateTime 2019/12/16 15:34
 * Email 642125256@qq.com
 */
@Mapper
public interface AuditMapper {

    /**
     * 插入申请完成信息
     * @author CZS
     * @param auditEntity
     * @return
     */
    void insertAudit(AuditEntity auditEntity);


    /**
     * 更新申请信息
     * @author CZS
     * @param auditEntity
     * @return
     */
    void updateApply(AuditEntity auditEntity);

}
