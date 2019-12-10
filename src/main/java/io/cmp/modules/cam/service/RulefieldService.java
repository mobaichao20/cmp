package io.cmp.modules.cam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.cam.entity.RulefieldEntity;

import java.util.Map;

/**
 * 规则主表
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
public interface RulefieldService extends IService<RulefieldEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

