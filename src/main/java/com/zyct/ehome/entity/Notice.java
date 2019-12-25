package com.zyct.ehome.entity;

import lombok.Data;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-25 09:31
 */
@Data
public class Notice {
    private Integer noticeId;
    private String title;
    private String content;
    private Integer type;
    private String createTime;
    private String communityId;

}
