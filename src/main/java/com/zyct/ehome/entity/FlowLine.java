package com.zyct.ehome.entity;

import lombok.Data;

@Data
public class FlowLine {
    private Integer flowLineId;

    private Integer prevNodeId;

    private Integer nextNodeId;

}