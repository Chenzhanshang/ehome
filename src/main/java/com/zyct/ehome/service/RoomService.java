package com.zyct.ehome.service;

import com.zyct.ehome.entity.Room;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/9 16:51
 * Email 1945282561@qq.com
 */
public interface RoomService {
    /**
     * 插入房间
     * @param room
     */
    void addRoom(Room room);

    /**
     * 获取小区的所有房间号
     * @param communityId
     * @return
     */
    List<Room> getListByCommunityId(String communityId);

    /**
     * 修改房间信息
     * @param room
     */
    void updateRoom(Room room);

    /**
     * 删除房间
     * @param room
     */
    void deleteRoom(Room room);
}
