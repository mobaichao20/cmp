package io.cmp.modules.cus.service.impl;

import io.cmp.common.utils.Constant;
import io.cmp.modules.cus.entity.CustomerBaseEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.cus.dao.FieldConfigurationDao;
import io.cmp.modules.cus.entity.FieldConfigurationEntity;
import io.cmp.modules.cus.service.FieldConfigurationService;


@Service("fieldConfigurationService")
public class FieldConfigurationServiceImpl extends ServiceImpl<FieldConfigurationDao, FieldConfigurationEntity> implements FieldConfigurationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String dataName = (String)params.get("dataName");
        String dataAttribute = (String)params.get("dataAttribute");
        String displayName = (String)params.get("displayName");
        String fieldType = (String)params.get("fieldType");
        String type = (String)params.get("type");
        String isEnable = (String)params.get("isEnable");
        String isKeyword = (String)params.get("isKeyword");
        String isQuery = (String)params.get("isQuery");
        String formType = (String)params.get("formType");
        String baseDatatype = (String)params.get("baseDatatype");
        String isListfield = (String)params.get("isListfield");
        String queryNum = (String)params.get("queryNum");
        String listNum = (String)params.get("listNum");
        String detailsNum = (String)params.get("detailsNum");
        String updateCode = (String)params.get("updateCode");
        String updateName = (String)params.get("updateName");
        String startUpdateTime = (String)params.get("startUpdateTime");
        String endUpdateTime = (String)params.get("endUpdateTime");
        IPage<FieldConfigurationEntity> page = this.page(
                new Query<FieldConfigurationEntity>().getPage(params),
                new QueryWrapper<FieldConfigurationEntity>()
                        .like(StringUtils.isNotBlank(dataName),"data_name", dataName)
                        .like(StringUtils.isNotBlank(dataAttribute),"data_attribute", dataAttribute)
                        .like(StringUtils.isNotBlank(displayName),"display_name", displayName)
                        .eq(StringUtils.isNotBlank(fieldType),"field_type", fieldType)
                        .like(StringUtils.isNotBlank(type),"type", type)
                        .eq(StringUtils.isNotBlank(isEnable),"is_enable", isEnable)
                        .eq(StringUtils.isNotBlank(isKeyword),"is_keyword", isKeyword)
                        .eq(StringUtils.isNotBlank(isQuery),"is_query", isQuery)
                        .eq(StringUtils.isNotBlank(formType),"form_type", formType)
                        .eq(StringUtils.isNotBlank(baseDatatype),"base_datatype", baseDatatype)
                        .eq(StringUtils.isNotBlank(isListfield),"is_listfield", isListfield)
                        .eq(StringUtils.isNotBlank(queryNum),"query_num", queryNum)
                        .eq(StringUtils.isNotBlank(listNum),"list_num", listNum)
                        .eq(StringUtils.isNotBlank(detailsNum),"details_num", detailsNum)
                        .eq(StringUtils.isNotBlank(updateCode),"update_code", updateCode)
                        .like(StringUtils.isNotBlank(updateName),"update_name", updateName)
                        .ge(StringUtils.isNotBlank(startUpdateTime),"update_time",startUpdateTime)
                        .le(StringUtils.isNotBlank(endUpdateTime),"update_time",endUpdateTime)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        return new PageUtils(page);
    }

    @Override
    public List<FieldConfigurationEntity> queryList(Map<String, Object> params) {
        String dataName = (String)params.get("dataName");
        String dataAttribute = (String)params.get("dataAttribute");
        String displayName = (String)params.get("displayName");
        String fieldType = (String)params.get("fieldType");
        String type = (String)params.get("type");
        String isEnable = (String)params.get("isEnable");
        String isKeyword = (String)params.get("isKeyword");
        String isQuery = (String)params.get("isQuery");
        String formType = (String)params.get("formType");
        String baseDatatype = (String)params.get("baseDatatype");
        String isListfield = (String)params.get("isListfield");
        String queryNum = (String)params.get("queryNum");
        String listNum = (String)params.get("listNum");
        String detailsNum = (String)params.get("detailsNum");
        String updateCode = (String)params.get("updateCode");
        String updateName = (String)params.get("updateName");
        String startUpdateTime = (String)params.get("startUpdateTime");
        String endUpdateTime = (String)params.get("endUpdateTime");
        String sidx = (String)params.get("sidx");

        List<FieldConfigurationEntity> fieldConfigurationList = this.list(new QueryWrapper<FieldConfigurationEntity>()
                .like(StringUtils.isNotBlank(dataName),"data_name", dataName)
                .like(StringUtils.isNotBlank(dataAttribute),"data_attribute", dataAttribute)
                .like(StringUtils.isNotBlank(displayName),"display_name", displayName)
                .eq(StringUtils.isNotBlank(fieldType),"field_type", fieldType)
                .eq(StringUtils.isNotBlank(type),"type", type)
                .eq(StringUtils.isNotBlank(isEnable),"is_enable", isEnable)
                .eq(StringUtils.isNotBlank(isKeyword),"is_keyword", isKeyword)
                .eq(StringUtils.isNotBlank(isQuery),"is_query", isQuery)
                .eq(StringUtils.isNotBlank(formType),"form_type", formType)
                .eq(StringUtils.isNotBlank(baseDatatype),"base_datatype", baseDatatype)
                .eq(StringUtils.isNotBlank(isListfield),"is_listfield", isListfield)
                .eq(StringUtils.isNotBlank(queryNum),"query_num", queryNum)
                .eq(StringUtils.isNotBlank(listNum),"list_num", listNum)
                .eq(StringUtils.isNotBlank(detailsNum),"details_num", detailsNum)
                .eq(StringUtils.isNotBlank(updateCode),"update_code", updateCode)
                .like(StringUtils.isNotBlank(updateName),"update_name", updateName)
                .ge(StringUtils.isNotBlank(startUpdateTime),"update_time",startUpdateTime)
                .le(StringUtils.isNotBlank(endUpdateTime),"update_time",endUpdateTime)
                .orderByAsc(StringUtils.isNotBlank(sidx),sidx)
                .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER)));
        return fieldConfigurationList;
    }
}