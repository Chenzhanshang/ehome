package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Owner;
import com.zyct.ehome.service.OwnerManageService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author JGZ
 * CreateTime 2019/12/16 11:06
 * Email 1945282561@qq.com
 */
@Controller
@RequestMapping("/admin")
public class OwnerManageController {

    @Autowired
    private OwnerManageService ownerManageService;

    @RequestMapping(value = "/unCandidateOwnerList/{communityId}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage unCandidateOwnerList(@PathVariable("communityId") String communityId){
        try{
           List<Owner> ownerList = ownerManageService.unCandidateOwnerList(communityId);
           Set<Owner> set = new HashSet<>(ownerList);
           ResponseMessage responseMessage = new ResponseMessage("0","获取业主列表成功");
           responseMessage.getData().put("ownerList",set);
           return responseMessage;
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","获取业主列表失败");
        }


    }
}
