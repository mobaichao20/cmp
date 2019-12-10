package io.cmp.modules.job.task;

import io.cmp.modules.mma.service.CrmSmsSendRecordsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信定时发送扫描
 */
@Component("smsTask")
public class smsTask implements ITask  {
    @Autowired
    private CrmSmsSendRecordsService crmSmsSendRecordsService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(String params){
        Map<String, Object> param = new HashMap<>();
        crmSmsSendRecordsService.sendMessages(param);
    }
}
