package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Issue;
import com.zyct.ehome.entity.Vote;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-12 10:59
 */
@Mapper
public interface IssueMapper {

    @Insert("insert into t_issue(issue_id,issue_title,issue_content,issue_agree,issue_waiver,issue_oppose," +
            "issue_start_time,issue_end_time,issue_status,committee_id) values(#{issueId},#{issueTitle},#{issueContent}," +
            "#{issueAgree},#{issueWaiver},#{issueOppose},#{issueStartTime},#{issueEndTime},#{issueStatus},#{committee.committeeId})")
    void insertIssue(Issue issue);

    List<Issue> selectIssueListByCommitteeId(String CommitteeId);

    void insertVote(Vote vote);

    void updateIssue(@Param("issueId") String issueId,@Param("voteFlag") Integer voteFlag);

    List<Vote> selectVoteListByOwnerId(String ownerId);
}
