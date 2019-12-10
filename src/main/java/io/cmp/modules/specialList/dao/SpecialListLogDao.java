package io.cmp.modules.specialList.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cmp.modules.specialList.entity.SpecialListLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 特殊名单日志
 */
@Mapper
public interface SpecialListLogDao extends BaseMapper<SpecialListLogEntity> {
}
