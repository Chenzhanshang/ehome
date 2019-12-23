package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.CommunityMapper;
import com.zyct.ehome.dao.OwnerMapper;
import com.zyct.ehome.dao.TenMapper;
import com.zyct.ehome.dto.FixDto;
import com.zyct.ehome.entity.Community;
import com.zyct.ehome.entity.Fix;
import com.zyct.ehome.entity.Owner;
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
        for (Fix fix : fixList) {
            FixDto fixDto = new FixDto();
            Owner owner = ownerMapper.getOwnerByOwnerId(fix.getOwnerId());
            Community community = communityMapper.getCommunityByCommunityId(fix.getCommunityId());
            BeanUtils.copyProperties(fix,fixDto);
            fixDto.setCommunity(community);
            fixDto.setOwner(owner);
            fixDtos.add(fixDto);
        }
        return fixDtos;
    }

    /**
     * 更新维修信息
     *
     * @param fix
     */
    @Override
    public void updateFix(Fix fix) {

    }


}
