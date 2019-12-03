package com.zyct.ehome.entity;

import lombok.Data;

@Data
public class File {
    private String fileId;

    private String fileName;

    private String filePath;

    private Apply apply;
}