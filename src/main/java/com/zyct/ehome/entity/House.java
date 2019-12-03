package com.zyct.ehome.entity;

import lombok.Data;

import java.util.List;

@Data
public class House {
    private String houseId;

    private String houseName;

    private Community community;

    private List<Room> rooms;
}