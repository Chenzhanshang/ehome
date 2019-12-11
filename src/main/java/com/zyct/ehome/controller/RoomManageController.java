package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Room;
import com.zyct.ehome.service.RoomService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/9 16:49
 * Email 1945282561@qq.com
 */
@Controller
@RequestMapping("/admin")
public class RoomManageController {

    @Autowired
    private RoomService roomService;

    /**
     * 插入房间
     * @param room
     * @return
     */
    @RequestMapping(value = "/addRoom",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage addRoom(@RequestBody Room room){

        roomService.addRoom(room);
        return new ResponseMessage("0","插入成功");
    }

    /**
     * 获取小区的房间列表
     * @param communityId
     * @return
     */
    @RequestMapping(value = "/roomList/{communityId}",method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage roomList(@PathVariable("communityId") String communityId){

        List<Room> list = roomService.getListByCommunityId(communityId);
        if(list != null){
            ResponseMessage responseMessage = new ResponseMessage("0","请求成功");
            responseMessage.getData().put("roomList",list);
            return responseMessage;
        }
        return new ResponseMessage("-1","请求失败");
    }

    /**
     * 修改房间信息
     * @param room
     * @return
     */
    @RequestMapping(value = "/updateRoom",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage updateRoom(@RequestBody Room room){
        System.out.println(room);
        roomService.updateRoom(room);
        return new ResponseMessage("0","修改成功");
    }

    @RequestMapping(value = "/deleteRoom",method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage deleteRoom(@RequestBody Room room){
        System.out.println(room);
        roomService.deleteRoom(room);
        return new ResponseMessage("0","删除成功");
    }

}
