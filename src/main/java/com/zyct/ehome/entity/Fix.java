package com.zyct.ehome.entity;

import lombok.Data;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-23 09:56
 */
@Data
public class Fix {
    private String fixId;
    private String communityId;
    private String ownerId;
    private String type;
    private String date;
    private String time;
    private String text;
    private Integer flag;
    private String createTime;
    private String updateTime;
}
