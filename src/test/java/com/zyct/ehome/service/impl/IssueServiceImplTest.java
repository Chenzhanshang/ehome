package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.CommitteeMapper;
import com.zyct.ehome.dao.IssueMapper;
import com.zyct.ehome.entity.Committee;
import com.zyct.ehome.entity.Issue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-12 11:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class IssueServiceImplTest {
    @Autowired
    private IssueMapper issueMapper;

    @Autowired
    private CommitteeMapper committeeMapper;

    @Test
    void insertIssue() {
        Issue issue = new Issue();
        issue.setIssueId(UUID.randomUUID().toString());
        issue.setIssueTitle("物业招标投票");
        issue.setIssueContent("公平、公正、公开");
        issue.setIssueAgree(0);
        issue.setIssueWaiver(0);
        issue.setIssueOppose(0);
        issue.setIssueStartTime(System.currentTimeMillis());
        issue.setIssueEndTime(System.currentTimeMillis()+(24*60*60*1000)*2);
        Committee committee = committeeMapper.selectCommitteeByCommunityId("1");
        issue.setCommittee(committee);
        issue.setIssueStatus(1);
        System.out.println("插入");
        issueMapper.insertIssue(issue);
    }

    @Test
    void selectIssueListByCommitteeId() {
        List<Issue> issues = issueMapper.selectIssueListByCommitteeId("1");
        System.out.println(issues);
    }
}