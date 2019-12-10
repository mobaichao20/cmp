package io.cmp.modules.cus.dao;

import io.cmp.modules.cus.entity.FilterEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 过滤器表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
@Mapper
public interface FilterDao extends BaseMapper<FilterEntity> {
	
}
