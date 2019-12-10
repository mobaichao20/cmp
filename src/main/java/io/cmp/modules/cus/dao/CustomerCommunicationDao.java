package io.cmp.modules.cus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.cmp.modules.cus.entity.CustomerCommunicationEntity;
import io.cmp.modules.cus.vo.CustomerCommunicationCustomerBaseVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 客户信息通讯表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-02 15:13:26
 */
@Mapper
public interface CustomerCommunicationDao extends BaseMapper<CustomerCommunicationEntity> {
    //假删除客户信息通信记录
    public boolean updateByIds(String[] ids);

    List<CustomerCommunicationCustomerBaseVo> queryCustomerCommunicationCustomerBaselist(IPage<CustomerCommunicationCustomerBaseVo> page,@Param("communicationType")String communicationType,@Param("mobile")String mobile,@Param("areaCode")String areaCode,@Param("phone")String phone,@Param("email")String email,@Param("customerName")String customerName,@Param("enName")String enName);
}
