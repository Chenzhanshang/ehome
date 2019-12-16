package com.zyct.ehome.entity;

import lombok.Data;

@Data
public class LeaveAudit {
    private Integer auditId;

    private String adminName;

    private Integer auditState;

    private String auditInfo;
    /**
     * 审批时间
     */
    private Long auditDate;

    private File file;

    private FlowNode flowNode;

    private Admin admin;

}