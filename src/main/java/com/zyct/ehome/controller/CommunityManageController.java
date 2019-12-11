package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Community;
import com.zyct.ehome.service.CommunityManageService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 管理端的小区控制层
 * @author JGZ
 * CreateTime 2019/12/6 14:23
 * Email 1945282561@qq.com
 */
@Controller
@RequestMapping("/admin")
public class CommunityManageController {

    @Autowired
    private CommunityManageService communityManageService;
    /**
     * 添加小区
     * @param community
     * @return
     */
    @RequestMapping(value = "/addCommunity",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage add(@RequestBody Community community){
        //插入用户
        Community dbComm = communityManageService.addCommunity(community);
        if (dbComm != null){
            ResponseMessage responseMessage = new ResponseMessage("0","插入成功");
            responseMessage.getData().put("dbData",dbComm);
            return responseMessage;
        }
        else {
            return new ResponseMessage("-1","插入失败");
        }
    }

    /**
     * 获取小区列表
     * @return
     */
    @RequestMapping(value = "/communityList",method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage list(){
        List<Community> list =  communityManageService.getList();
        ResponseMessage responseMessage ;
        if(list != null){
            responseMessage = new ResponseMessage("0","获取成功");
            responseMessage.getData().put("communityList",list);
            return responseMessage;
        }
        else {
            responseMessage = new ResponseMessage("-1","获取失败");
            return responseMessage;
        }
    }
    @RequestMapping(value = "deleteCommunity",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage deleteCommunity(@RequestBody Community community){
        communityManageService.deleteCommunity(community);

        return new ResponseMessage("0","删除成功");
    }

    @RequestMapping(value = "updateCommunity",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage updateCommunity(@RequestBody Community community){
        System.out.println(community);

        communityManageService.updateCommunity(community);
        return new ResponseMessage("0","修改成功");
    }
}
