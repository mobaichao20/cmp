package io.cmp.modules.out.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.out.dao.OutriggerMessageDao;
import io.cmp.modules.out.entity.OutriggerMessageEntity;
import io.cmp.modules.out.service.OutriggerMessageService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("outriggerMessageService")
public class OutriggerMessageServiceImpl extends ServiceImpl<OutriggerMessageDao, OutriggerMessageEntity> implements OutriggerMessageService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OutriggerMessageEntity> page = this.page(
                new Query<OutriggerMessageEntity>().getPage(params),
                new QueryWrapper<OutriggerMessageEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<OutriggerMessageEntity> queryNotUsed() {
        return baseMapper.queryNotUsed();
    }

    /**
     * 新增外部数据（此方法用于对外部数据做特殊处理）
     * @param outEntity
     * @return
     */
    @Override
    public Boolean saveOutriggerMsg(OutriggerMessageEntity outEntity) {
        //设置默认值，不需要可删除
        outEntity.setCreateTime(new Date());
        outEntity.setCustomerGender(3);
        outEntity.setOutriggerStatus(1);
        return this.save(outEntity);
    }
}