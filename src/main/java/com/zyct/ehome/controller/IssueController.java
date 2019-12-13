package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Issue;
import com.zyct.ehome.service.IssueService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/insertIssue")
    @ResponseBody
    public ResponseMessage insertIssue(Issue issue){

        issueService.insertIssue(issue);
        return null;
    }

    @RequestMapping("/issueList")
    @ResponseBody
    public ResponseMessage issueList(@RequestParam("communityId")String communityId){
        System.out.println(communityId);
        List<Issue> issueList = issueService.selectIssueListByCommitteeId(communityId);
        System.out.println(issueList);
        ResponseMessage responseMessage = new ResponseMessage("success","请求成功");
        Map<String,Object> map = new HashMap<>();
        map.put("issueList",issueList);
        responseMessage.setData(map);
        return responseMessage;
    }

}
