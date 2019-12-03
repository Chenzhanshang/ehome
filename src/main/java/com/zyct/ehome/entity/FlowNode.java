package com.zyct.ehome.entity;

import lombok.Data;

@Data
public class FlowNode {
    private Integer flowNodeId;

    private String flowNodeName;

    private Role role;
}