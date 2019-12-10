package io.cmp.modules.mar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.mar.entity.MarketingTaskEntity;
import io.cmp.modules.mar.entity.MarketingTaskLogEntity;
import io.cmp.modules.mar.vo.MarketingTaskQueryVo;

import java.util.Map;

/**
 * 营销任务主表
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
public interface MarketingTaskService extends IService<MarketingTaskEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 提交营销数据
     */
    Boolean endService(MarketingTaskEntity marketing, MarketingTaskLogEntity marketingLog);

    /**
     * 根据ID查询营销数据
     * @param marketingId
     * @return
     */
    MarketingTaskQueryVo queryById(String marketingId);

    /**
     * 将外联表为使用的数据新增到营销主表
     */
    void saveNotUsedOutriggerMsg();
}

