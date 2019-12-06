package com.zyct.ehome.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author JGZ
 * CreateTime 2019/12/3 11:57
 * Email 1945282561@qq.com
 */
@Data
public class Region implements Serializable {
    private Long regionId;
    private String createDate;
    private String modifyDate;
    private Long version;
    private Integer orders;
    private String fullName;
    private Integer grade;
    private String name;
    private String treePath;
    private Long parent;
}
