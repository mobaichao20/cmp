package io.cmp.modules.mma.service.impl;

import io.cmp.common.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.mma.dao.CrmMediaTemplateDao;
import io.cmp.modules.mma.entity.CrmMediaTemplateEntity;
import io.cmp.modules.mma.service.CrmMediaTemplateService;
import org.springframework.transaction.annotation.Transactional;


@Service("crmMediaTemplateService")
public class CrmMediaTemplateServiceImpl extends ServiceImpl<CrmMediaTemplateDao, CrmMediaTemplateEntity> implements CrmMediaTemplateService {
    @Autowired
 private  CrmMediaTemplateService crmMediaTemplateService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String templateName = (String)params.get("templateName");
        String templateType = (String)params.get("templateType");
        System.out.println(templateName);
        System.out.println(templateType);

        IPage<CrmMediaTemplateEntity> page = this.page(
                new Query<CrmMediaTemplateEntity>().getPage(params),
                new QueryWrapper<CrmMediaTemplateEntity>()
                		.like(StringUtils.isNotBlank(templateName),"template_name", templateName)
                       .like(StringUtils.isNotBlank(templateType),"template_type", templateType)
					.apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public  boolean  deleteById(String id) {


        System.out.println("进来了！---------------------------------");
        boolean flag = crmMediaTemplateService.removeById(id);
        System.out.println("出去了！---------------------------------");
        System.out.println("这里是flag的值："+flag);
        return flag;
    }

    @Override
    public List findMediaTemplateById(List mediaTemplateList) {
        return baseMapper.selectBatchIds(mediaTemplateList);
    }

}