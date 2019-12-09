package com.zyct.ehome.controller;

import com.zyct.ehome.entity.House;
import com.zyct.ehome.service.HouseManageService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/9 14:08
 * Email 1945282561@qq.com
 */
@RequestMapping("/admin")
@Controller
public class HouseManageController {

    @Autowired
    private HouseManageService houseManageService;
    /**
     * 添加楼栋
     * @param house
     * @return
     */
    @RequestMapping(value = "/addHouse",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage addHouse(@RequestBody House house){

        System.out.println(house);
        houseManageService.addHouse(house);

        return new ResponseMessage("0","添加成功");
    }

    @RequestMapping(value = "/houseList/{communityId}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage houseList(@PathVariable("communityId") String communityId){

        System.out.println(communityId);
        //查询
        List<House> list =houseManageService.getHouseListByCommunityId(communityId);
        ResponseMessage responseMessage ;
        if(list != null){
            responseMessage = new ResponseMessage("0", "获取成功");
            responseMessage.getData().put("houseList",list);
            return responseMessage;
        }
        else {
            return new ResponseMessage("-1","获取失败");
        }

    }
}
