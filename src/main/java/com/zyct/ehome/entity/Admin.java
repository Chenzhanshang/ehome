package com.zyct.ehome.entity;

import lombok.Data;

import java.util.List;

@Data
public class Admin {
    private String adminId;

    private String adminAccount;

    private String adminPassword;

    private Region region;

    private List<Role> roles;
}