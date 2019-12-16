package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Candidate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/16 10:48
 * Email 1945282561@qq.com
 */
@Mapper
public interface CandidateMapper {
    /**
     * 通过小区获取候选人列表
     * @param communityId
     * @return
     */
    List<Candidate> getCandidateListByCommunityId(String communityId);

    /**
     * 添加候选人，返回添加后的候选人
     * @param can
     * @return
     */
    void addCandidate(List<Candidate> can);

    /**
     * 通过业主id查询候选人列表
     * @param ownerId
     * @return
     */
    List<Candidate> selectCandidateByOwnerId(List<String> ownerId);
}
