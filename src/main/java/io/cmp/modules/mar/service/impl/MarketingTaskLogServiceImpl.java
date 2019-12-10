package io.cmp.modules.mar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.mar.dao.MarketingTaskLogDao;
import io.cmp.modules.mar.entity.MarketingTaskLogEntity;
import io.cmp.modules.mar.service.MarketingTaskLogService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("marketingTaskLogService")
public class MarketingTaskLogServiceImpl extends ServiceImpl<MarketingTaskLogDao, MarketingTaskLogEntity> implements MarketingTaskLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MarketingTaskLogEntity> page = this.page(
                new Query<MarketingTaskLogEntity>().getPage(params),
                new QueryWrapper<MarketingTaskLogEntity>()
        );

        return new PageUtils(page);
    }

}