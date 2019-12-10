package io.cmp.modules.cus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.cus.entity.DataTraceabilityEntity;

import java.util.Map;

/**
 * 数据追溯表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:57
 */
public interface DataTraceabilityService extends IService<DataTraceabilityEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

