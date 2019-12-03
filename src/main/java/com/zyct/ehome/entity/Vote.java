package com.zyct.ehome.entity;

import lombok.Data;

@Data
public class Vote  {
    private String issueId;
    private String ownerId;
    private Integer voteFlag;


}