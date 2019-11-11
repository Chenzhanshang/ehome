package com.zyct.ehome.entity;

import lombok.Data;

import java.util.List;

/**
 * @author JGZ
 * @Classname Manage
 * @Date 2019/11/4 20:01
 * @Email 1945282561@qq.com
 */
@Data
public class Manage {
    private String manageId;
    private String loginName;
    private String password;
    private List<Role> roles;
}
