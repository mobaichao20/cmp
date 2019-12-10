package io.cmp.modules.cam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.cam.entity.CampaignStateconfigEntity;

import java.util.Map;

/**
 * 活动状态配置
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
public interface CampaignStateconfigService extends IService<CampaignStateconfigEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

