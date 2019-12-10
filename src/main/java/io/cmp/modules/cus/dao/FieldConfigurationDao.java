package io.cmp.modules.cus.dao;

import io.cmp.modules.cus.entity.FieldConfigurationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字段配置表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
@Mapper
public interface FieldConfigurationDao extends BaseMapper<FieldConfigurationEntity> {
	
}
