package io.cmp.modules.mail.service.impl;

import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;
import io.cmp.modules.mail.service.CrmEmailAttachmentService;
import io.cmp.modules.mail.utils.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.mail.dao.CrmEmailAccountDao;
import io.cmp.modules.mail.entity.CrmEmailAccountEntity;
import io.cmp.modules.mail.service.CrmEmailAccountService;
import org.springframework.transaction.annotation.Transactional;


@Service("crmEmailAccountService")
public class CrmEmailAccountServiceImpl extends ServiceImpl<CrmEmailAccountDao, CrmEmailAccountEntity> implements CrmEmailAccountService {
    @Autowired
    private CrmEmailAttachmentService crmEmailAttachmentService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CrmEmailAccountEntity> page = this.page(
                new Query<CrmEmailAccountEntity>().getPage(params),
                new QueryWrapper<CrmEmailAccountEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(String[] ids) {
        for (String id:ids) {
            this.removeById(id);
        }
        return true;
    }

    @Override
    public boolean testsss() {
       // this.baseMapper=getBaseMapper();
        System.out.println("------baseMapper---------"+getBaseMapper());
        return true;
    }

    @Override
    public void mailRelay(String id) {
        String email = new ScheduledService().getuser().getEmail();
        List<CrmEmailAttachmentEntity> list= crmEmailAttachmentService.list(new QueryWrapper<CrmEmailAttachmentEntity>().eq("mr_creater", id));
//        先保存到数据库中
        for (CrmEmailAttachmentEntity crmEmailAttachmentEntity:list) {
            crmEmailAttachmentEntity.setId(null);
            crmEmailAttachmentEntity.setMrCreater(email);
            crmEmailAttachmentEntity.setCreateTime(new Date());
            crmEmailAttachmentService.save(crmEmailAttachmentEntity);


        }

    }

}