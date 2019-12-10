package io.cmp.modules.cus.service.impl;

import io.cmp.common.utils.Constant;
import io.cmp.modules.cus.entity.FilterFieldEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.cus.dao.CustomerTrajectorySonDao;
import io.cmp.modules.cus.entity.CustomerTrajectorySonEntity;
import io.cmp.modules.cus.service.CustomerTrajectorySonService;
import org.springframework.transaction.annotation.Transactional;


@Service("customerTrajectorySonService")
public class CustomerTrajectorySonServiceImpl extends ServiceImpl<CustomerTrajectorySonDao, CustomerTrajectorySonEntity> implements CustomerTrajectorySonService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String trajectoryId = (String)params.get("trajectoryId");
        String businessId = (String)params.get("businessId");
        String businessName = (String)params.get("businessName");
        IPage<CustomerTrajectorySonEntity> page = this.page(
                new Query<CustomerTrajectorySonEntity>().getPage(params),
                new QueryWrapper<CustomerTrajectorySonEntity>()
                        .eq(StringUtils.isNotBlank(trajectoryId),"trajectory_id", trajectoryId)
                        .eq(StringUtils.isNotBlank(businessId),"business_id", businessId)
                        .like(StringUtils.isNotBlank(businessName),"business_name", businessName)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        return new PageUtils(page);
    }

    /**
     * 根据客户轨迹ID，获取客户轨迹子表列表
     */
    @Override
    public List<CustomerTrajectorySonEntity> queryCustomerTrajectorySonList(String trajectoryId) {
        return baseMapper.queryCustomerTrajectorySonList(trajectoryId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(String trajectoryId, List<CustomerTrajectorySonEntity> customerTrajectorySonEntityList) {
        //先删除过滤器与过滤器字段数据
        deleteBatch(new String[]{trajectoryId});

        if(customerTrajectorySonEntityList.size() == 0){
            return ;
        }

        //保存过滤器与过滤器字段数据
        for(CustomerTrajectorySonEntity customerTrajectorySonEntity : customerTrajectorySonEntityList){
            customerTrajectorySonEntity.setTrajectoryId(trajectoryId);
            customerTrajectorySonEntity.setCreateTime(new Date());
            this.save(customerTrajectorySonEntity);
        }
    }

    @Override
    public int deleteBatch(String[] trajectoryIds){
        return baseMapper.deleteBatch(trajectoryIds);
    }

}