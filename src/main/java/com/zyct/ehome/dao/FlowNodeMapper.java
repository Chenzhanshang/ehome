package com.zyct.ehome.dao;

import com.zyct.ehome.entity.FlowNode;
import com.zyct.ehome.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/11 15:14
 * Email 1945282561@qq.com
 */
@Mapper
public interface FlowNodeMapper {

    /**
     * 通过角色列表获取能审批的节点
     * @param roleList
     * @return
     */
    List<FlowNode> getNodeByRoleId(List<Role> roleList);
}
