package io.cmp.modules.wot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cmp.modules.wot.entity.SysNodeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工单节点
 */
@Mapper
public interface SysNodeDao extends BaseMapper<SysNodeEntity> {

    /**
     * 删除模板
     */
    void deleteBatch(String[] needDelwotps);
}
