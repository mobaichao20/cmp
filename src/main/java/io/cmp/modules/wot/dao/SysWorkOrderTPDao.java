package io.cmp.modules.wot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cmp.modules.wot.entity.SysWorkOrderTPEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 模板工单
 */
@Mapper
public interface SysWorkOrderTPDao extends BaseMapper<SysWorkOrderTPEntity> {

    /**
     * 删除模板
     */
    void deleteBatch(String[] needDelwotps);
}
