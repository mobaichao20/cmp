package io.cmp.modules.cus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.cus.entity.CustomerBaseEntity;
import io.cmp.modules.cus.entity.FieldConfigurationEntity;

import java.util.List;
import java.util.Map;

/**
 * 字段配置表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
public interface FieldConfigurationService extends IService<FieldConfigurationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<FieldConfigurationEntity> queryList(Map<String, Object> params);
}

