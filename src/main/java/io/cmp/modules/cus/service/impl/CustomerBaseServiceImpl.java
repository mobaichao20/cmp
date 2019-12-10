package io.cmp.modules.cus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.Constant;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.cus.dao.CustomerBaseDao;
import io.cmp.modules.cus.entity.CustomerBaseEntity;
import io.cmp.modules.cus.service.CustomerBaseService;
import io.cmp.modules.cus.service.CustomerCommunicationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("customerBaseService")
public class CustomerBaseServiceImpl extends ServiceImpl<CustomerBaseDao, CustomerBaseEntity> implements CustomerBaseService {

    @Autowired
    private CustomerBaseDao customerBaseDao;
    @Autowired
    private CustomerCommunicationService customerCommunicationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
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
        String createCode = (String)params.get("createCode");
        String createName = (String)params.get("createName");
        String startCreateTime = (String)params.get("startCreateTime");
        String endCreateTime = (String)params.get("endCreateTime");
        String updateCode = (String)params.get("updateCode");
        String updateName = (String)params.get("updateName");
        String startUpdateTime = (String)params.get("startUpdateTime");
        String endUpdateTime = (String)params.get("endUpdateTime");
        String isDelete = (String)params.get("isDelete");
        IPage<CustomerBaseEntity> page = this.page(
                new Query<CustomerBaseEntity>().getPage(params),
                new QueryWrapper<CustomerBaseEntity>()
                        .like(StringUtils.isNotBlank(surname),"surname", surname)
                        .like(StringUtils.isNotBlank(customerName),"customer_name", customerName)
                        .like(StringUtils.isNotBlank(enName),"en_name", enName)
                        .like(StringUtils.isNotBlank(beforeName),"before_name", beforeName)
                        .like(StringUtils.isNotBlank(nickname),"nickname", nickname)
                        .like(StringUtils.isNotBlank(idNumber),"id_number", idNumber)
                        .eq(StringUtils.isNotBlank(driverNumber),"driver_number", driverNumber)
                        .eq(StringUtils.isNotBlank(nationality),"nationality", nationality)
                        .eq(StringUtils.isNotBlank(nation),"nation", nation)
                        .eq(StringUtils.isNotBlank(nativePlace),"native_place", nativePlace)
                        .eq(StringUtils.isNotBlank(sex),"sex", sex)
                        .eq(StringUtils.isNotBlank(age),"age", age)
                        .eq(StringUtils.isNotBlank(birthday),"birthday", birthday)
                        .eq(StringUtils.isNotBlank(height),"height", height)
                        .eq(StringUtils.isNotBlank(weight),"weight", weight)
                        .eq(StringUtils.isNotBlank(bloodType),"blood_type", bloodType)
                        .like(StringUtils.isNotBlank(politicalAppearance),"political_appearance", politicalAppearance)
                        .eq(StringUtils.isNotBlank(birth),"birth", birth)
                        .eq(StringUtils.isNotBlank(workingYears),"working_years", workingYears)
                        .eq(StringUtils.isNotBlank(maritalStatus),"marital_status", maritalStatus)
                        .eq(StringUtils.isNotBlank(customerEducation),"customer_education", customerEducation)
                        .like(StringUtils.isNotBlank(headUrl),"head_url", headUrl)
                        .like(StringUtils.isNotBlank(company),"company", company)
                        .like(StringUtils.isNotBlank(department),"department", department)
                        .like(StringUtils.isNotBlank(duty),"duty", duty)
                        .eq(StringUtils.isNotBlank(belongCode),"belong_code", belongCode)
                        .eq(StringUtils.isNotBlank(belongInstitutions),"belong_institutions", belongInstitutions)
                        .like(StringUtils.isNotBlank(customerNotes),"customer_notes", customerNotes)
                        .eq(StringUtils.isNotBlank(isEffective),"is_effective", isEffective)
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
        return customerBaseDao.updateByIds(ids);
    }

    @Override
    public List<CustomerBaseEntity> selectList(String[] ids) {
        return this.baseMapper.selectBatchIds(Arrays.asList(ids));
    }

    @Override
    public List<CustomerBaseEntity> queryList(Map<String, Object> params) {
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
        String createCode = (String)params.get("createCode");
        String createName = (String)params.get("createName");
        String startCreateTime = (String)params.get("startCreateTime");
        String endCreateTime = (String)params.get("endCreateTime");
        String updateCode = (String)params.get("updateCode");
        String updateName = (String)params.get("updateName");
        String startUpdateTime = (String)params.get("startUpdateTime");
        String endUpdateTime = (String)params.get("endUpdateTime");
        String isDelete = (String)params.get("isDelete");

        List<CustomerBaseEntity> customerBaseList = this.list(new QueryWrapper<CustomerBaseEntity>()
                .like(StringUtils.isNotBlank(surname),"surname", surname)
                .like(StringUtils.isNotBlank(customerName),"customer_name", customerName)
                .like(StringUtils.isNotBlank(enName),"en_name", enName)
                .like(StringUtils.isNotBlank(beforeName),"before_name", beforeName)
                .like(StringUtils.isNotBlank(nickname),"nickname", nickname)
                .eq(StringUtils.isNotBlank(idNumber),"id_number", idNumber)
                .eq(StringUtils.isNotBlank(driverNumber),"driver_number", driverNumber)
                .eq(StringUtils.isNotBlank(nationality),"nationality", nationality)
                .eq(StringUtils.isNotBlank(nation),"nation", nation)
                .eq(StringUtils.isNotBlank(nativePlace),"native_place", nativePlace)
                .eq(StringUtils.isNotBlank(sex),"sex", sex)
                .eq(StringUtils.isNotBlank(age),"age", age)
                .eq(StringUtils.isNotBlank(birthday),"birthday", birthday)
                .eq(StringUtils.isNotBlank(height),"height", height)
                .eq(StringUtils.isNotBlank(weight),"weight", weight)
                .eq(StringUtils.isNotBlank(bloodType),"blood_type", bloodType)
                .like(StringUtils.isNotBlank(politicalAppearance),"political_appearance", politicalAppearance)
                .eq(StringUtils.isNotBlank(birth),"birth", birth)
                .eq(StringUtils.isNotBlank(workingYears),"working_years", workingYears)
                .eq(StringUtils.isNotBlank(maritalStatus),"marital_status", maritalStatus)
                .eq(StringUtils.isNotBlank(customerEducation),"customer_education", customerEducation)
                .like(StringUtils.isNotBlank(headUrl),"head_url", headUrl)
                .like(StringUtils.isNotBlank(company),"company", company)
                .like(StringUtils.isNotBlank(department),"department", department)
                .like(StringUtils.isNotBlank(duty),"duty", duty)
                .eq(StringUtils.isNotBlank(belongCode),"belong_code", belongCode)
                .eq(StringUtils.isNotBlank(belongInstitutions),"belong_institutions", belongInstitutions)
                .like(StringUtils.isNotBlank(customerNotes),"customer_notes", customerNotes)
                .eq(StringUtils.isNotBlank(isEffective),"is_effective", isEffective)
                .eq(StringUtils.isNotBlank(createCode),"create_code", createCode)
                .like(StringUtils.isNotBlank(createName),"create_name", createName)
                .ge(StringUtils.isNotBlank(startCreateTime),"create_time",startCreateTime)
                .le(StringUtils.isNotBlank(endCreateTime),"create_time",endCreateTime)
                .eq(StringUtils.isNotBlank(updateCode),"update_code", updateCode)
                .like(StringUtils.isNotBlank(updateName),"update_name", updateName)
                .ge(StringUtils.isNotBlank(startUpdateTime),"update_time",startUpdateTime)
                .le(StringUtils.isNotBlank(endUpdateTime),"update_time",endUpdateTime)
                .eq(StringUtils.isNotBlank(isDelete),"is_delete", isDelete)
                .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER)));
        return customerBaseList;
    }



}