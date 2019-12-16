package com.zyct.ehome.service;

import com.zyct.ehome.entity.Candidate;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/16 10:44
 * Email 1945282561@qq.com
 */
public interface CandidateService {

    /**
     * 获取候选人列表
     * @return
     * @param communityId
     */
    List<Candidate> candidateList(String communityId);
}
