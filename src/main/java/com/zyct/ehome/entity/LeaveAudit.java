package com.zyct.ehome.entity;

public class LeaveAudit {
    private Integer auditId;

    private String adminName;

    private Boolean auditState;

    private String auditInfo;
    /**
     * 审批时间
     */
    private Long auditDate;

    private File file;

    private FlowNode flowNode;

    private Admin admin;

}