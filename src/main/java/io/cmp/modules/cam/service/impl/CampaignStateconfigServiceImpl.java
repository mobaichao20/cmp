package io.cmp.modules.cam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.cam.dao.CampaignStateconfigDao;
import io.cmp.modules.cam.entity.CampaignStateconfigEntity;
import io.cmp.modules.cam.service.CampaignStateconfigService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("campaignStateconfigService")
public class CampaignStateconfigServiceImpl extends ServiceImpl<CampaignStateconfigDao, CampaignStateconfigEntity> implements CampaignStateconfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CampaignStateconfigEntity> page = this.page(
                new Query<CampaignStateconfigEntity>().getPage(params),
                new QueryWrapper<CampaignStateconfigEntity>()
        );

        return new PageUtils(page);
    }

}