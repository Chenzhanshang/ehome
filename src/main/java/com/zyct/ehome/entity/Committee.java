package com.zyct.ehome.entity;

import lombok.Data;

import java.util.List;
@Data
public class Committee {
    private String committeeId;

    private Integer committeePeopleNumber;

    private Integer committeeTerm;

    private Community community;

    private List<Issue> issues;

}