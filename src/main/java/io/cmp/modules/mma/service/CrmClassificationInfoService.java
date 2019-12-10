package io.cmp.modules.mma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.mma.entity.CrmClassificationInfoEntity;

import java.util.Map;

/**
 * 分类信息表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
public interface CrmClassificationInfoService extends IService<CrmClassificationInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

