package io.cmp.modules.specialList.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cmp.modules.specialList.entity.SpecialListEntity;
import io.cmp.modules.specialList.vo.VoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 特殊名单
 */
@Mapper
public interface SpecialListDao extends BaseMapper<SpecialListEntity> {
    /**
     * 根据手机号或客户查询名单
     * @param params
     * @return
     */
    List<VoEntity> getByPhone(@Param("params") Map<String, Object> params);

    /**
     * 根据表单手机号查询名单
     * @param params
     * @return
     */
    List<SpecialListEntity> queryBySel(@Param("params") Map<String, Object> params);

    /**
     * 批量删除
     * @param listIds
     */
    void deleteBatch(Long[] listIds);
}
