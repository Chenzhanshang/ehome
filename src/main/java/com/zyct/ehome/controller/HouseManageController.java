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
        try{
            houseManageService.addHouse(house);
            return new ResponseMessage("0","添加楼栋成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","添加楼栋失败");
        }

    }

    /**
     * 根据communityId查询小区中的所有楼栋
     * @param communityId
     * @return
     */
    @RequestMapping(value = "/houseList/{communityId}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage houseList(@PathVariable("communityId") String communityId){

        System.out.println(communityId);
        ResponseMessage responseMessage ;
        try{
            //查询
            List<House> list =houseManageService.getHouseListByCommunityId(communityId);
            responseMessage = new ResponseMessage("0", "获取楼栋列表成功");
            responseMessage.getData().put("houseList",list);
            return responseMessage;
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","获取楼栋列表失败");
        }
    }

    /**
     * 修改楼栋
     * @param house
     * @return
     */
    @RequestMapping(value = "/updateHouse",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage updateHouse(@RequestBody House house){
        System.out.println(house);
        try{
            houseManageService.updateHouse(house);
            return new ResponseMessage("0","修改楼栋信息成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1","修改楼栋信息失败");
        }
    }

    /**
     * 删除楼栋
     * @param house
     * @return
     */
    @RequestMapping(value = "/deleteHouse",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage deleteHouse(@RequestBody House house){
        System.out.println(house);
        try {
            houseManageService.deleteHouse(house);
            return new ResponseMessage("0", "删除楼栋成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage("-1", "删除楼栋失败");
        }
    }


}
