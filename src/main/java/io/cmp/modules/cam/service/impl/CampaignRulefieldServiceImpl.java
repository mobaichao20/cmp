package io.cmp.modules.cam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.cam.dao.CampaignRulefieldDao;
import io.cmp.modules.cam.entity.CampaignRulefieldEntity;
import io.cmp.modules.cam.service.CampaignRulefieldService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("campaignRulefieldService")
public class CampaignRulefieldServiceImpl extends ServiceImpl<CampaignRulefieldDao, CampaignRulefieldEntity> implements CampaignRulefieldService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CampaignRulefieldEntity> page = this.page(
                new Query<CampaignRulefieldEntity>().getPage(params),
                new QueryWrapper<CampaignRulefieldEntity>()
        );

        return new PageUtils(page);
    }

}