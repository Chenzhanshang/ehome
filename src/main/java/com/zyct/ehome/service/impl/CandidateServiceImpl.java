package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.CandidateMapper;
import com.zyct.ehome.dto.AddCandidateDto;
import com.zyct.ehome.entity.Candidate;
import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.Owner;
import com.zyct.ehome.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Override
    public List<Candidate> addCandidate(AddCandidateDto addCandidateDto) {
        List<Candidate> can = new ArrayList<>();
        for (String ownerId:addCandidateDto.getOwnerId()) {
            Candidate candidate = new Candidate();
            //设置id
            candidate.setCandidateId(UUID.randomUUID().toString().replaceAll("-",""));
            //设置时间
            candidate.setCandidateCreateTime(System.currentTimeMillis());
            //设置社区
            Community community = new Community();
            community.setCommunityId(addCandidateDto.getCommunityId());
            candidate.setCommunity(community);
            //设置业主
            Owner owner = new Owner();
            owner.setOwnerId(ownerId);
            candidate.setOwner(owner);
            can.add(candidate);
        }
        //添加
        candidateMapper.addCandidate(can);
        //查询候选人列表
        List<Candidate> candidateList = candidateMapper.selectCandidateByOwnerId(addCandidateDto.getOwnerId());
        //过滤
        List<Candidate> candidateList2 = new ArrayList<>();
        for (Candidate c:candidateList) {
            //一个业主可能有多套房
            if(c.getCommunity().getCommunityId().equals(addCandidateDto.getCommunityId())){
                candidateList2.add(c);
            }
        }
        return candidateList2;
    }

    @Override
    public void deleteCandidate(String candidateId) {
        candidateMapper.deleteCandidateById(candidateId);
    }
}
