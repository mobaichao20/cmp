package io.cmp.modules.mail.service.impl;

import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;
import io.cmp.modules.mail.service.CrmEmailAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.mail.dao.CrmEmailModuleDao;
import io.cmp.modules.mail.entity.CrmEmailModuleEntity;
import io.cmp.modules.mail.service.CrmEmailModuleService;


@Service("crmEmailModuleService")
public class CrmEmailModuleServiceImpl extends ServiceImpl<CrmEmailModuleDao, CrmEmailModuleEntity> implements CrmEmailModuleService {
    @Autowired
    private CrmEmailAttachmentService crmEmailAttachmentService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CrmEmailModuleEntity> page = this.page(
                new Query<CrmEmailModuleEntity>().getPage(params),
                new QueryWrapper<CrmEmailModuleEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveTemplate(CrmEmailModuleEntity crmEmailModule, String email) {
//        保存模板
        this.save(crmEmailModule);
//        保存附件
      List<CrmEmailAttachmentEntity> list=  crmEmailAttachmentService.list(new QueryWrapper<CrmEmailAttachmentEntity>().eq(("mr_creater"),email));
        for (CrmEmailAttachmentEntity crmEmailAttachmentEntity:list) {
            crmEmailAttachmentEntity.setMrCreater(crmEmailModule.getId());
            crmEmailAttachmentEntity.setModuleId(crmEmailModule.getId());

            crmEmailAttachmentService.updateById(crmEmailAttachmentEntity);

        }

    }

}