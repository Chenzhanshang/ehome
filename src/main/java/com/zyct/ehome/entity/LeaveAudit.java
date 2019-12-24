package com.zyct.ehome.entity;

import lombok.Data;

@Data
public class LeaveAudit {
    private String auditId;

    private String adminName;

    private Integer auditState;

    private String auditInfo;
    /**
     * 审批时间
     */
    private Long auditDate;

    private Apply apply;

    private FlowNode flowNode;

    private Admin admin;

}