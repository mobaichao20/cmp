package io.cmp.modules.tel.service.impl;

import io.cmp.common.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.tel.dao.CrmChannelManagementDao;
import io.cmp.modules.tel.entity.CrmChannelManagementEntity;
import io.cmp.modules.tel.service.CrmChannelManagementService;


@Service("crmChannelManagementService")
public class CrmChannelManagementServiceImpl extends ServiceImpl<CrmChannelManagementDao, CrmChannelManagementEntity> implements CrmChannelManagementService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String trafficBrand = (String)params.get("trafficBrand");
        String ctiAddressMaster = (String)params.get("ctiAddressMaster");
        String ctiAddressSlave = (String)params.get("ctiAddressSlave");
        String satisfactionNodeNumber = (String)params.get("satisfactionNodeNumber");
        String broadcasterNode = (String)params.get("broadcasterNode");
        String workingHoursBegin = (String)params.get("workingHoursBegin");
        String workingHoursEnd = (String)params.get("workingHoursEnd");
        String isBlacklist = (String)params.get("isBlacklist");
        String createCode = (String)params.get("createCode");
        String createName = (String)params.get("createName");
        String startCreateTime = (String)params.get("startCreateTime");
        String endCreateTime = (String)params.get("endCreateTime");
        String updateCode = (String)params.get("updateCode");
        String updateName = (String)params.get("updateName");
        String startUpdateTime = (String)params.get("startUpdateTime");
        String endUpdateTime = (String)params.get("endUpdateTime");

        IPage<CrmChannelManagementEntity> page = this.page(
                new Query<CrmChannelManagementEntity>().getPage(params),
                new QueryWrapper<CrmChannelManagementEntity>()
                        .like(StringUtils.isNotBlank(trafficBrand),"traffic_brand", trafficBrand)
                        .eq(StringUtils.isNotBlank(ctiAddressMaster),"cti_address_master", ctiAddressMaster)
                        .eq(StringUtils.isNotBlank(ctiAddressSlave),"cti_address_slave", ctiAddressSlave)
                        .eq(StringUtils.isNotBlank(satisfactionNodeNumber),"satisfaction_node_number", satisfactionNodeNumber)
                        .eq(StringUtils.isNotBlank(broadcasterNode),"broadcaster_node", broadcasterNode)
                        .eq(StringUtils.isNotBlank(workingHoursBegin),"working_hours_begin", workingHoursBegin)
                        .eq(StringUtils.isNotBlank(workingHoursEnd),"working_hours_end", workingHoursEnd)
                        .eq(StringUtils.isNotBlank(isBlacklist),"is_blacklist", isBlacklist)
                        .eq(StringUtils.isNotBlank(createCode),"create_code", createCode)
                        .like(StringUtils.isNotBlank(createName),"create_name", createName)
                        .ge(StringUtils.isNotBlank(startCreateTime),"create_time",startCreateTime)
                        .le(StringUtils.isNotBlank(endCreateTime),"create_time",endCreateTime)
                        .eq(StringUtils.isNotBlank(updateCode),"update_code", updateCode)
                        .like(StringUtils.isNotBlank(updateName),"update_name", updateName)
                        .ge(StringUtils.isNotBlank(startUpdateTime),"update_time",startUpdateTime)
                        .le(StringUtils.isNotBlank(endUpdateTime),"update_time",endUpdateTime)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        return new PageUtils(page);
    }

}