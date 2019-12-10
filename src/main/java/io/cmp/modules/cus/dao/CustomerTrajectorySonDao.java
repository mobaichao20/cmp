package io.cmp.modules.cus.dao;

import io.cmp.modules.cus.entity.CustomerTrajectorySonEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 客户轨迹子表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
@Mapper
public interface CustomerTrajectorySonDao extends BaseMapper<CustomerTrajectorySonEntity> {

    /**
     * 根据客户轨迹ID，获取客户轨迹子表列表
     */
    List<CustomerTrajectorySonEntity> queryCustomerTrajectorySonList(String trajectoryId);

    /**
     * 根据过滤器ID数组，批量删除
     */
    int deleteBatch(String[] trajectoryIds);
}
