package io.cmp.modules.mail.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.cmp.common.utils.SpringContextUtils;
import io.cmp.modules.mail.entity.CrmEmailAccountEntity;
import io.cmp.modules.mail.entity.CrmEmailSendEntity;
import io.cmp.modules.mail.service.CrmEmailAccountService;
import io.cmp.modules.mail.service.CrmEmailSendService;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 用于定时任务发送邮件的逻辑
 */
public class SendMail {

    public void f() {
        //获取spring bean
        CrmEmailAccountService crmEmailAccountService = (CrmEmailAccountService) SpringContextUtils.getBean("crmEmailAccountService");
        CrmEmailSendService crmEmailSendService = (CrmEmailSendService) SpringContextUtils.getBean("crmEmailSendService");
//     查询所有的发件
        List<CrmEmailSendEntity> list = crmEmailSendService.list();

        for (CrmEmailSendEntity c : list) {
            CrmEmailAccountEntity mailConfig = crmEmailAccountService.getOne(new QueryWrapper<CrmEmailAccountEntity>()
                    .eq(StringUtils.isNotBlank(c.getSender()), "mail_address", c.getSender()));
            if(c.getScheduleTime().getTime()>=new Date().getTime()){
                ScheduledService.param(mailConfig, c);

            }

        }

    }
}
