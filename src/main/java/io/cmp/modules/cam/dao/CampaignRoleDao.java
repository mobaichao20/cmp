package io.cmp.modules.cam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cmp.modules.cam.entity.CampaignRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 活动权限中间表
 * 
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@Mapper
public interface CampaignRoleDao extends BaseMapper<CampaignRoleEntity> {
	
}
