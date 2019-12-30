package com.zyct.ehome.service.impl;

import com.zyct.ehome.dao.ApplyMapper;
import com.zyct.ehome.dao.FlowLineMapper;
import com.zyct.ehome.dao.FlowNodeMapper;
import com.zyct.ehome.entity.*;
import com.zyct.ehome.service.ApplyManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author JGZ
 * CreateTime 2019/12/11 15:09
 * Email 1945282561@qq.com
 */
@Service
public class ApplyManageServiceImpl implements ApplyManageService {

    @Autowired
    private FlowNodeMapper flowNodeMapper;

    @Autowired
    private FlowLineMapper flowLineMapper;

    @Autowired
    private ApplyMapper applyMapper;


    @Override
    public List<Apply> getCurrentAdminExamineList(Admin admin) {
        //1.获取管理员的角色
        Set<Role> roles = admin.getRoles();
        //将角色列表转换为list
        List<Role> roleList = new ArrayList<>(roles);
        //2.通过其角色列表拿到该用户能够审批的节点
        List<FlowNode> flowNodes = flowNodeMapper.getNodeByRoleId(roleList);
        //3.在流程线表中获取前一节点的列表
        List<FlowNode> prevNode = flowLineMapper.getPrevNodeByNextNode(flowNodes);
        //4.通过查询到的前一节点获取待审列表
        List<Apply> applyList = applyMapper.getApplyStateIsZeroByNode(prevNode);

        return filterAdminApplyList(applyList,admin);
    }

    @Override
    public Apply getApply(String applyId) {
        return applyMapper.getApplyById(applyId);
    }

    /**
     * 通过管理员的地区过滤能够审核的申请
     * @param applyList
     * @param admin
     * @return
     */
    private List<Apply> filterAdminApplyList(List<Apply> applyList,Admin admin){
        Set<Role> roles = admin.getRoles();
        for (Role r:roles) {
            if("平台成员".equals(r.getRoleName())){
                //如果其角色为平台成员则直接返回所有数据
                return applyList;
            }
        }

        List<Apply> applies = new ArrayList<>();
        for (Apply a:applyList) {
            //如果当前申请的地区属于管理员管辖范围
            if (a.getCommunity().getRegion().getRegionId().equals(admin.getRegion().getRegionId())||
                a.getCommunity().getRegion().getParent().equals(admin.getRegion().getRegionId())){
                applies.add(a);
            }
        }
        return applies;
    }
}
