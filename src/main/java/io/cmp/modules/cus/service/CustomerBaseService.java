package io.cmp.modules.cus.service;
import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.cus.entity.CustomerBaseEntity;

import java.util.List;
import java.util.Map;

/**
 * 客户信息表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-02 15:13:26
 */
public interface CustomerBaseService extends IService<CustomerBaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public boolean updateByIds(String[] ids);

    List<CustomerBaseEntity> selectList(String[] ids);

    List<CustomerBaseEntity> queryList(Map<String, Object> params);

}

