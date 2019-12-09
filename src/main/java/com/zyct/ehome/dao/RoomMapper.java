package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Room;
import org.apache.ibatis.annotations.Mapper;

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
}
