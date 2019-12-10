package io.cmp.modules.wot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.wot.dao.SysWorkOrderTPDao;
import io.cmp.modules.wot.entity.SysWorkOrderTPEntity;
import io.cmp.modules.wot.service.SysWorkOrderTPService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;


@Service("SysWorkOrderTPService")
public class SysWorkOrderTPServiceImpl  extends ServiceImpl<SysWorkOrderTPDao,SysWorkOrderTPEntity> implements SysWorkOrderTPService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String syswotpid = (String)params.get("syswotpId");
        String syswotpname = (String)params.get("syswotpName");

        String createname = (String)params.get("createName");
        String updatename = (String)params.get("updateName");
        String startTime = (String)params.get("create0");
        String endTime = (String)params.get("create1");
        String ustartTime = (String)params.get("update0");
        String uendTime = (String)params.get("update1");

        String remindtype = (String)params.get("remindType");
        String wotype = (String)params.get("woType");
        String remindNum = (String)params.get("remindNum");
        IPage<SysWorkOrderTPEntity> page = this.page(
                new Query<SysWorkOrderTPEntity>().getPage(params),
                new QueryWrapper<SysWorkOrderTPEntity>()
                        .like(StringUtils.isNotBlank(syswotpname),"syswotp_name", syswotpname)
                        .like(StringUtils.isNotBlank(createname),"create_name", createname)
                        .like(StringUtils.isNotBlank(updatename),"update_name", updatename)
                        .eq(StringUtils.isNotBlank(syswotpid),"syswotp_id", syswotpid)
                        .ge(StringUtils.isNotBlank(startTime),"create_time",startTime)
                        .le(StringUtils.isNotBlank(endTime),"create_time",endTime)
                        .ge(StringUtils.isNotBlank(ustartTime),"update_time",ustartTime)
                        .le(StringUtils.isNotBlank(uendTime),"update_time",uendTime)
                        .eq(StringUtils.isNotBlank(remindtype),"remind_type", remindtype)
                        .eq(StringUtils.isNotBlank(wotype),"wo_type", wotype)
                        .eq(StringUtils.isNotBlank(remindNum),"remind_num", remindNum)

        );
        return new PageUtils(page);
    }

    @Override
        public void deleteBatch(String[] needDelwotps) {
        this.removeByIds(Arrays.asList(needDelwotps));
    }

}
