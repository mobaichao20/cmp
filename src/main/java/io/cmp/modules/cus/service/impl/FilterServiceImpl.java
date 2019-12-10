package io.cmp.modules.cus.service.impl;

import io.cmp.common.utils.Constant;
import io.cmp.modules.cus.service.FilterFieldService;
import io.cmp.modules.sys.entity.SysRoleEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.cus.dao.FilterDao;
import io.cmp.modules.cus.entity.FilterEntity;
import io.cmp.modules.cus.service.FilterService;
import org.springframework.transaction.annotation.Transactional;


@Service("filteService")
public class FilterServiceImpl extends ServiceImpl<FilterDao, FilterEntity> implements FilterService {
    @Autowired
    private FilterFieldService filterFieldService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String filterName = (String)params.get("filterName");
        String isPublic = (String)params.get("isPublic");
        String createCode = (String)params.get("createCode");
        String createName = (String)params.get("createName");
        String startCreateTime = (String)params.get("startCreateTime");
        String endCreateTime = (String)params.get("endCreateTime");
        IPage<FilterEntity> page = this.page(
                new Query<FilterEntity>().getPage(params),
                new QueryWrapper<FilterEntity>()
                        .like(StringUtils.isNotBlank(filterName),"filter_name", filterName)
                        .eq(StringUtils.isNotBlank(isPublic),"is_public", isPublic)
                        .eq(StringUtils.isNotBlank(createCode),"create_code", createCode)
                        .like(StringUtils.isNotBlank(createName),"create_name", createName)
                        .ge(StringUtils.isNotBlank(startCreateTime),"create_time",startCreateTime)
                        .le(StringUtils.isNotBlank(endCreateTime),"create_time",endCreateTime)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFilter(FilterEntity filter) {

        filter.setCreateTime(new Date());
        this.save(filter);

        //保存过滤器与过滤器字段数据
        if(filter.getFilterFieldList()!=null) {
            if (filter.getFilterFieldList().size() != 0) {
                filterFieldService.saveOrUpdate(filter.getId(), filter.getFilterFieldList());
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFilter(FilterEntity filter) {

        this.updateById(filter);

        //保存过滤器与过滤器字段数据
        if(filter.getFilterFieldList()!=null) {
            if (filter.getFilterFieldList().size() != 0) {
                filterFieldService.saveOrUpdate(filter.getId(), filter.getFilterFieldList());
            }
        }


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(String[] filterIds) {
        //删除过滤器
        this.removeByIds(Arrays.asList(filterIds));

        //删除过滤器与过滤器字段
        filterFieldService.deleteBatch(filterIds);


    }



}