package io.cmp.modules.mar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.mar.entity.MarketingTaskLogEntity;

import java.util.Map;

/**
 * 营销任务操作历史表
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
public interface MarketingTaskLogService extends IService<MarketingTaskLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

