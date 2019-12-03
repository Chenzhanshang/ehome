package com.zyct.ehome.entity;

import lombok.Data;

import java.util.List;

@Data
public class Promise {
    private String promiseId;

    private String promiseName;

    private List<Role> roles;
}