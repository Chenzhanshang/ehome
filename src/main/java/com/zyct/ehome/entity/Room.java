package com.zyct.ehome.entity;

import lombok.Data;

@Data
public class Room {
    private String roomId;

    private String roomName;

    private Long roomCertificationTime;

    private Owner owner;

    private House house;


}