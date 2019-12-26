package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.AuditMapper;
import com.zyct.ehome.dao.OwnerMapper;
import com.zyct.ehome.dao.RoomMapper;
import com.zyct.ehome.entity.Apply;
import com.zyct.ehome.entity.LeaveAudit;
import com.zyct.ehome.entity.Room;
import com.zyct.ehome.service.Auditservice;
import com.zyct.ehome.utils.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private OwnerMapper ownerMapper;


    @Override
    public void insertAudit(AuditEntity auditEntity) {
        auditEntity.setAuditId(UUID.randomUUID().toString().replaceAll("-",""));

        //如果为拒绝状态
        if(auditEntity.getAuditState() == 0){
            //设置当前申请的状态为-1（即拒绝）
            auditEntity.setApplyState(-1);
        }
        else{
            Integer flowId = auditMapper.findFlowIdByApplyId(auditEntity.getApplyId());
            //查询当前的申请对应的节点
            Integer flowNode = auditMapper.findFlowNodeByApplyId(auditEntity.getApplyId());
            //如果当前节点拥有下一节点
            if(auditMapper.findNextNodeByFlowNodeAndFlowId(flowNode, flowId) != null){
                //设置当前申请的节点为下一节点
                auditEntity.setFlowNode(auditMapper.findNextNodeByFlowNodeAndFlowId(flowNode, flowId));
                if(auditMapper.findNextNodeByFlowNodeAndFlowId(auditEntity.getFlowNode(), flowId) == null){
                    //设置申请状态为1（完成）
                    auditEntity.setApplyState(1);
                    if(flowId == 1) {
                        //获取申请信息
                        Apply apply = auditMapper.findApplyByApplyId(auditEntity.getApplyId());
                        roomMapper.updateRoomOwnerIdByRoomId(apply.getRoom().getRoomId(),
                                apply.getOwner().getOwnerId());
                        apply.getOwner().setAuthenticationFlag(1);
                        //设置用户为已认证
                        ownerMapper.updateOwnerFlag(apply.getOwner());

                    }
                }
            }

        }

        if(auditEntity.getApplyState() == null){
            auditEntity.setApplyState(0);
        }

        //获取当前时间毫秒值
        auditEntity.setAuditDate(System.currentTimeMillis());

        System.out.println(auditEntity);
        //调用存储
        auditMapper.insertAudit(auditEntity);
        //更新申请表
        auditMapper.updateApply(auditEntity);
    }


    @Override
    public List<LeaveAudit> getHistoryExamineListByAdminId(String adminId) {

        return auditMapper.getHistoryExamineListByAdminId(adminId);
    }


    @Override
    public LeaveAudit getAuditInfoByAuditId(String auditId) {
        return auditMapper.getAuditInfoByAuditId(auditId);
    }
}
