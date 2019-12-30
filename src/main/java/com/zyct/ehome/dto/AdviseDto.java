package com.zyct.ehome.dto;

import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.Owner;
import lombok.Data;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-30 10:24
 */
@Data
public class AdviseDto {
    private Integer adviseId;
    private String communityId;
    private String ownerId;
    private Integer type;
    private String department;
    private String content;
    private String createTime;
    private String name;
    private String phone;
}
