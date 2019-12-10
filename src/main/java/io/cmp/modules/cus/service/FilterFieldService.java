package io.cmp.modules.cus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.cus.entity.FilterFieldEntity;

import java.util.List;
import java.util.Map;

/**
 * 过滤器字段表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:57
 */
public interface FilterFieldService extends IService<FilterFieldEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //保存过滤器与过滤器字段数据
    void saveOrUpdate(String filterId, List<FilterFieldEntity> filterFieldList);

    /**
     * 根据过滤器ID，获取过滤器字段列表
     */
    List<FilterFieldEntity> queryFilterFieldList(String filterId);

    /**
     * 根据过滤器ID数组，批量删除
     */
    int deleteBatch(String[] filterIds);
}

