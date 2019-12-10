package io.cmp.modules.cus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.cus.entity.FilterEntity;
import io.cmp.modules.sys.entity.SysRoleEntity;

import java.util.Map;

/**
 * 过滤器表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
public interface FilterService extends IService<FilterEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveFilter(FilterEntity filter);

    void updateFilter(FilterEntity filter);

    void deleteBatch(String[] filterIds);
}

