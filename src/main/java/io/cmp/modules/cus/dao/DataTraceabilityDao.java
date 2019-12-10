package io.cmp.modules.cus.dao;

import io.cmp.modules.cus.entity.DataTraceabilityEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据追溯表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:57
 */
@Mapper
public interface DataTraceabilityDao extends BaseMapper<DataTraceabilityEntity> {
	
}
