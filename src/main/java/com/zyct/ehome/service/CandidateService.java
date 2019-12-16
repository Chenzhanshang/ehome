package com.zyct.ehome.service;

import com.zyct.ehome.dto.AddCandidateDto;
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

    /**
     * 添加候选人
     * @param addCandidateDto
     * @return
     */
    List<Candidate> addCandidate(AddCandidateDto addCandidateDto);

    /**
     * 删除候选人
     * @param candidateId
     */
    void deleteCandidate(String candidateId);
}
