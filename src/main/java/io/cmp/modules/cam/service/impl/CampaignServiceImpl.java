package io.cmp.modules.cam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.cam.dao.CampaignDao;
import io.cmp.modules.cam.entity.CampaignEntity;
import io.cmp.modules.cam.service.CampaignService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("campaignService")
public class CampaignServiceImpl extends ServiceImpl<CampaignDao, CampaignEntity> implements CampaignService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CampaignEntity> page = this.page(
                new Query<CampaignEntity>().getPage(params),
                new QueryWrapper<CampaignEntity>()
        );

        return new PageUtils(page);
    }

}