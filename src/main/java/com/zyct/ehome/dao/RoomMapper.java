package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/9 16:53
 * Email 1945282561@qq.com
 */
@Mapper
public interface RoomMapper {
    /**
     * 插入房间
     * @param room
     */
    void addRoom(Room room);

    /**
     * 通过小区号获取房间列表
     * @param communityId
     * @return
     */
    List<Room> getListByCommunityId(String communityId);

    /**
     * 通过房间id修改房间名
     * @param room
     */
    void updateRoomNameByRoomId(Room room);

    /**
     * 通过id删除房间
     * @param room
     */
    void deleteRoomById(Room room);

    /**
     * 修改房间所属的用户
     * @param roomId
     * @param ownerId
     */
    void updateRoomOwnerIdByRoomId(String roomId, String ownerId);
}
