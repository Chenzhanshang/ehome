package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Apply;
import com.zyct.ehome.entity.FlowNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-09 14:28
 */
@Mapper
public interface ApplyMapper {


    void insertApply(Apply apply);

    /**
     * 获取正在进行中的审批申请
     * @param prevNode
     * @return
     */
    List<Apply> getApplyStateIsZeroByNode(List<FlowNode> prevNode);

    /**
     * 通过id获取审批信息
     * @param applyId
     * @return
     */
    Apply getApplyById(String applyId);

    Apply selectAppplyByApplyId(String applyId);
}
