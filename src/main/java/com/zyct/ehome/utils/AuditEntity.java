package com.zyct.ehome.utils;

import com.sun.javafx.util.TempState;
import com.zyct.ehome.entity.Admin;

/**
 * 处理申请信息的封装
 * @author CZS
 * CreateTime 2019/12/16 15:50
 * Email 642125256@qq.com
 */
public class AuditEntity {
    private String auditId;

    private Integer applyState;

    private String adminName;

    private Integer auditState;

    private String auditInfo;

    private String applyId;

    private String adminId;

    private Integer flowNode;

    private String fileName;

    private String filePath;

    /**
     * 审批时间
     */
    private Long auditDate;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

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

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public Integer getFlowNode() {
        return flowNode;
    }

    public void setFlowNode(Integer flowNode) {
        this.flowNode = flowNode;
    }

    public Integer getApplyState() {
        return applyState;
    }

    public void setApplyState(Integer applyState) {
        this.applyState = applyState;
    }

    @Override
    public String toString() {
        return "AuditEntity{" +
                "auditId='" + auditId + '\'' +
                ", applyState=" + applyState +
                ", adminName='" + adminName + '\'' +
                ", auditState=" + auditState +
                ", auditInfo='" + auditInfo + '\'' +
                ", applyId='" + applyId + '\'' +
                ", adminId='" + adminId + '\'' +
                ", flowNode=" + flowNode +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", auditDate=" + auditDate +
                '}';
    }
}
