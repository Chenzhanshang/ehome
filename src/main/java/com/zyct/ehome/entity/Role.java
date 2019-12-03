package com.zyct.ehome.entity;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private String roleId;

    private String roleName;

    private List<Admin> admins;

    private List<Promise> promises;
}