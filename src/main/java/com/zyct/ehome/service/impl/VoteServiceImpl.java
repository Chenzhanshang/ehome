package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.VoteMapper;
import com.zyct.ehome.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-16 16:01
 */
@Service
public class VoteServiceImpl implements VoteService {
    @Autowired
    private VoteMapper voteMapper;

    @Override
    public void insertVoteCandidate(String candidateId, String ownerId) {
        voteMapper.insertVoteCandidate(candidateId,ownerId);
    }

    @Override
    public Integer voteCountByCandidateId(String candidateId) {
        Integer count = voteMapper.voteCountByCandidateId(candidateId);
        return count;
    }
}
