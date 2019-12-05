package com.zyct.ehome.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class Admin implements Serializable {
    private String adminId;

    private String adminAccount;

    private String adminPassword;

    private Region region;

    private Set<Role> roles;
}