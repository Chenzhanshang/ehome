package com.zyct.ehome.entity;

import lombok.Data;

import java.util.List;

@Data
public class Ten {
    private String tenId;

    private String tenName;

    private String tenPhone;

    private List<Community> communities;

}