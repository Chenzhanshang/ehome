package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.CandidateMapper;
import com.zyct.ehome.dao.CommitteeMapper;
import com.zyct.ehome.dao.OwnerMapper;
import com.zyct.ehome.entity.Candidate;
import com.zyct.ehome.entity.Committee;
import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.Owner;
import com.zyct.ehome.service.CommitteeManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author JGZ
 * CreateTime 2019/12/25 14:23
 * Email 1945282561@qq.com
 */
@Service
public class CommitteeManageServiceImpl implements CommitteeManageService {

    @Autowired
    private CommitteeMapper committeeMapper;

    @Autowired
    private CandidateMapper candidateMapper;

    @Autowired
    private OwnerMapper ownerMapper;

    @Override
    public void createCommittee(String communityId, Integer count) {
        //查询某个小区的业委会是否存在
        Committee committee =committeeMapper.selectCommitteeByCommunityId(communityId);
        if(committee == null){
            //如果为空则创建新的，并设置业委会成员
            newCommittee(communityId,count);
        }
        else{
            //否则修改届数并重新设置业委会成员
            alterCommittee(committee,count);
        }

        //将当前届的候选人变为往届
        candidateMapper.updateCandidateIsCurrentToOneByCommunityId(communityId);
    }

    /**
     * 创建新的业委会
     * @param communityId
     * @param count
     */
    private void newCommittee(String communityId, Integer count){
        //新建业委会
        Committee committee = new Committee();
        //设置id
        String committeeId = UUID.randomUUID().toString().replace("-", "");
        committee.setCommitteeId(committeeId);
        //设置人数
        committee.setCommitteePeopleNumber(count);
        //设置届数
        committee.setCommitteeTerm(1);
        //设置所属小区
        Community community = new Community();
        community.setCommunityId(communityId);
        committee.setCommunity(community);
        //插入业委会
        committeeMapper.insert(committee);
        //按照票数从大到小获取候选人
        List<Candidate> candidateList = candidateMapper.getCandidateListByCommunityIdDesc(communityId);
        List<Owner> ownerList = new ArrayList<>();
        //获取前count个业主
        for (int i = 0;i<count;i++){
            Owner o = new Owner();
            //设置id
            o.setOwnerId(candidateList.get(i).getOwner().getOwnerId());
            o.setCommittee(committee);
            o.setOwnerOfficeTrime(System.currentTimeMillis());
            ownerList.add(o);
        }
        ownerMapper.updateCommitteeIdAndOfficeTrimeById(ownerList);
    }


    /**
     * 修改已存在的业委会
     * @param committee
     * @param count
     */
    private void alterCommittee(Committee committee,Integer count){
        committee.setCommitteeTerm(committee.getCommitteeTerm()+1);
        committee.setCommitteePeopleNumber(count);
        //修改业委会
        committeeMapper.update(committee);
        //设置业主所属业委会为空
        ownerMapper.setCommitteeIdIsNullByCommitteeId(committee.getCommitteeId());
        //按照票数从大到小获取候选人
        List<Candidate> candidateList = candidateMapper.
                getCandidateListByCommunityIdDesc(committee.getCommunity().getCommunityId());
        List<Owner> ownerList = new ArrayList<>();
        //获取前count个业主
        for (int i = 0;i<count;i++){
            Owner o = new Owner();
            //设置id
            o.setOwnerId(candidateList.get(i).getOwner().getOwnerId());
            o.setCommittee(committee);
            o.setOwnerOfficeTrime(System.currentTimeMillis());
            ownerList.add(o);
        }
        ownerMapper.updateCommitteeIdAndOfficeTrimeById(ownerList);
    }


}
