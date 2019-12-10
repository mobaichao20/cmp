package io.cmp.modules.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.csr.dao.CrmCsrLogDao;
import io.cmp.modules.csr.entity.CrmCsrLog;
import io.cmp.modules.csr.service.CrmCsrLogService;
import io.cmp.modules.csr.vo.CrmCsrLogVO;
import io.cmp.modules.cus.service.CustomerBaseService;
import io.cmp.modules.sys.entity.SysDictEntity;
import io.cmp.modules.sys.service.SysDictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("CrmCsrLogService")
public class CrmCsrLogServiceImpl extends ServiceImpl<CrmCsrLogDao, CrmCsrLog> implements CrmCsrLogService {
    @Autowired
    private CrmCsrLogService crmCsrLogService;
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private CustomerBaseService customerBaseService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String customerName = (String)params.get("customerName");
        String userName = (String)params.get("userName");
        String serviceId = (String)params.get("serviceId");
        String channelId = (String)params.get("channelId");
        String csrLog = (String) params.get("csrLog");
        String startTime = (String)params.get("startTime");
        String endTime = (String)params.get("endTime");
        Boolean flagLog = false;
        if(csrLog !=null){
            flagLog = true;
        }
        IPage<CrmCsrLog> page = this.page(

                new Query<CrmCsrLog>().getPage(params),
                new QueryWrapper<CrmCsrLog>()
                        .like(StringUtils.isNotBlank(customerName),"customer_name", customerName)
                        .like(StringUtils.isNotBlank(userName),"user_name", userName)
                        .eq(StringUtils.isNotBlank(serviceId),"service_id", serviceId)
                        .eq(StringUtils.isNotBlank(channelId),"channel_id", channelId)
                        .eq(flagLog,"csr_log", csrLog)
                        .ge(StringUtils.isNotBlank(startTime),"create_time",startTime)
                        .le(StringUtils.isNotBlank(endTime),"create_time",endTime)

        );
        for (CrmCsrLog ccl: page.getRecords()) {
            int code = ccl.getCsrLog();
            SysDictEntity sysDictEntity = sysDictService.getOne(
                    new QueryWrapper()
                            .eq(Boolean.TRUE,"type", "satisfaction")
                            .eq(Boolean.TRUE,"code", code)
            );
            ccl.setLogNum(sysDictEntity.getValue());
        }
        return new PageUtils(page);
    }

    @Override
    public List<CrmCsrLogVO> getAll() {
        List<CrmCsrLogVO> crmCsrLogVOList = new ArrayList<CrmCsrLogVO>();
        List<CrmCsrLog> list = crmCsrLogService.list();
        for (CrmCsrLog ccl:list){
            CrmCsrLogVO vo = new CrmCsrLogVO();
            vo.setCsrLogId(ccl.getCsrLogId());
            vo.setCsrLog(ccl.getCsrLog());
            vo.setChannelId(ccl.getChannelId());
            vo.setChannel(sysDictService.getOne(
                    new QueryWrapper()
                            .eq(Boolean.TRUE,"type", "chanType")
                            .eq(Boolean.TRUE,"code", vo.getChannelId()) ).getValue());
            vo.setCreateTime(ccl.getCreateTime());
            vo.setCustomerId(ccl.getCustomerId());
            vo.setCustomerName(customerBaseService.getOne(
                    new QueryWrapper()
                            .eq(Boolean.TRUE,"id", vo.getCustomerId())).getCustomerName());
            vo.setLogNum(sysDictService.getOne(
                    new QueryWrapper()
                            .eq(Boolean.TRUE,"type", "satisfaction")
                            .eq(Boolean.TRUE,"code", vo.getCsrLog())).getValue());
            vo.setUserName(ccl.getUserName());
            vo.setServiceId(ccl.getServiceId());
            //会话暂定
            crmCsrLogVOList.add(vo);
        }

        return crmCsrLogVOList;
    }
}
