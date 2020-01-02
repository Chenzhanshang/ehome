package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Candidate;
import com.zyct.ehome.service.CandidateService;
import com.zyct.ehome.service.VoteService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-16 15:44
 */
@Controller
@RequestMapping("/vote")
@PropertySource("classpath:file.properties")
public class VoteController {
    @Autowired
    private CandidateService candidateService;

    @Autowired
    private VoteService voteService;

    @Value("${filepath}")
    String path;

    @Value("${responsePath}")
    private String responsePath;



    /**
     * 给候选人投票
     * @param candidateId 候选人id
     * @param ownerId 投票者id
     */
    @RequestMapping("/voteCandidate")
    @ResponseBody
    public void voteCandidate(@RequestParam("candidateId")String candidateId,
                              @RequestParam("ownerId")String ownerId){
        voteService.insertVoteCandidate(candidateId,ownerId);
        Integer pollNum = voteService.voteCountByCandidateId(candidateId);

    }

    /**
     * 通过小区id获取候选人列表
     * @param communityId
     * @return
     */
    @RequestMapping(value = "/candidateList/{communityId}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage candidateList(@PathVariable("communityId") String communityId, HttpServletRequest request){
        try {
            List<Candidate> candidateList = candidateService.candidateList(communityId);
            for (Candidate candidate:candidateList) {
                if(candidate.getOwner().getAvatar() != null){
                    String avatar = candidate.getOwner().getAvatar();
                    candidate.getOwner().setAvatar(responsePath + request.getContextPath() + "/file/"+avatar);
                }
            }
            ResponseMessage responseMessage = new ResponseMessage("0","获取候选人列表成功");
            responseMessage.getData().put("candidateList",candidateList);
            return responseMessage;
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","获取候选人列表失败");
        }
    }



}
