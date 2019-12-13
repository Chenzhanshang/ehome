package com.zyct.ehome.dao;

import com.zyct.ehome.entity.Committee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author litianfu
 * @version 1.0
 * @email 1035869369@qq.com
 * @date 2019-12-12 11:12
 */
@Mapper
public interface CommitteeMapper {

    Committee selectCommitteeByCommunityId(String communityId);
}
