package com.zyct.ehome.dto;

import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.Manager;
import lombok.Data;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-23 16:50
 */
@Data
public class ManagerDto {
    private String managerId;
    private String communityId;
    private String account;
    private String password;
    private Integer type;
    private Long loginTime;
    private Community community;

}
