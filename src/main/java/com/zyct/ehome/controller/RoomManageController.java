package com.zyct.ehome.controller;

import com.zyct.ehome.entity.Room;
import com.zyct.ehome.service.RoomService;
import com.zyct.ehome.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
