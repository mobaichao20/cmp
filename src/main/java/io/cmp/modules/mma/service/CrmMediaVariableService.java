package io.cmp.modules.mma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.mma.entity.CrmMediaVariableEntity;

import java.util.Map;

/**
 * 多媒体变量表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
public interface CrmMediaVariableService extends IService<CrmMediaVariableEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

