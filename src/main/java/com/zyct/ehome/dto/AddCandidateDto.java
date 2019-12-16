package com.zyct.ehome.dto;

import lombok.Data;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/16 15:52
 * Email 1945282561@qq.com
 */
@Data
public class AddCandidateDto {
    private List<String> ownerId;
    private String communityId;
}
