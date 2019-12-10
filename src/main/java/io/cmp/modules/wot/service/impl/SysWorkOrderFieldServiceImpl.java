package io.cmp.modules.wot.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.wot.dao.SysWorkOrderFieldDao;
import io.cmp.modules.wot.entity.SysWorkOrderFieldEntity;
import io.cmp.modules.wot.service.SysWorkOrderFieldService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("SysWorkOrderFieldService")
public class SysWorkOrderFieldServiceImpl extends ServiceImpl<SysWorkOrderFieldDao, SysWorkOrderFieldEntity> implements SysWorkOrderFieldService {

    private SysWorkOrderFieldService sysWorkOrderFieldService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String sysfieldId =(String)params.get("sysfieldId");
        String syswotpId = (String)params.get("syswotpId");
        String fieldName= (String)params.get("fieldName");
        String isMust= (String)params.get("isMust");
        String fieldType= (String)params.get("fieldType");
        String testType= (String)params.get("testType");
        String isSource = (String)params.get("isSource");
        String createName= (String)params.get("createName");
        String updateName= (String)params.get("updateName");
        String create0 = (String)params.get("create0");
        String create1 = (String)params.get("create1");
        String update0 = (String)params.get("update0");
        String update1 = (String)params.get("update1");

        IPage<SysWorkOrderFieldEntity> page = this.page(
                new Query<SysWorkOrderFieldEntity>().getPage(params),
                new QueryWrapper<SysWorkOrderFieldEntity>()
                    .like(StringUtils.isNotBlank(syswotpId),"syswotpid", syswotpId)
                    .like(StringUtils.isNotBlank(fieldName),"fieldname", fieldName)
                    .like(StringUtils.isNotBlank(createName),"createname", createName)
                    .like(StringUtils.isNotBlank(updateName),"updatename", updateName)
                    .eq(StringUtils.isNotBlank(sysfieldId),"sysfieldid", sysfieldId)
                    .eq(StringUtils.isNotBlank(isMust),"ismust", isMust)
                    .eq(StringUtils.isNotBlank(fieldType),"fieldtype", fieldType)
                    .eq(StringUtils.isNotBlank(testType),"testtype", testType)
                    .eq(StringUtils.isNotBlank(isSource),"issource", isSource)
                    .eq(StringUtils.isNotBlank(fieldType),"fieldtype", fieldType)
                    .ge(StringUtils.isNotBlank(create0),"create_time",create0)
                    .le(StringUtils.isNotBlank(create1),"create_time",create1)
                    .ge(StringUtils.isNotBlank(update0),"update_time",update0)
                    .le(StringUtils.isNotBlank(update1),"update_time",update1)
        );
        return new PageUtils(page);
    }



}
