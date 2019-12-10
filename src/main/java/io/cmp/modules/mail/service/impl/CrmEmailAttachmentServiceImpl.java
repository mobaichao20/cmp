package io.cmp.modules.mail.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.mail.dao.CrmEmailAttachmentDao;
import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;
import io.cmp.modules.mail.service.CrmEmailAttachmentService;


@Service("crmEmailAttachmentService")
public class CrmEmailAttachmentServiceImpl extends ServiceImpl<CrmEmailAttachmentDao, CrmEmailAttachmentEntity> implements CrmEmailAttachmentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CrmEmailAttachmentEntity> page = this.page(
                new Query<CrmEmailAttachmentEntity>().getPage(params),
                new QueryWrapper<CrmEmailAttachmentEntity>()
        );

        return new PageUtils(page);
    }

}