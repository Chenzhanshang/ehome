package com.zyct.ehome.entity;

import lombok.Data;

import java.util.List;

@Data
public class Owner {
    private String ownerId;

    private String ownerName;

    private String ownerPhone;

    private String ownerIdNumber;

    private String ownerWeixinId;

    private Long ownerOfficeTrime;

    private Committee committee;

    private List<Room> rooms;

    private String avatar;

    private String avatarUrl;

    private Integer authenticationFlag;


}