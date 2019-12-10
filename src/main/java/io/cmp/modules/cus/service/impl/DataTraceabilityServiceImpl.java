package io.cmp.modules.cus.service.impl;

import io.cmp.common.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.cus.dao.DataTraceabilityDao;
import io.cmp.modules.cus.entity.DataTraceabilityEntity;
import io.cmp.modules.cus.service.DataTraceabilityService;


@Service("dataTraceabilityService")
public class DataTraceabilityServiceImpl extends ServiceImpl<DataTraceabilityDao, DataTraceabilityEntity> implements DataTraceabilityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String dataName = (String)params.get("dataName");
        String dataAttribute = (String)params.get("dataAttribute");
        String displayName = (String)params.get("displayName");
        String type = (String)params.get("type");
        String dataValue = (String)params.get("dataValue");
        String updateCode = (String)params.get("updateCode");
        String updateName = (String)params.get("updateName");
        String startUpdateTime = (String)params.get("startUpdateTime");
        String endUpdateTime = (String)params.get("endUpdateTime");

        IPage<DataTraceabilityEntity> page = this.page(
                new Query<DataTraceabilityEntity>().getPage(params),
                new QueryWrapper<DataTraceabilityEntity>()
                        .like(StringUtils.isNotBlank(dataName),"data_name", dataName)
                        .like(StringUtils.isNotBlank(dataAttribute),"data_attribute", dataAttribute)
                        .like(StringUtils.isNotBlank(displayName),"display_name", displayName)
                        .like(StringUtils.isNotBlank(type),"type", type)
                        .like(StringUtils.isNotBlank(dataValue),"data_value", dataValue)
                        .eq(StringUtils.isNotBlank(updateCode),"update_code", updateCode)
                        .like(StringUtils.isNotBlank(updateName),"update_name", updateName)
                        .ge(StringUtils.isNotBlank(startUpdateTime),"update_time",startUpdateTime)
                        .le(StringUtils.isNotBlank(endUpdateTime),"update_time",endUpdateTime)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        return new PageUtils(page);
    }

}