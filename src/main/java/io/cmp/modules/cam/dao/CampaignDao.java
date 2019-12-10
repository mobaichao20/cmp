package io.cmp.modules.cam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cmp.modules.cam.entity.CampaignEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 活动主表
 * 
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@Mapper
public interface CampaignDao extends BaseMapper<CampaignEntity> {
	
}
