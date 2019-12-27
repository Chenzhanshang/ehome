package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.CommunityMapper;
import com.zyct.ehome.dao.OwnerMapper;
import com.zyct.ehome.dao.TenMapper;
import com.zyct.ehome.dto.FixDto;
import com.zyct.ehome.entity.*;
import com.zyct.ehome.service.TenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-23 10:06
 */
@Service
public class TenServiceImpl implements TenService {

    @Autowired
    private TenMapper tenMapper;

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private OwnerMapper ownerMapper;

    /**
     * 添加维修信息
     * @param fix
     */
    @Override
    public void insertFix(Fix fix) {
        tenMapper.insertFix(fix);
    }

    /**
     * 通过小区id查询待维修列表
     *
     * @param communityId
     * @return
     */
    @Override
    public List<FixDto> fixList(String communityId) {
        List<Fix> fixList = tenMapper.selectFixListByCommunityId(communityId);
        List<FixDto> fixDtos = new ArrayList<>();
        //将fix转换为fixDto
        for (Fix fix : fixList) {
            FixDto fixDto = new FixDto();
            Owner owner = ownerMapper.getOwnerByOwnerId(fix.getOwnerId());
            Community community = communityMapper.getCommunityByCommunityId(fix.getCommunityId());
            BeanUtils.copyProperties(fix,fixDto);
            fixDto.setName(owner.getOwnerName());
            fixDto.setPhone(owner.getOwnerPhone());
            fixDto.setCommunity(community);
            fixDto.setOwner(owner);
            //查询出该用户的所有房子
            List<Room> rooms = communityMapper.getRoomListByOwnerId(owner.getOwnerId());
            for (Room room : rooms) {
                //查出改房子所属的栋
                House house = communityMapper.getHouseByHouseId(room.getHouse().getHouseId());
                if (house.getCommunity().getCommunityId().equals(communityId)){
                    fixDto.setRoom(room.getRoomName());
                    fixDto.setHouse(house.getHouseName());
                }
            }
            fixDtos.add(fixDto);
        }
        return fixDtos;
    }

    /**
     * 通过小区id查询已维修列表，历史维修列表
     *
     * @param communityId
     * @return
     */
    @Override
    public List<FixDto> fixedList(String communityId) {
        List<Fix> fixedList = tenMapper.selectFixedListByCommunityId(communityId);
        List<FixDto> fixedDtos = new ArrayList<>();
        //将fix转换为fixDto
        for (Fix fix : fixedList) {
            FixDto fixDto = new FixDto();
            Owner owner = ownerMapper.getOwnerByOwnerId(fix.getOwnerId());
            Community community = communityMapper.getCommunityByCommunityId(fix.getCommunityId());
            BeanUtils.copyProperties(fix,fixDto);
            fixDto.setName(owner.getOwnerName());
            fixDto.setPhone(owner.getOwnerPhone());
            fixDto.setCommunity(community);
            fixDto.setOwner(owner);
            //查询出该用户的所有房子
            List<Room> rooms = communityMapper.getRoomListByOwnerId(owner.getOwnerId());
            for (Room room : rooms) {
                //查出改房子所属的栋
                House house = communityMapper.getHouseByHouseId(room.getHouse().getHouseId());
                if (house.getCommunity().getCommunityId().equals(communityId)){
                    fixDto.setRoom(room.getRoomName());
                    fixDto.setHouse(house.getHouseName());
                }
            }
            fixedDtos.add(fixDto);
        }
        return fixedDtos;
    }

    /**
     * 更新维修信息
     *
     * @param fix
     */
    @Override
    public void updateFix(Fix fix) {
        tenMapper.updateFix(fix);
    }

    @Override
    public void putNotice(Notice notice) {
        tenMapper.insertNotice(notice);
    }

    /**
     * 通过小区id和类型获取通知列表
     *
     * @param communityId
     * @param type
     * @return
     */
    @Override
    public List<Notice> getNoticeListByType(String communityId, Integer type) {
        List<Notice> noticeList = tenMapper.selectNoticeListByCommunityIdAndType(communityId, type);
        return noticeList;
    }

    /**
     * 删除公告
     *
     * @param notice
     */
    @Override
    public void deleteNotice(Notice notice) {
        tenMapper.deleteNotice(notice);
    }

    /**
     * 修改公告
     *
     * @param notice
     */
    @Override
    public void updateNotice(Notice notice) {
        tenMapper.updateNotice(notice);
    }

    /**
     * 通过小区id获取全部通知
     *
     * @param communityId
     * @return
     */
    @Override
    public List<Notice> getNoticeList(String communityId) {
        List<Notice> noticeList = tenMapper.selectNoticeListByCommunityId(communityId);
        return noticeList;
    }


}
