package io.cmp.modules.mma.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.mma.dao.CrmMediaTemplateFileDao;
import io.cmp.modules.mma.entity.CrmMediaTemplateFileEntity;
import io.cmp.modules.mma.service.CrmMediaTemplateFileService;


@Service("crmMediaTemplateFileService")
public class CrmMediaTemplateFileServiceImpl extends ServiceImpl<CrmMediaTemplateFileDao, CrmMediaTemplateFileEntity> implements CrmMediaTemplateFileService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CrmMediaTemplateFileEntity> page = this.page(
                new Query<CrmMediaTemplateFileEntity>().getPage(params),
                new QueryWrapper<CrmMediaTemplateFileEntity>()
        );

        return new PageUtils(page);
    }

}