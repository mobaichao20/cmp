package io.cmp.modules.wot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.wot.dao.SysNodeLocusDao;
import io.cmp.modules.wot.entity.SysNodeLocusEntity;
import io.cmp.modules.wot.service.SysNodeLocusService;
import io.cmp.modules.wot.service.SysNodeService;
import io.cmp.modules.wot.service.SysWorkOrderTPService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service("SysNodeLocusService")
public class SysNodeLocusServiceImpl extends ServiceImpl<SysNodeLocusDao, SysNodeLocusEntity> implements SysNodeLocusService{

    @Autowired
    private SysNodeLocusService sysNodeLocusService;
    @Autowired
    private SysNodeService sysNodeService;
    @Autowired
    private SysWorkOrderTPService sysWorkOrderTPService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String syswotpid =(String)params.get("syswotpid");
        String startId=(String)params.get("startId");
        String endId = (String)params.get("endId");
        String isBranch = (String)params.get("isBranch");
        String createtime = (String)params.get("createTime");
        String updatetime = (String)params.get("updateTime");
        IPage<SysNodeLocusEntity> page = this.page(
                new Query<SysNodeLocusEntity>().getPage(params),
                new QueryWrapper<SysNodeLocusEntity>()
                        .eq(StringUtils.isNotBlank(syswotpid),"syswotpid", syswotpid)
                        .eq(StringUtils.isNotBlank(startId),"startid", startId)
                        .eq(StringUtils.isNotBlank(endId),"endid", endId)
                        .eq(StringUtils.isNotBlank(isBranch),"isbranch", isBranch)
                        .eq(StringUtils.isNotBlank(createtime),"createtime", createtime)
                        .eq(StringUtils.isNotBlank(updatetime),"updatetime", updatetime)

        );
        for (SysNodeLocusEntity sysNodeLocusEntity:page.getRecords()) {
            //获得节点名
            String startName = sysNodeService.getById(sysNodeLocusEntity.getStartId()).getNodeName();
            String nextName = sysNodeService.getById(sysNodeLocusEntity.getNextId()).getNodeName();
            //获得模板名
            String name =sysWorkOrderTPService.getById(sysNodeLocusEntity.getSyswotpId()).getSyswotpName();
            sysNodeLocusEntity.setStartName(startName);
            sysNodeLocusEntity.setNextName(nextName);
            sysNodeLocusEntity.setSyswotpName(name);
        }

        return new PageUtils(page);
    }

    @Override
    public void deleteBatch(String[] needDelwotps) {
        this.removeByIds(Arrays.asList(needDelwotps));
    }

}
