package com.zyct.ehome.service;

import com.zyct.ehome.entity.Issue;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-12 11:50
 */
public interface IssueService {

    void insertIssue(Issue issue);

    List<Issue> selectIssueListByCommitteeId(String committeeId);
}
