package io.cmp.modules.cus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.cus.entity.CustomerCommunicationEntity;

import java.util.Map;

/**
 * 客户信息通讯表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-02 15:13:26
 */
public interface CustomerCommunicationService extends IService<CustomerCommunicationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public boolean updateByIds(String[] ids);

    CustomerCommunicationEntity findCommunicaById(String id);

    PageUtils queryCustomerCommunicationCustomerBaselist(Map<String, Object> params);

}

