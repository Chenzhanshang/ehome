package com.zyct.ehome.entity;

import lombok.Data;

@Data
public class FlowNode {
    private Integer flowNodeId;

    private String flowNodeName;

    private Role role;

    public FlowNode() {
    }

    public FlowNode(Integer flowNodeId, String flowNodeName, Role role) {
        this.flowNodeId = flowNodeId;
        this.flowNodeName = flowNodeName;
        this.role = role;
    }

}