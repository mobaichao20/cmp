package io.cmp.modules.cus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.cus.entity.CustomerTrajectoryEntity;
import io.cmp.modules.cus.entity.CustomerTrajectorySonEntity;
import io.cmp.modules.cus.entity.FilterFieldEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户轨迹子表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
public interface CustomerTrajectorySonService extends IService<CustomerTrajectorySonEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据客户轨迹ID，获取客户轨迹子表列表
     */
    List<CustomerTrajectorySonEntity> queryCustomerTrajectorySonList(String trajectoryId);


    //保存客户轨迹与客户轨迹子表数据
    void saveOrUpdate(String trajectoryId, List<CustomerTrajectorySonEntity> customerTrajectorySonList);

    /**
     * 根据客户轨迹ID数组，批量删除
     */
    int deleteBatch(String[] customerTrajectoryIds);

}

