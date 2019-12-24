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

    /**
     * 通过申请id查询当前节点
     * @author CZS
     * @param applyId
     * @return
     */
    Integer findFlowNodeByApplyId(String applyId);

    /**
     * 通过当前节点查询后一节点
     * @author CZS
     * @param flowNode
     * @param flowId
     * @return
     */
    Integer findNextNodeByFlowNodeAndFlowId(Integer flowNode, Integer flowId);

    /**
     * 根据申请id查询节点类型表的flow_id
     * @author CZS
     * @param applyId
     * @return
     */
    Integer findFlowIdByApplyId(String applyId);

}
