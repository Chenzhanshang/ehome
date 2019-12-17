package com.zyct.ehome.utils;

import com.sun.javafx.util.TempState;

/**
 * 处理申请信息的封装
 * @author CZS
 * CreateTime 2019/12/16 15:50
 * Email 642125256@qq.com
 */
public class AuditEntity {
    private String auditId;

    private String adminName;

    private Integer auditState;

    private String auditInfo;

    private String applyId;

    private String flowNode;

    /**
     * 审批时间
     */
    private Long auditDate;

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }


    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Integer getAuditState() {
        return auditState;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }

    public String getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(String auditInfo) {
        this.auditInfo = auditInfo;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public Long getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Long auditDate) {
        this.auditDate = auditDate;
    }

    @Override
    public String toString() {
        return "AuditEntity{" +
                "auditId=" + auditId +
                ", adminName='" + adminName + '\'' +
                ", auditState=" + auditState +
                ", auditInfo='" + auditInfo + '\'' +
                ", applyId='" + applyId + '\'' +
                ", flowNode='" + flowNode + '\'' +
                ", auditDate=" + auditDate +
                '}';
    }

    public String getFlowNode() {
        return flowNode;
    }

    public void setFlowNode(String flowNode) {
        this.flowNode = flowNode;
    }
}
