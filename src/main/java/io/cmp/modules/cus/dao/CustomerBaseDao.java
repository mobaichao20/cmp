package io.cmp.modules.cus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cmp.modules.cus.entity.CustomerBaseEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客户信息表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-02 15:13:26
 */
@Mapper
public interface CustomerBaseDao extends BaseMapper<CustomerBaseEntity> {
    //假删除客户信息记录
    boolean updateByIds(String[] ids);

}
