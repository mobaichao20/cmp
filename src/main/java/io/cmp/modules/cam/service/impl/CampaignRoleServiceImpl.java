package io.cmp.modules.cam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.cam.dao.CampaignRoleDao;
import io.cmp.modules.cam.entity.CampaignRoleEntity;
import io.cmp.modules.cam.service.CampaignRoleService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("campaignRoleService")
public class CampaignRoleServiceImpl extends ServiceImpl<CampaignRoleDao, CampaignRoleEntity> implements CampaignRoleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CampaignRoleEntity> page = this.page(
                new Query<CampaignRoleEntity>().getPage(params),
                new QueryWrapper<CampaignRoleEntity>()
        );

        return new PageUtils(page);
    }

}