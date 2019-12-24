package com.zyct.ehome.entity;

import lombok.Data;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-23 15:00
 */
@Data
public class Manager {
    private String managerId;
    private String communityId;
    private String account;
    private String password;
    private Integer type;
    private Long loginTime;
}
