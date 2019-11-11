package com.zyct.ehome.entity;

import lombok.Data;

import java.util.List;

/**
 * @author JGZ
 * @Classname Permissions
 * @Date 2019/11/4 20:03
 * @Email 1945282561@qq.com
 */
@Data
public class Permissions {
    private String permissionsId;
    private String permissionsName;
    private List<Role> roles;
}
