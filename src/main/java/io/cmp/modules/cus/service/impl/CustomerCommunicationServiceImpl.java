package io.cmp.modules.cus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.Constant;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.cus.dao.CustomerCommunicationDao;
import io.cmp.modules.cus.entity.CustomerCommunicationEntity;
import io.cmp.modules.cus.service.CustomerBaseService;
import io.cmp.modules.cus.service.CustomerCommunicationService;
import io.cmp.modules.cus.vo.CustomerCommunicationCustomerBaseVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("customerCommunicationService")
public class CustomerCommunicationServiceImpl extends ServiceImpl<CustomerCommunicationDao, CustomerCommunicationEntity> implements CustomerCommunicationService {
    @Autowired
    private CustomerCommunicationDao customerCommunicationDao;
    @Autowired
    private CustomerCommunicationService customerCommunicationService;
    @Autowired
    private CustomerBaseService customerBaseService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String customerId = (String)params.get("customerId");
        String communicationType = (String)params.get("communicationType");
        String mobile = (String)params.get("mobile");
        String areaCode = (String)params.get("areaCode");
        String phone = (String)params.get("phone");
        String extensionNum = (String)params.get("extensionNum");
        String qq = (String)params.get("qq");
        String weixin = (String)params.get("weixin");
        String email = (String)params.get("email");
        String fax = (String)params.get("fax");
        String province = (String)params.get("province");
        String city = (String)params.get("city");
        String area = (String)params.get("area");
        String address = (String)params.get("address");
        String postCode = (String)params.get("postCode");
        String isContact = (String)params.get("isContact");
        String createCode = (String)params.get("createCode");
        String createName = (String)params.get("createName");
        String startCreateTime = (String)params.get("startCreateTime");
        String endCreateTime = (String)params.get("endCreateTime");
        String updateCode = (String)params.get("updateCode");
        String updateName = (String)params.get("updateName");
        String startUpdateTime = (String)params.get("startUpdateTime");
        String endUpdateTime = (String)params.get("endUpdateTime");
        String isDelete = (String)params.get("isDelete");
        IPage<CustomerCommunicationEntity> page = this.page(
                new Query<CustomerCommunicationEntity>().getPage(params),
                new QueryWrapper<CustomerCommunicationEntity>()
                        .eq(StringUtils.isNotBlank(customerId),"customer_id", customerId)
                        .eq(StringUtils.isNotBlank(communicationType),"communication_type", communicationType)
                        .like(StringUtils.isNotBlank(mobile),"mobile", mobile)
                        .like(StringUtils.isNotBlank(areaCode),"area_code", areaCode)
                        .like(StringUtils.isNotBlank(phone),"phone", phone)
                        .like(StringUtils.isNotBlank(extensionNum),"extension_num", extensionNum)
                        .like(StringUtils.isNotBlank(qq),"qq", qq)
                        .like(StringUtils.isNotBlank(weixin),"weixin", weixin)
                        .like(StringUtils.isNotBlank(email),"email", email)
                        .like(StringUtils.isNotBlank(fax),"fax", fax)
                        .eq(StringUtils.isNotBlank(province),"province", province)
                        .eq(StringUtils.isNotBlank(city),"city", city)
                        .eq(StringUtils.isNotBlank(area),"area", area)
                        .like(StringUtils.isNotBlank(address),"address", address)
                        .eq(StringUtils.isNotBlank(postCode),"post_code", postCode)
                        .eq(StringUtils.isNotBlank(isContact),"is_contact", isContact)
                        .eq(StringUtils.isNotBlank(createCode),"create_code", createCode)
                        .like(StringUtils.isNotBlank(createName),"create_name", createName)
                        .ge(StringUtils.isNotBlank(startCreateTime),"create_time",startCreateTime)
                        .le(StringUtils.isNotBlank(endCreateTime),"create_time",endCreateTime)
                        .eq(StringUtils.isNotBlank(updateCode),"update_code", updateCode)
                        .like(StringUtils.isNotBlank(updateName),"update_name", updateName)
                        .ge(StringUtils.isNotBlank(startUpdateTime),"update_time",startUpdateTime)
                        .le(StringUtils.isNotBlank(endUpdateTime),"update_time",endUpdateTime)
                        .eq(StringUtils.isNotBlank(isDelete),"is_delete", isDelete)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        return new PageUtils(page);
    }

    @Override
    public boolean updateByIds(String[] ids)
    {
        return customerCommunicationDao.updateByIds(ids);
    }

    @Override
    public CustomerCommunicationEntity findCommunicaById(String id) {
        return baseMapper.selectOne(
                new QueryWrapper<CustomerCommunicationEntity>()
                        .eq(StringUtils.isNotBlank(id),"customer_id", id)
        );
    }


    @Override
    public PageUtils queryCustomerCommunicationCustomerBaselist(Map<String, Object> params) {
        String customerId = (String)params.get("customerId");
        String communicationType = (String)params.get("communicationType");
        String mobile = (String)params.get("mobile");
        String areaCode = (String)params.get("areaCode");
        String phone = (String)params.get("phone");
        String extensionNum = (String)params.get("extensionNum");
        String qq = (String)params.get("qq");
        String weixin = (String)params.get("weixin");
        String email = (String)params.get("email");
        String fax = (String)params.get("fax");
        String province = (String)params.get("province");
        String city = (String)params.get("city");
        String area = (String)params.get("area");
        String address = (String)params.get("address");
        String postCode = (String)params.get("postCode");
        String isContact = (String)params.get("isContact");
        String createCode = (String)params.get("createCode");
        String createName = (String)params.get("createName");
        String startCreateTime = (String)params.get("startCreateTime");
        String endCreateTime = (String)params.get("endCreateTime");
        String updateCode = (String)params.get("updateCode");
        String updateName = (String)params.get("updateName");
        String startUpdateTime = (String)params.get("startUpdateTime");
        String endUpdateTime = (String)params.get("endUpdateTime");
        String isDelete = (String)params.get("isDelete");

        String surname = (String)params.get("surname");
        String customerName = (String)params.get("customerName");
        String enName = (String)params.get("enName");
        String beforeName = (String)params.get("beforeName");
        String nickname = (String)params.get("nickname");
        String idNumber = (String)params.get("idNumber");
        String driverNumber = (String)params.get("driverNumber");
        String nationality = (String)params.get("nationality");
        String nation = (String)params.get("nation");
        String nativePlace = (String)params.get("nativePlace");
        String sex = (String)params.get("sex");
        String age = (String)params.get("age");
        String birthday = (String)params.get("birthday");
        String height = (String)params.get("height");
        String weight = (String)params.get("weight");
        String bloodType = (String)params.get("bloodType");
        String politicalAppearance = (String)params.get("politicalAppearance");
        String birth = (String)params.get("birth");
        String workingYears = (String)params.get("workingYears");
        String maritalStatus = (String)params.get("maritalStatus");
        String customerEducation = (String)params.get("customerEducation");
        String headUrl = (String)params.get("headUrl");
        String company = (String)params.get("company");
        String department = (String)params.get("department");
        String duty = (String)params.get("duty");
        String belongCode = (String)params.get("belongCode");
        String belongInstitutions = (String)params.get("belongInstitutions");
        String customerNotes = (String)params.get("customerNotes");
        String isEffective = (String)params.get("isEffective");


        //构建分页参数
        IPage<CustomerCommunicationCustomerBaseVo> page =  new Query<CustomerCommunicationCustomerBaseVo>().getPage(params);
        CustomerCommunicationCustomerBaseVo customerCommunicationCustomerBaseVo = new CustomerCommunicationCustomerBaseVo();
        //调用分页查询
        List<CustomerCommunicationCustomerBaseVo> customerCommunicationCustomerBaseVoList = baseMapper.queryCustomerCommunicationCustomerBaselist(page,communicationType,mobile,areaCode,phone,email,customerName,enName);
        //设置结果
        page.setRecords(customerCommunicationCustomerBaseVoList);

        return new PageUtils(page);
    }
}