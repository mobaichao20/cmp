package io.cmp.modules.cus.dao;

import io.cmp.modules.cus.entity.CustomerTrajectoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import io.cmp.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.cmp.modules.cus.entity.CustomerTrajectoryEntity;
import java.util.List;

/**
 * 客户轨迹表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
@Mapper
public interface CustomerTrajectoryDao extends BaseMapper<CustomerTrajectoryEntity> {

}
