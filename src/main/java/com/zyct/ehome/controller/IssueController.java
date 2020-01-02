package com.zyct.ehome.controller;

import com.zyct.ehome.dto.IssueDto;
import com.zyct.ehome.entity.Issue;
import com.zyct.ehome.entity.Vote;
import com.zyct.ehome.service.IssueService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-12 10:55
 */
@RequestMapping("/issue")
@Controller
public class IssueController {

    @Autowired
    private IssueService issueService;

    /**
     * 发布议题
     * @param issue
     * @return
     */
    @RequestMapping("/putIssue")
    @ResponseBody
    public ResponseMessage insertIssue(@RequestBody IssueDto issue) {
        System.out.println(issue.toString());
        issueService.insertIssue(issue);
        return new ResponseMessage("0","发布成功");
    }

    /**
     * 通过小区id获取议题列表
     * @param communityId
     * @return
     */
    @RequestMapping("/issueList")
    @ResponseBody
    public ResponseMessage issueList(@RequestParam("communityId") String communityId) {
        List<Issue> issueList = issueService.selectIssueListByCommitteeId(communityId);
        ResponseMessage responseMessage = new ResponseMessage("success", "请求成功");
        Map<String, Object> map = new HashMap<>();
        map.put("issueList", issueList);
        responseMessage.setData(map);
        return responseMessage;
    }

    /**
     * 给议题投票
     * @param vote
     * @return
     */
    @RequestMapping("/vote")
    @ResponseBody
    public ResponseMessage vote(@RequestBody Vote vote) {
        issueService.insertVote(vote);
        return new ResponseMessage("0", "投票成功");
    }

    /**
     * 用户投票列表
     * @param ownerId
     * @return
     */
    @RequestMapping("/ownerVoteList")
    @ResponseBody
    public List<Vote> ownerVoteList(@RequestParam("ownerId") String ownerId) {

        List<Vote> voteList = issueService.selectVoteListByOwnerId(ownerId);
        return voteList;
    }

}
