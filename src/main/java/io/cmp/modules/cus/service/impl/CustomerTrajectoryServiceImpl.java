package io.cmp.modules.cus.service.impl;

import io.cmp.common.utils.Constant;
import io.cmp.modules.cus.entity.CustomerTrajectorySonEntity;
import io.cmp.modules.cus.entity.FilterEntity;
import io.cmp.modules.cus.entity.FilterFieldEntity;
import io.cmp.modules.cus.service.CustomerTrajectorySonService;
import io.cmp.modules.cus.service.FilterFieldService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.cus.dao.CustomerTrajectoryDao;
import io.cmp.modules.cus.entity.CustomerTrajectoryEntity;
import io.cmp.modules.cus.service.CustomerTrajectoryService;
import org.springframework.transaction.annotation.Transactional;
import io.cmp.modules.cus.dao.CustomerTrajectoryDao;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Service("customerTrajectoryService")
public class CustomerTrajectoryServiceImpl extends ServiceImpl<CustomerTrajectoryDao, CustomerTrajectoryEntity> implements CustomerTrajectoryService {

    @Autowired
    private CustomerTrajectorySonService customerTrajectorySonService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String customerId = (String)params.get("customerId");
        String trajectoryName = (String)params.get("trajectoryName");
        String trajectoryType = (String)params.get("trajectoryType");
        String createCode = (String)params.get("createCode");
        String createName = (String)params.get("createName");
        String startCreateTime = (String)params.get("startCreateTime");
        String endCreateTime = (String)params.get("endCreateTime");
        String startUpdateTime = (String)params.get("startUpdateTime");
        String endUpdateTime = (String)params.get("endUpdateTime");
        IPage<CustomerTrajectoryEntity> page = this.page(
                new Query<CustomerTrajectoryEntity>().getPage(params),
                new QueryWrapper<CustomerTrajectoryEntity>()
                        .eq(StringUtils.isNotBlank(customerId),"customer_id", customerId)
                        .like(StringUtils.isNotBlank(trajectoryName),"trajectory_name", trajectoryName)
                        .eq(StringUtils.isNotBlank(trajectoryType),"trajectory_type", trajectoryType)
                        .eq(StringUtils.isNotBlank(createCode),"create_code", createCode)
                        .like(StringUtils.isNotBlank(createName),"create_name", createName)
                        .ge(StringUtils.isNotBlank(startCreateTime),"create_time",startCreateTime)
                        .le(StringUtils.isNotBlank(endCreateTime),"create_time",endCreateTime)
                        .ge(StringUtils.isNotBlank(startUpdateTime),"update_time",startUpdateTime)
                        .le(StringUtils.isNotBlank(endUpdateTime),"update_time",endUpdateTime)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        if(page.getRecords()!=null) {
            if (page.getRecords().size() != 0) {
                for(CustomerTrajectoryEntity customerTrajectoryEntity : page.getRecords()){
                     List<CustomerTrajectorySonEntity> customerTrajectorySonEntityList = customerTrajectorySonService.queryCustomerTrajectorySonList(customerTrajectoryEntity.getId());
                     customerTrajectoryEntity.setCustomerTrajectorySonEntityList(customerTrajectorySonEntityList);
                }
            }
        }

        return new PageUtils(page);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCustomerTrajectory(CustomerTrajectoryEntity customerTrajectory) {

        customerTrajectory.setCreateTime(new Date());
        this.save(customerTrajectory);

        //保存客户轨迹与客户轨迹子表数据
        if(customerTrajectory.getCustomerTrajectorySonEntityList()!=null) {
            if (customerTrajectory.getCustomerTrajectorySonEntityList().size() != 0) {
                customerTrajectorySonService.saveOrUpdate(customerTrajectory.getId(), customerTrajectory.getCustomerTrajectorySonEntityList());
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCustomerTrajectory(CustomerTrajectoryEntity customerTrajectory) {

        this.updateById(customerTrajectory);

        //保存客户轨迹与客户轨迹子表数据
        if(customerTrajectory.getCustomerTrajectorySonEntityList()!=null) {
            if (customerTrajectory.getCustomerTrajectorySonEntityList().size() != 0) {
                customerTrajectorySonService.saveOrUpdate(customerTrajectory.getId(), customerTrajectory.getCustomerTrajectorySonEntityList());
            }
        }


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] customerTrajectoryIds) {
        //删除客户轨迹
        this.removeByIds(Arrays.asList(customerTrajectoryIds));

        //删除客户轨迹与客户轨迹子表
        customerTrajectorySonService.deleteBatch(customerTrajectoryIds);


    }


}