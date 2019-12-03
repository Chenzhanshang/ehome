package com.zyct.ehome.entity;

import lombok.Data;

@Data
public class Community {
    private String communityId;

    private String communityName;

    private String communityInfo;

    private Ten ten;

    private Region region;


}