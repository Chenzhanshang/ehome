package com.zyct.ehome.controller;

import com.zyct.ehome.dto.CommunityDto;
import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.House;
import com.zyct.ehome.entity.Room;
import com.zyct.ehome.service.CommunityService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-06 09:56
 */
@Controller
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @RequestMapping("/communityList")
    @ResponseBody
    public List<Community> communityList(@RequestParam("city") String city){
        List<Community> communityList = communityService.getCommunityList(city);
        System.out.println(communityList);
        return communityList;
    }

    @RequestMapping("/houseList")
    @ResponseBody
    public List<House> houseList(@RequestParam("communityId") String communityId){
        List<House> houseList = communityService.getHouseListByCommunityId(communityId);
        System.out.println(houseList);
        return houseList;
    }

    @RequestMapping("/roomList")
    @ResponseBody
    public List<Room> RoomList(@RequestParam("houseId") String houseId){
        System.out.println(houseId);
        List<Room> roomList = communityService.getRoomListByHouseId(houseId);
        System.out.println(roomList);
        return roomList;
    }

    @RequestMapping("/ownerCommunityList")
    @ResponseBody
    public List<CommunityDto> ownerCommunityList(@RequestParam("ownerId")String ownerId){
        List<CommunityDto> communityDtoList = new ArrayList<>();
        List<Community> communityList = communityService.getCommunityListByOwnerId(ownerId);
        for (Community community : communityList) {
            CommunityDto communityDto = new CommunityDto();
            communityDto.setCommunityId(community.getCommunityId());
            communityDto.setCommunityName(community.getCommunityName());
            communityDto.setCommunityInfo(community.getCommunityInfo());
            communityDto.setIsSelected(0);
            communityDtoList.add(communityDto);
        }
        return communityDtoList;
    }
}
