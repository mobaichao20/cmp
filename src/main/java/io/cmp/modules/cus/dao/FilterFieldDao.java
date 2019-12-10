package io.cmp.modules.cus.dao;

import io.cmp.modules.cus.entity.FilterFieldEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 过滤器字段表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:57
 */
@Mapper
public interface FilterFieldDao extends BaseMapper<FilterFieldEntity> {

    /**
     * 根据过滤器ID，获取过滤器字段列表
     */
    List<FilterFieldEntity> queryFilterFieldList(String filterId);

    /**
     * 根据过滤器ID数组，批量删除
     */
    int deleteBatch(String[] filterIds);
}
