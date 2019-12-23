package com.zyct.ehome.dto;

import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.Owner;
import lombok.Data;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-23 10:15
 */
@Data
public class FixDto {
    private String fixId;
    private String communityId;
    private String ownerId;
    private String type;
    private String date;
    private String time;
    private String text;
    private Integer flag;
    private Community community;
    private Owner owner;
}
