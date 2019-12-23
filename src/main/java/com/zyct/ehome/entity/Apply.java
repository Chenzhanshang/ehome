package com.zyct.ehome.entity;

import lombok.Data;

import java.util.List;
@Data
public class Apply {
    private String applyId;

    private Integer applyState;

    private String roomId;

    private Owner owner;

    private Community community;

    private FlowNode flowNode;

    private House house;

    private Room room;

    private List<File> files;

    private Long createTime;

    private Flow flow;

}