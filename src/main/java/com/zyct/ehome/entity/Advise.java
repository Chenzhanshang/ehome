package com.zyct.ehome.entity;

import lombok.Data;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-30 10:22
 */
@Data
public class Advise {
    private Integer adviseId;
    private String communityId;
    private String ownerId;
    private Integer type;
    private String department;
    private String content;
    private String createTime;
}
