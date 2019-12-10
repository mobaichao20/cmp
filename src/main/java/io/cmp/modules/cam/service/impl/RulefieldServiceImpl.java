package io.cmp.modules.cam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.cam.dao.RulefieldDao;
import io.cmp.modules.cam.entity.RulefieldEntity;
import io.cmp.modules.cam.service.RulefieldService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("rulefieldService")
public class RulefieldServiceImpl extends ServiceImpl<RulefieldDao, RulefieldEntity> implements RulefieldService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RulefieldEntity> page = this.page(
                new Query<RulefieldEntity>().getPage(params),
                new QueryWrapper<RulefieldEntity>()
        );

        return new PageUtils(page);
    }

}