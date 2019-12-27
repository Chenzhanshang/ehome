package com.zyct.ehome.dto;

import lombok.Data;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-27 15:15
 */
@Data
public class IssueDto {
    private String title;
    private String content;
    private String communityId;
    private Long startTime;
    private Long endTime;
}
