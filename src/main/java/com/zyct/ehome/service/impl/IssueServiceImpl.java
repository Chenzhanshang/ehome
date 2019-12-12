package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.CommitteeMapper;
import com.zyct.ehome.dao.CommunityMapper;
import com.zyct.ehome.dao.IssueMapper;
import com.zyct.ehome.entity.Committee;
import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.Issue;
import com.zyct.ehome.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-12 10:57
 */
@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueMapper issueMapper;

    @Autowired
    private CommitteeMapper committeeMapper;

    @Autowired
    private CommunityMapper communityMapper;


    @Override
    public void insertIssue(Issue issueTemp){
        Issue issue = new Issue();
        issue.setIssueId(UUID.randomUUID().toString());
        issue.setIssueTitle(issueTemp.getIssueTitle());
        issue.setIssueContent(issueTemp.getIssueContent());
        issue.setIssueAgree(0);
        issue.setIssueWaiver(0);
        issue.setIssueOppose(0);
        issue.setIssueStartTime(issueTemp.getIssueStartTime());
        issue.setIssueEndTime(issueTemp.getIssueEndTime());
        Committee committee = committeeMapper.selectCommitteeByCommunityId(issueTemp.getCommittee().getCommitteeId());
        issue.setCommittee(committee);
        //0代表用户待投票，1代表用户已投票
        issue.setIssueStatus(0);
        issueMapper.insertIssue(issue);
    }

    @Override
    public List<Issue> selectIssueListByCommitteeId(String communityId){
        Community community = communityMapper.getCommunityByCommunityId(communityId);
        List<Issue> issueList = issueMapper.selectIssueListByCommitteeId(community.getCommunityId());
        return issueList;
    }
}
