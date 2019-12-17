package com.zyct.ehome.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-16 16:02
 */
@Mapper
public interface VoteMapper {

    void insertVoteCandidate(String candidateId, String ownerId);

    Integer voteCountByCandidateId(String candidateId);
}
