package io.cmp.modules.mma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.mma.entity.CrmMediaTemplateFileEntity;

import java.util.Map;

/**
 * 多媒体模板附件表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
public interface CrmMediaTemplateFileService extends IService<CrmMediaTemplateFileEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

