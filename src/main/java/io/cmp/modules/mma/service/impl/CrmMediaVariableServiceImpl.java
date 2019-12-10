package io.cmp.modules.mma.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.mma.dao.CrmMediaVariableDao;
import io.cmp.modules.mma.entity.CrmMediaVariableEntity;
import io.cmp.modules.mma.service.CrmMediaVariableService;


@Service("crmMediaVariableService")
public class CrmMediaVariableServiceImpl extends ServiceImpl<CrmMediaVariableDao, CrmMediaVariableEntity> implements CrmMediaVariableService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CrmMediaVariableEntity> page = this.page(
                new Query<CrmMediaVariableEntity>().getPage(params),
                new QueryWrapper<CrmMediaVariableEntity>()
        );

        return new PageUtils(page);
    }

}