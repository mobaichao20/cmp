package io.cmp.modules.specialList.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.specialList.entity.SpecialListLogEntity;

import java.util.Map;

/**
 * 特殊名单日志
 */
public interface SpecialListLogService extends IService<SpecialListLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
