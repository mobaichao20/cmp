package io.cmp.modules.mma.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.mma.dao.CrmClassificationInfoDao;
import io.cmp.modules.mma.entity.CrmClassificationInfoEntity;
import io.cmp.modules.mma.service.CrmClassificationInfoService;


@Service("crmClassificationInfoService")
public class CrmClassificationInfoServiceImpl extends ServiceImpl<CrmClassificationInfoDao, CrmClassificationInfoEntity> implements CrmClassificationInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CrmClassificationInfoEntity> page = this.page(
                new Query<CrmClassificationInfoEntity>().getPage(params),
                new QueryWrapper<CrmClassificationInfoEntity>()
        );

        return new PageUtils(page);
    }

}