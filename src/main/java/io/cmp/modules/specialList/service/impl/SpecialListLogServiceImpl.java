package io.cmp.modules.specialList.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.mail.validator.StringUtil;
import io.cmp.modules.specialList.dao.SpecialListLogDao;
import io.cmp.modules.specialList.entity.SpecialListEntity;
import io.cmp.modules.specialList.entity.SpecialListLogEntity;
import io.cmp.modules.specialList.service.SpecialListLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 特殊名单日志
 */
@Service("specialListLogService")
public class SpecialListLogServiceImpl extends ServiceImpl<SpecialListLogDao,SpecialListLogEntity> implements SpecialListLogService{

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String username =  params.get("username").toString();
        String pickerStart =  params.get("pickerStart").toString();
        String pickerEnd =  params.get("pickerEnd").toString();

        IPage<SpecialListLogEntity> page = this.page(
                new Query<SpecialListLogEntity>().getPage(params),
                new QueryWrapper<SpecialListLogEntity>()
                .like(StringUtils.isNotBlank(username),"username",username)
                .between(StringUtils.isNotBlank(pickerStart),"create_time",pickerEnd,pickerEnd)
        );
        return new PageUtils(page);
    }
}
