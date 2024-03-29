package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.Manager;
import com.zyct.ehome.service.CommunityManageService;
import com.zyct.ehome.service.ManagerService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ManagerService managerService;

    /**
     * 添加小区
     * @param community
     * @return
     */
    @RequestMapping(value = "/addCommunity",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage add(@RequestBody Community community){
        try{
            //插入小区
            Community dbComm = communityManageService.addCommunity(community);
            ResponseMessage responseMessage = new ResponseMessage("0","添加小区成功");
            responseMessage.getData().put("dbData",dbComm);
            return responseMessage;
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","添加小区失败");
        }


    }

    /**
     * 获取小区列表
     * @return
     */
    @RequestMapping(value = "/communityList",method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage list(){
        ResponseMessage responseMessage ;
        try {
            List<Community> list =  communityManageService.getList();
            responseMessage = new ResponseMessage("0","获取小区列表成功");
            responseMessage.getData().put("communityList",list);
            return responseMessage;
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","获取小区列表失败");
        }

    }

    /**
     * 删除小区信息
     * @param community
     * @return
     */
    @RequestMapping(value = "deleteCommunity",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage deleteCommunity(@RequestBody Community community){
        try {
            communityManageService.deleteCommunity(community);
            return new ResponseMessage("0","删除小区成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","删除小区失败");
        }
    }

    /**
     * 修改小区
     * @param community
     * @return
     */
    @RequestMapping(value = "updateCommunity",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage updateCommunity(@RequestBody Community community){
        System.out.println(community);
        try{
            System.out.println("你好");
            communityManageService.updateCommunity(community);
            return new ResponseMessage("0","修改小区信息成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage("-1", "修改小区信息失败");
        }
    }

    /**
     * 获取小区的管理账号
     * @param communityId
     * @return
     */
    @RequestMapping(value = "/getCommunityAccount/{communityId}",method = RequestMethod.GET)
    public @ResponseBody ResponseMessage getCommunityAccount(@PathVariable("communityId") String communityId){
        try {
           List<Manager> managerList = managerService.selectByCommunityId(communityId);
           ResponseMessage responseMessage = new ResponseMessage("0","获取成功");
           responseMessage.getData().put("communityAccount",managerList);
           return responseMessage;
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","获取失败");
        }
    }
}
