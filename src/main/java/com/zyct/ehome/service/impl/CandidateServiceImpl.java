package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.CandidateMapper;
import com.zyct.ehome.entity.Candidate;
import com.zyct.ehome.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/16 10:47
 * Email 1945282561@qq.com
 */
@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    public List<Candidate> candidateList(String communityId) {
        return candidateMapper.getCandidateListByCommunityId(communityId);
    }
}
