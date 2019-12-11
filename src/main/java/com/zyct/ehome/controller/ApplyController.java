package com.zyct.ehome.controller;

import com.zyct.ehome.entity.*;
import com.zyct.ehome.service.ApplyService;
import com.zyct.ehome.service.CommunityService;
import com.zyct.ehome.service.UserService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-09 11:38
 * 用户申请业主认证控制层
 */
@Controller
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplyService applyService;

    @RequestMapping("/applyMessage")
    @ResponseBody
    public ResponseMessage applyMessage(@RequestParam("weixin")String ownerId,@RequestParam("name")String name,@RequestParam("idCard")String idCard,
                                        @RequestParam("phone")String phone,@RequestParam("communityId")String communityId,
                                        @RequestParam("houseId")String houseId,@RequestParam("roomId")String roomId){
        Owner owner = userService.selectUserByOwnerId(ownerId);
        owner.setOwnerName(name);
        owner.setOwnerPhone(phone);
        owner.setOwnerIdNumber(idCard);
        //更新用户数据
        userService.updateUser(owner);
        Apply apply = new Apply();
        apply.setApplyId(UUID.randomUUID().toString());
        apply.setOwner(owner);
        Community community = communityService.getCommunityByCommunityId(communityId);
        apply.setCommunity(community);
        House house = communityService.getHouseByHouseId(houseId);
        apply.setHouse(house);
        Room room = communityService.getRoomByRoomId(roomId);
        apply.setRoom(room);
        apply.setFlowNode(new FlowNode(1,"用户提交申请",null));
        apply.setApplyState(0);
        System.out.println(apply.toString());
        applyService.insertApply(apply);

        return null;
    }


}