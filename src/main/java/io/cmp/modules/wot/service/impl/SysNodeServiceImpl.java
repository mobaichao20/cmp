package io.cmp.modules.wot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.wot.dao.SysNodeDao;
import io.cmp.modules.wot.entity.SysNodeEntity;
import io.cmp.modules.wot.service.SysNodeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service("SysNodeService")
public class SysNodeServiceImpl extends ServiceImpl<SysNodeDao,SysNodeEntity> implements SysNodeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String syswotpid = (String) params.get("syswotpId");
        String nodeid = (String)params.get("nodeId");
        String nodename = (String)params.get("nodeName");
        String isbranch = (String)params.get("isBranch");
        String isend = (String)params.get("isEnd");
        String updatename = (String)params.get("updateName");
        String updatetime = (String)params.get("updateTime");
        String createname = (String)params.get("createName");
        String createtime = (String)params.get("createTime");

        IPage<SysNodeEntity> page = this.page(
                new Query<SysNodeEntity>().getPage(params),
                new QueryWrapper<SysNodeEntity>()
                        .like(StringUtils.isNotBlank(nodename),"nodename", nodename)
                        .like(StringUtils.isNotBlank(createname),"createname", createname)
                        .like(StringUtils.isNotBlank(updatename),"updatename", updatename)
                        .eq(StringUtils.isNotBlank(syswotpid),"syswotpid", syswotpid)
                        .eq(StringUtils.isNotBlank(nodeid),"nodeid", nodeid)
                        .eq(StringUtils.isNotBlank(createtime),"createtime", createtime)
                        .eq(StringUtils.isNotBlank(isbranch),"isbranch", isbranch)
                        .eq(StringUtils.isNotBlank(isend),"isend", isend)
                        .eq(StringUtils.isNotBlank(updatetime),"updatetime", updatetime)


        );
        return new PageUtils(page);
    }

    @Override
    public void deleteBatch(String[] needDelwotps) {
        this.removeByIds(Arrays.asList(needDelwotps));
    }


}
