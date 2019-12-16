package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.OwnerMapper;
import com.zyct.ehome.entity.Owner;
import com.zyct.ehome.service.OwnerManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/16 11:12
 * Email 1945282561@qq.com
 */
@Service
public class OwnerManageServiceImpl implements OwnerManageService {

    @Autowired
    private OwnerMapper ownerMapper;

    @Override
    public List<Owner> ownerList(String communityId) {
        return ownerMapper.getOwnerListByCommunityId(communityId);
    }
}
