package com.zyct.ehome.controller;

import com.zyct.ehome.dto.AddCandidateDto;
import com.zyct.ehome.entity.Candidate;
import com.zyct.ehome.entity.Committee;
import com.zyct.ehome.entity.Owner;
import com.zyct.ehome.service.CandidateService;
import com.zyct.ehome.service.CommitteeManageService;
import com.zyct.ehome.service.OwnerManageService;
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

    @Autowired
    private OwnerManageService ownerManageService;


    /**
     * 获取候选人列表
     * @param communityId
     * @param request
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

    /**
     * 添加候选人
     * @param addCandidateDto
     * @return
     */
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

    /**
     * 删除候选人
     * @param candidateId
     * @return
     */
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

    /**
     * 获取该小区的业委会信息
     * @param communityId
     * @return
     */
    @RequestMapping(value = "/committeeInfo/{communityId}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage committeeInfo(@PathVariable("communityId") String communityId){
        try {
            Committee committee = committeeManageService.getCommitteeById(communityId);
            if(committee!=null){
               List<Owner> ownerList = ownerManageService.getOwnerInfoByCommitteeId(committee.getCommitteeId());
                ResponseMessage responseMessage = new ResponseMessage("0","获取成功");
                responseMessage.getData().put("committee",committee);
                responseMessage.getData().put("ownerList",ownerList);
                return responseMessage;
            }
            else{
                return new ResponseMessage("0","暂无业委会");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","获取业委会信息失败");
        }

    }


}
