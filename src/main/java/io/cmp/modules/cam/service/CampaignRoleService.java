package io.cmp.modules.cam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.cam.entity.CampaignRoleEntity;

import java.util.Map;

/**
 * 活动权限中间表
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
public interface CampaignRoleService extends IService<CampaignRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

