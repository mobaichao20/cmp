package io.cmp.modules.tel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.tel.entity.CrmChannelManagementEntity;

import java.util.Map;

/**
 * 渠道管理表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-01 10:46:46
 */
public interface CrmChannelManagementService extends IService<CrmChannelManagementEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

