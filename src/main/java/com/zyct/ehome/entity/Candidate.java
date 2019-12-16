package com.zyct.ehome.entity;

import lombok.Data;

/**
 * @author JGZ
 * CreateTime 2019/12/16 9:41
 * Email 1945282561@qq.com
 */
@Data
public class Candidate {
    private String candidateId;
    private String candidateHeadPortrait;
    private String candidateHeadPath;
    private Integer candidatePoll;
    private Long candidateCreateTime;

    private Owner owner;
    private Community community;
}
