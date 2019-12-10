package io.cmp.modules.mma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.mma.entity.CrmMediaTemplateEntity;

import java.util.List;
import java.util.Map;

/**
 * 多媒体模板表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
public interface CrmMediaTemplateService extends IService<CrmMediaTemplateEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean deleteById(String id);

    List findMediaTemplateById(List mediaTemplateList);
}

