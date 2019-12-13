package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.RoomMapper;
import com.zyct.ehome.entity.Room;
import com.zyct.ehome.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author JGZ
 * CreateTime 2019/12/9 16:53
 * Email 1945282561@qq.com
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public void addRoom(Room room) {
        room.setRoomId(UUID.randomUUID().toString().replaceAll("-",""));
        roomMapper.addRoom(room);
    }

    @Override
    public List<Room> getListByCommunityId(String communityId) {
        return roomMapper.getListByCommunityId(communityId);
    }

    @Override
    public void updateRoom(Room room) {
        roomMapper.updateRoomNameByRoomId(room);
    }

    @Override
    public void deleteRoom(Room room) {
        roomMapper.deleteRoomById(room);
    }
}
