package io.cmp.modules.cam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.cam.entity.CampaignRulefieldEntity;

import java.util.Map;

/**
 * 活动规则中间表
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
public interface CampaignRulefieldService extends IService<CampaignRulefieldEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

