package io.cmp.modules.mar.service;

import io.cmp.common.utils.PageUtils;
import io.cmp.modules.mar.entity.MarketingTaskEntity;
import io.cmp.modules.mar.entity.MarketingTaskLogEntity;
import io.cmp.modules.mar.vo.MarketingTaskQueryVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarketingTaskServiceTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MarketingTaskService marketingTaskService;

    //@Autowired
    //private OutriggerMessageService outriggerMessageService;

    @Test
    public void saveNotUsedOutriggerMsg(){
        marketingTaskService.saveNotUsedOutriggerMsg();
    }

    @Test
    public void endService(){
        MarketingTaskEntity marketing = new MarketingTaskEntity();
        marketing.setMarketingId("3880e47738f70a1a0c85d945e988137a");
        marketing.setCustomerName("test2");
        marketing.setCustomerCredentialType(1);
        marketing.setCustomerCredentialNo("433130199*********");
        marketing.setOutriggerId("4b912d5fa34e7e311ff4b9bbbf5fa7cc");
        marketing.setTaskStatus(3);
        marketing.setCampaignId("default");

        MarketingTaskLogEntity marketingLog = new MarketingTaskLogEntity();
        marketingLog.setRecordingsId("testRecordings");

        Boolean reVal = marketingTaskService.endService(marketing,marketingLog);
        logger.info("营销数据修改成功："+reVal.toString());
    }

    @Test
    public void queryById(){
        String marketingId = "3880e47738f70a1a0c85d945e988137a";
        MarketingTaskQueryVo mv = marketingTaskService.queryById(marketingId);
        logger.info("营销数据："+mv);
        logger.info("外部数据："+mv.getOutriggerMessageEntity());
    }

    @Test
    public void queryPage(){
        Map<String, Object> params = new HashMap();
        params.put("campaignId","default");
        params.put("customerName","test");
        //params.put("","");//营销类型
        params.put("customerCredentialNo","433130************");
        params.put("taskType",1);
        PageUtils page = marketingTaskService.queryPage(params);
        List<MarketingTaskEntity> meList = (List<MarketingTaskEntity>)page.getList();
        logger.info("当前营销数量为" + meList.size());
    }
}
