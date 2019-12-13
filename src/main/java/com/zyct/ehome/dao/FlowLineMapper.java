package com.zyct.ehome.dao;

import com.zyct.ehome.entity.FlowNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author JGZ
 * CreateTime 2019/12/11 15:18
 * Email 1945282561@qq.com
 */
@Mapper
public interface FlowLineMapper {

    /**
     * 通过后一节点获取前一节点信息
     * @param flowNodes
     * @return
     */
    List<FlowNode> getPrevNodeByNextNode(List<FlowNode> flowNodes);
}
