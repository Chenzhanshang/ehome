package com.zyct.ehome.dto;

import lombok.Data;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-11 11:45
 */
@Data
public class CommunityDto {
    private String communityId;

    private String communityName;

    private String communityInfo;

    private Integer isSelected;
}
