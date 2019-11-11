package com.zyct.ehome.entity;

import lombok.Data;

import java.util.List;

/**
 * @author JGZ
 * @Classname Role
 * @Date 2019/11/4 20:02
 * @Email 1945282561@qq.com
 */
@Data
public class Role {
    private String roleId;
    private String roleName;
    private List<Manage> manages;
    private List<Permissions> permissions;
}
