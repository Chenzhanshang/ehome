package com.zyct.ehome.controller;

import com.zyct.ehome.dto.AddCandidateDto;
import com.zyct.ehome.entity.Candidate;
import com.zyct.ehome.service.CandidateService;
import com.zyct.ehome.service.CommitteeManageService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private CommitteeManageService committeeManageService;


    @RequestMapping(value = "/candidateList/{communityId}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage candidateList(@PathVariable("communityId") String communityId, HttpServletRequest request){
        try {
            List<Candidate> candidateList = candidateService.candidateList(communityId);
            for (Candidate candidate:candidateList) {
                if(candidate.getOwner().getAvatar() != null){
                    String avatar = candidate.getOwner().getAvatar();
                    candidate.getOwner().setAvatar("http://localhost:8081" + request.getContextPath() + "/file/"+avatar);
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

    /**
     * 生成业委会
     * @param communityId
     * @param count
     * @return
     */
    @RequestMapping(value = "/createCommittee/{communityId}/{count}",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage createCommittee(@PathVariable("communityId") String communityId,
                                           @PathVariable("count")Integer count){
        if(count <= 0 ){
            return new ResponseMessage("-2","业委会人数过少");
        }
        if(count > candidateService.candidateList(communityId).size()){
            return new ResponseMessage("-3","业委会人多于候选人人数");
        }
        try {
            committeeManageService.createCommittee(communityId,count);
            return new ResponseMessage("0","创建成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","创建失败");
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
