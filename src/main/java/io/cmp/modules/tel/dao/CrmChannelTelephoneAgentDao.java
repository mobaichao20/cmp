package io.cmp.modules.tel.dao;

import io.cmp.modules.tel.entity.CrmChannelTelephoneAgentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cmp.modules.tel.vo.CrmChannelTelephoneAgentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 渠道管理电话工号表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-01 10:46:46
 */
@Mapper
public interface CrmChannelTelephoneAgentDao extends BaseMapper<CrmChannelTelephoneAgentEntity> {
    CrmChannelTelephoneAgentVo queryTelephoneAgentInfoByExtension(@Param("extension") String extension, @Param("agentNumber")String agentNumber);

    CrmChannelTelephoneAgentEntity queryrRandomTelephoneAgentInfo();

}
