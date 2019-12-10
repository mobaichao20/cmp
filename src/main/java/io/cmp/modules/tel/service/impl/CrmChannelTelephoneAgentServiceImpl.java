package io.cmp.modules.tel.service.impl;

import io.cmp.common.utils.Constant;
import io.cmp.modules.tel.vo.CrmChannelTelephoneAgentVo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.tel.dao.CrmChannelTelephoneAgentDao;
import io.cmp.modules.tel.entity.CrmChannelTelephoneAgentEntity;
import io.cmp.modules.tel.service.CrmChannelTelephoneAgentService;
import org.springframework.web.bind.annotation.RequestParam;


@Service("crmChannelTelephoneAgentService")
public class CrmChannelTelephoneAgentServiceImpl extends ServiceImpl<CrmChannelTelephoneAgentDao, CrmChannelTelephoneAgentEntity> implements CrmChannelTelephoneAgentService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String channelId = (String)params.get("channelId");
        String telephoneAgent = (String)params.get("telephoneAgent");
        String skillsGroup = (String)params.get("skillsGroup");
        String processingTime = (String)params.get("processingTime");
        String ringingCount = (String)params.get("ringingCount");
        String agentNumber = (String)params.get("agentNumber");
        String agentName = (String)params.get("agentName");
        String isAnswer = (String)params.get("isAnswer");
        String isHangupSatisfaction = (String)params.get("isHangupSatisfaction");
        String isBroadcasterAgent = (String)params.get("isBroadcasterAgent");
        String createCode = (String)params.get("createCode");
        String createName = (String)params.get("createName");
        String startCreateTime = (String)params.get("startCreateTime");
        String endCreateTime = (String)params.get("endCreateTime");
        String updateCode = (String)params.get("updateCode");
        String updateName = (String)params.get("updateName");
        String startUpdateTime = (String)params.get("startUpdateTime");
        String endUpdateTime = (String)params.get("endUpdateTime");
        IPage<CrmChannelTelephoneAgentEntity> page = this.page(
                new Query<CrmChannelTelephoneAgentEntity>().getPage(params),
                new QueryWrapper<CrmChannelTelephoneAgentEntity>()
                        .eq(StringUtils.isNotBlank(channelId),"channel_id", channelId)
                        .eq(StringUtils.isNotBlank(telephoneAgent),"telephone_agent", telephoneAgent)
                        .eq(StringUtils.isNotBlank(skillsGroup),"skills_group", skillsGroup)
                        .eq(StringUtils.isNotBlank(processingTime),"processing_time", processingTime)
                        .eq(StringUtils.isNotBlank(ringingCount),"ringing_count", ringingCount)
                        .eq(StringUtils.isNotBlank(agentNumber),"agent_number", agentNumber)
                        .like(StringUtils.isNotBlank(agentName),"agent_name", agentName)
                        .eq(StringUtils.isNotBlank(isAnswer),"is_answer", isAnswer)
                        .eq(StringUtils.isNotBlank(isHangupSatisfaction),"is_hangup_satisfaction", isHangupSatisfaction)
                        .eq(StringUtils.isNotBlank(isBroadcasterAgent),"is_broadcaster_agent", isBroadcasterAgent)
                        .eq(StringUtils.isNotBlank(createCode),"create_code", createCode)
                        .like(StringUtils.isNotBlank(createName),"create_name", createName)
                        .ge(StringUtils.isNotBlank(startCreateTime),"create_time",startCreateTime)
                        .le(StringUtils.isNotBlank(endCreateTime),"create_time",endCreateTime)
                        .eq(StringUtils.isNotBlank(updateCode),"update_code", updateCode)
                        .like(StringUtils.isNotBlank(updateName),"update_name", updateName)
                        .ge(StringUtils.isNotBlank(startUpdateTime),"update_time",startUpdateTime)
                        .le(StringUtils.isNotBlank(endUpdateTime),"update_time",endUpdateTime)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        return new PageUtils(page);
    }

    @Override
    public CrmChannelTelephoneAgentVo queryTelephoneAgentInfoByExtension(Map<String, Object> params) {
        String extension =(String)params.get("extension");
        String agentNumber =(String)params.get("agentNumber");
        logger.info("extension="+extension);
        logger.info("agentNumber="+agentNumber);
        return 	baseMapper.queryTelephoneAgentInfoByExtension(extension,agentNumber);
    }


    @Override
    public CrmChannelTelephoneAgentEntity queryrRandomTelephoneAgentInfo() {
        return baseMapper.queryrRandomTelephoneAgentInfo();
    }
}