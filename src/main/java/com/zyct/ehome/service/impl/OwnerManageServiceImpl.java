package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.CandidateMapper;
import com.zyct.ehome.dao.OwnerMapper;
import com.zyct.ehome.entity.Candidate;
import com.zyct.ehome.entity.Owner;
import com.zyct.ehome.service.OwnerManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    public List<Owner> unCandidateOwnerList(String communityId) {
        //获取小区的所有业主
        List<Owner> ownerListByCommunityId = ownerMapper.getOwnerListByCommunityId(communityId);
        //获取候选人列表
        List<Candidate> candidateListByCommunityId = candidateMapper.getCandidateListByCommunityId(communityId);
        List<Owner> ownerList = new ArrayList<>();
        for (Owner o:ownerListByCommunityId){
            boolean flag = true;
            for (Candidate c:candidateListByCommunityId){
                if(o.getOwnerId().equals(c.getOwner().getOwnerId())){
                    //如果该业主是候选人列表中的人则将标志位设为false
                    flag = false;
                }
            }
            if (flag){
                //经历了整个循环标志位依旧为true
                ownerList.add(o);
            }
        }

        return ownerList;
    }
}
