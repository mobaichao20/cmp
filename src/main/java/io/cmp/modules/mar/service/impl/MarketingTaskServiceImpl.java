package io.cmp.modules.mar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.Constant;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.cam.entity.CampaignEntity;
import io.cmp.modules.cam.service.CampaignService;
import io.cmp.modules.mar.dao.MarketingTaskDao;
import io.cmp.modules.mar.entity.MarketingTaskEntity;
import io.cmp.modules.mar.entity.MarketingTaskLogEntity;
import io.cmp.modules.mar.service.MarketingTaskLogService;
import io.cmp.modules.mar.service.MarketingTaskService;
import io.cmp.modules.mar.vo.MarketingTaskQueryVo;
import io.cmp.modules.out.entity.OutriggerMessageEntity;
import io.cmp.modules.out.service.OutriggerMessageService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("marketingTaskService")
public class MarketingTaskServiceImpl extends ServiceImpl<MarketingTaskDao, MarketingTaskEntity> implements MarketingTaskService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MarketingTaskLogService marketingTaskLogService;

    @Autowired
    private OutriggerMessageService outriggerMessageService;

    @Autowired
    private CampaignService campaignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String campaignId = params.get("campaignId").toString();
        String customerName = params.get("customerName").toString();
        //String  = params.get("").toString();//营销类型
        String customerCredentialNo = params.get("customerCredentialNo").toString();
        String taskType = params.get("taskType").toString();
        IPage<MarketingTaskEntity> page = this.page(
            new Query<MarketingTaskEntity>().getPage(params),
            new QueryWrapper<MarketingTaskEntity>()
                    .eq(StringUtils.isNotBlank(campaignId),"campaign_id", campaignId)
                    .like(StringUtils.isNotBlank(customerName),"customer_name", customerName)
                    .like(StringUtils.isNotBlank(customerCredentialNo),"customer_credential_no",customerCredentialNo)
                    .eq(StringUtils.isNotBlank(customerCredentialNo), "task_status", taskType)
                    .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        return new PageUtils(page);
    }

    /**
     * 提交营销数据
     */
    @Override
    @Transactional
    public Boolean endService(MarketingTaskEntity marketing, MarketingTaskLogEntity marketingLog) {
        Date nowDate = new Date();
        marketing.setUpdateTime(nowDate);
        Boolean reVal = this.updateById(marketing);
        if (reVal) {
            //任务开始时间、任务结束时间  需确认怎么记录
            //联络时间、录音ID、联系电话 需要从前台记录传值，因为坐席在前台操作，后台只能被动接收
            marketingLog.setMarketingTaskId(marketing.getMarketingId());
            marketingLog.setMarketingTaskStatus(marketing.getTaskStatus());
            marketingLog.setBookingId(marketing.getBookingId());
            marketingLog.setBookingType(marketing.getBookingType());
            marketingLog.setBookingTime(marketing.getBookingTime());
            marketingLog.setRemark(marketing.getDescription());
            marketingLog.setEndCode(marketing.getEndCode());
            marketingLog.setUserCode(marketing.getUpdateCode());
            marketingLog.setUserName(marketing.getUpdateName());
            marketingLog.setCreateTime(nowDate);
            marketingTaskLogService.save(marketingLog);
        }
        return reVal;
    }

    /**
     * 根据ID查询营销数据
     * @param marketingId
     * @return
     */
    @Override
    public MarketingTaskQueryVo queryById(String marketingId) {
        MarketingTaskQueryVo marketingTaskQueryVo = baseMapper.queryById(marketingId);

        OutriggerMessageEntity outriggerMessage = outriggerMessageService.getById(marketingTaskQueryVo.getOutriggerId());
        if (null != outriggerMessage){
            marketingTaskQueryVo.setOutriggerMessageEntity(outriggerMessage);
            logger.info("查询到相关外部数据，营销数据ID为：" + marketingId);
        }else{
            logger.info("未查询到相关外部数据，营销数据ID为：" + marketingId);
        }
        return marketingTaskQueryVo;
    }

    /**
     * 将外联表为使用的数据新增到营销主表
     */
    @Override
    @Transactional
    public void saveNotUsedOutriggerMsg() {
        //先查出外联表有多少未使用的数据
        List<OutriggerMessageEntity> outList = outriggerMessageService.queryNotUsed();
        if (null != outList && outList.size() > 0) {
            int successCount = 0;
            int campaignFailedCount = 0;
            for (OutriggerMessageEntity out : outList) {
                CampaignEntity campaignEntity = campaignService.getById(out.getCampaignId());
                if (null == campaignEntity) {
                    campaignFailedCount ++;
                    continue;
                }
                MarketingTaskEntity marketing = new MarketingTaskEntity();
                marketing.setOutriggerId(out.getOutriggerId());
                marketing.setCustomerName(out.getCustomerName());
                marketing.setCustomerCredentialType(out.getCustomerCredentialType());
                marketing.setCustomerCredentialNo(out.getCustomerCredentialNo());
                marketing.setTaskStatus(1);//默认为未开始
                marketing.setCampaignId(out.getCampaignId());
                //新增数据到营销主表
                Boolean reVal = this.save(marketing);
                if (reVal) {
                    successCount ++;
                    out.setOutriggerStatus(2);//将数据设置为已使用
                    //讲已经新增入营销主表的外联数据状态置为已使用状态
                    outriggerMessageService.updateById(out);
                }
                StringBuffer msg = new StringBuffer();
                msg.append("营销数据新增成功条数为：").append(successCount);
                msg.append("，失败条数为：").append((outList.size()-successCount));
                msg.append("，活动不存在条数为：").append(campaignFailedCount);
                logger.info(msg.toString());
            }
        } else {
            logger.info("需要新增到库里的外部数据数量为 0 条");
        }
    }
}