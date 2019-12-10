package io.cmp.modules.mar.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cmp.modules.mar.entity.MarketingTaskEntity;
import io.cmp.modules.mar.vo.MarketingTaskQueryVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 营销任务主表
 * 
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@Mapper
public interface MarketingTaskDao extends BaseMapper<MarketingTaskEntity> {
    MarketingTaskQueryVo queryById(String marketingId);
}
