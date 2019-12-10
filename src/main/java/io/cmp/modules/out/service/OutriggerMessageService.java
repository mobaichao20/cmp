package io.cmp.modules.out.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.out.entity.OutriggerMessageEntity;

import java.util.List;
import java.util.Map;

/**
 * 外联数据表
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
public interface OutriggerMessageService extends IService<OutriggerMessageEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询未使用数据
     * @return
     */
    List<OutriggerMessageEntity> queryNotUsed();

    /**
     * 新增外部数据
     * @param outEntity
     * @return
     */
    Boolean saveOutriggerMsg(OutriggerMessageEntity outEntity);
}

