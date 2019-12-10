package io.cmp.modules.wot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.wot.entity.SysWorkOrderFieldEntity;

import java.util.Map;

/**
 * 字段
 */
public interface SysWorkOrderFieldService extends IService<SysWorkOrderFieldEntity> {
    PageUtils queryPage(Map<String, Object> params);

}
