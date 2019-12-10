package io.cmp.modules.mail.handle;

import io.cmp.modules.mail.dao.CrmEmailReceiverDao;
import io.cmp.modules.mail.entity.CrmEmailAccountEntity;
import io.cmp.modules.mail.entity.CrmEmailSendEntity;
import io.cmp.modules.mail.service.CrmEmailReceiverService;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class MailDefaultHandlerImpl extends MailUtil {

    /**
     * 发送单封邮件
     */
    public List<CrmEmailSendEntity> sendMail(CrmEmailAccountEntity mailConfig, CrmEmailSendEntity mailBodys) throws Exception {
        return send(mailConfig,mailBodys);
    }

    /**
     * 发送多封邮件
     */
    public List<CrmEmailSendEntity> sendMail(CrmEmailAccountEntity mailConfig, List<CrmEmailSendEntity> mailBodyList) throws Exception {
        return send(mailConfig,mailBodyList);
    }

    /**
     * 测试邮箱配置可用性
     */
    public String testSend(CrmEmailAccountEntity mailConfig) throws Exception {
        if("0".equals(receive(mailConfig))){
            return send(mailConfig);
        }else{
            return "1";
        }
    }

    /**
     * 接收邮件
     */
    public List recvMail(CrmEmailAccountEntity mailConfigs, StringBuilder basePath, CrmEmailReceiverService crmEmailReceiverDao) throws Exception {
        ReceiveEmailHandlerImpl popMails = new ReceiveEmailHandlerImpl(basePath);
        List mailList = popMails.receiveMails(1, mailConfigs,crmEmailReceiverDao);
        return mailList;
    }

    /**
     * @Title: deleteMessage
     * @Description: 解析邮件
     * @param messages
     *            要解析的邮件列表
     * @throws
     * @throws
     *      、、
     */
    public static void deleteMessage(Message... messages)
            throws MessagingException, IOException {
        if (messages == null || messages.length < 1) {
            throw new MessagingException("未找到要解析的邮件!");
        }
        // 解析所有邮件
        for (int i = 0, count = messages.length; i < count; i++) {

            // 邮件删除
            Message message = messages[i];
            String subject = message.getSubject();
            // set the DELETE flag to true
            message.setFlag(Flags.Flag.DELETED, true);
            System.out.println("Marked DELETE for message: " + subject);

        }
    }

}
