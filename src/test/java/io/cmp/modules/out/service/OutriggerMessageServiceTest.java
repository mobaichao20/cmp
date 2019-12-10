package io.cmp.modules.out.service;

import io.cmp.modules.out.entity.OutriggerMessageEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OutriggerMessageServiceTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OutriggerMessageService outriggerMessageService;

    @Test
    public void saveOutriggerMsg(){
        OutriggerMessageEntity outriggerMessage = new OutriggerMessageEntity();
        outriggerMessage.setCampaignId("default");
        outriggerMessage.setCustomerName("测试");
        outriggerMessage.setCustomerEmail("test.163.com");
        outriggerMessage.setCustomerTelephone("13817264907");
        Boolean reVal = outriggerMessageService.saveOutriggerMsg(outriggerMessage);
        logger.info("外部数据新增成功："+reVal.toString());
    }

    @Test
    public void queryNotUsed(){
        List<OutriggerMessageEntity> needEntitys = outriggerMessageService.queryNotUsed();
        logger.info("需要新增到库里的外部数据数量为"+needEntitys.size());
        for (OutriggerMessageEntity ome : needEntitys) {
            logger.info("需要新增到库里的外部数据ID为："+ome.getOutriggerId());
        }
    }
}
