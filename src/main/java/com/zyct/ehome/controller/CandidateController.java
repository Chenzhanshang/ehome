package com.zyct.ehome.controller;

import com.zyct.ehome.dto.AddCandidateDto;
import com.zyct.ehome.entity.Candidate;
import com.zyct.ehome.service.CandidateService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/16 10:42
 * Email 1945282561@qq.com
 */
@Controller
@RequestMapping("/admin")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;


    @RequestMapping(value = "/candidateList/{communityId}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage candidateList(@PathVariable("communityId") String communityId){
        try {
            List<Candidate> candidateList = candidateService.candidateList(communityId);
            ResponseMessage responseMessage = new ResponseMessage("0","获取候选人列表成功");
            responseMessage.getData().put("candidateList",candidateList);
            return responseMessage;
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","获取候选人列表失败");
        }
    }

    @RequestMapping(value = "/addCandidate",method = RequestMethod.POST)
    public @ResponseBody ResponseMessage addCandidate(@RequestBody AddCandidateDto addCandidateDto){
        System.out.println(addCandidateDto);
        try{
            List<Candidate> candidateList = candidateService.addCandidate(addCandidateDto);
            ResponseMessage responseMessage = new ResponseMessage("0","添加候选人成功");
            responseMessage.getData().put("candidateList",candidateList);
            return responseMessage;
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","添加候选人失败");
        }
    }

    @RequestMapping(value = "/deleteCandidate/{candidateId}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage deleteCandidate(@PathVariable("candidateId") String candidateId){
        try {
            candidateService.deleteCandidate(candidateId);
            return new ResponseMessage("0","删除成功");
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","删除失败");
        }
    }



}
