package io.cmp.modules.cus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.cus.entity.CustomerTrajectoryEntity;
import io.cmp.modules.cus.entity.FilterEntity;
import io.cmp.modules.cus.entity.FilterFieldEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户轨迹表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
public interface CustomerTrajectoryService extends IService<CustomerTrajectoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveCustomerTrajectory(CustomerTrajectoryEntity customerTrajectoryEntity);

    void updateCustomerTrajectory(CustomerTrajectoryEntity customerTrajectoryEntity);

    void deleteBatch(String[] customerTrajectoryrIds);

}

