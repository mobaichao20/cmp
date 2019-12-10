package io.cmp.modules.wot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cmp.modules.wot.entity.SysNodeLocusEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工单节点轨迹
 */
@Mapper
public interface SysNodeLocusDao extends BaseMapper<SysNodeLocusEntity> {

    /**
     * 删除模板
     */
    void deleteBatch(String[] needDelwotps);
}
