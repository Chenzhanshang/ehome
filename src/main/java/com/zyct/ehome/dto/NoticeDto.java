package com.zyct.ehome.dto;

import lombok.Data;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-25 09:41
 */
@Data
public class NoticeDto {
    private Integer noticeId;
    private String title;
    private String content;
    private ManagerDto managerDto;
}
