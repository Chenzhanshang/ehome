package com.zyct.ehome.service;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-16 15:59
 */
public interface VoteService {

    void insertVoteCandidate(String candidateId, String ownerId);

    Integer voteCountByCandidateId(String candidateId);
}
