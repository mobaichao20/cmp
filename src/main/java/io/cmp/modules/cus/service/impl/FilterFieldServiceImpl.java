package io.cmp.modules.cus.service.impl;

import io.cmp.common.utils.Constant;
import io.cmp.modules.sys.entity.SysRoleEntity;
import io.cmp.modules.sys.entity.SysRoleMenuEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.cus.dao.FilterFieldDao;
import io.cmp.modules.cus.entity.FilterFieldEntity;
import io.cmp.modules.cus.service.FilterFieldService;
import org.springframework.transaction.annotation.Transactional;


@Service("filterFieldService")
public class FilterFieldServiceImpl extends ServiceImpl<FilterFieldDao, FilterFieldEntity> implements FilterFieldService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String filterId = (String)params.get("filterId");
        String dataName = (String)params.get("dataName");
        String dataAttribute = (String)params.get("dataAttribute");
        String displayName = (String)params.get("displayName");
        String type = (String)params.get("type");
        String dataValue = (String)params.get("dataValue");


        IPage<FilterFieldEntity> page = this.page(
                new Query<FilterFieldEntity>().getPage(params),
                new QueryWrapper<FilterFieldEntity>()
                        .eq(StringUtils.isNotBlank(filterId),"filter_id", filterId)
                        .like(StringUtils.isNotBlank(dataName),"data_name", dataName)
                        .like(StringUtils.isNotBlank(dataAttribute),"data_attribute", dataAttribute)
                        .like(StringUtils.isNotBlank(displayName),"display_name", displayName)
                        .like(StringUtils.isNotBlank(type),"type", type)
                        .like(StringUtils.isNotBlank(dataValue),"data_value", dataValue)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        return new PageUtils(page);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(String filterId, List<FilterFieldEntity> filterFieldList) {
        //先删除过滤器与过滤器字段数据
        deleteBatch(new String[]{filterId});

        if(filterFieldList.size() == 0){
            return ;
        }

        //保存过滤器与过滤器字段数据
        for(FilterFieldEntity filterFieldEntity : filterFieldList){
            filterFieldEntity.setFilterId(filterId);
            this.save(filterFieldEntity);
            }
    }

    @Override
    public List<FilterFieldEntity> queryFilterFieldList(String filterId) {
        return baseMapper.queryFilterFieldList(filterId);
    }

    @Override
    public int deleteBatch(String[] filterIds){
        return baseMapper.deleteBatch(filterIds);
    }

}