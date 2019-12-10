package io.cmp.modules.mail.utils;
import io.cmp.modules.mail.dao.CrmEmailReceiverDao;
import io.cmp.modules.mail.entity.CrmEmailAccountEntity;
import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;
import io.cmp.modules.mail.entity.CrmEmailSendEntity;
import io.cmp.modules.mail.handle.MailUtil;
import io.cmp.modules.mail.service.CrmEmailAccountService;
import io.cmp.modules.mail.service.CrmEmailAttachmentService;
import io.cmp.modules.sys.controller.AbstractController;
import io.cmp.modules.sys.entity.SysUserEntity;
import lombok.extern.slf4j.Slf4j;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
public class ScheduledService extends AbstractController {

    /**
     * 入参的配置  发送邮件
     * @throws
     * @throws
     */
    public static void param(CrmEmailAccountEntity mailConfig, CrmEmailSendEntity mailBody)  {

        //  log.debug("-----------Service------------------"+crmEmailAccountService);
        //CrmEmailAccountEntity mailConfig= crmEmailAccountService.findByEmail(email);
     /* CrmEmailAccountEntity mailConfig= crmEmailAccountService.getOne(new QueryWrapper<CrmEmailAccountEntity>()
                .eq(StringUtils.isNotBlank(email), "mail_address", email));*/


        log.debug("-----------mailConfig------------------"+mailConfig);
        // CrmEmailSendEntity mailBody = new CrmEmailSendEntity();
/*        CrmEmailAccountEntity mailConfig = new CrmEmailAccountEntity();

        mailConfig.setMailAddress("caowenlong@sinosoft.com.cn");
        mailConfig.setPassword("Cwl19990228");
        mailConfig.setReceiveMailServer("imap.exmail.qq.com");
        mailConfig.setReceiveServerPort("993");
        mailConfig.setSendMailServer("smtp.exmail.qq.com");
        mailConfig.setSendServerPort("465");*/

//        设置多收件人
        mailBody.setReceiver("cwl231561@163.com;caowenlong@sinosoft.com.cn");
//        设置密送人
        mailBody.setSecuritySend("2318009561@qq.com;");
//        设置是否使用加密传输
        mailConfig.setIsSecretySend("2");
        mailBody.setMailSubject("你好");
        mailBody.setMailContent("测试3秒发送一次");
        mailBody.setMailCopy("caowenlong@sinosoft.com.cn");
        CrmEmailAttachmentEntity c=new CrmEmailAttachmentEntity();
        c.setCreateTime(new Date());
        c.setName("zzpic19237_s.jpg");
        c.setUrl("C:\\Users\\八戒\\Pictures\\Saved Pictures\\zzpic19237_s.jpg");
        List<CrmEmailAttachmentEntity> list=new ArrayList();
        list.add(c);
        mailBody.setMailResource(list);

        //  mailBody.setSendAddress("");
//        测试发送邮件
        try {
            new MailUtil().send(mailConfig, mailBody);
        } catch (NoSuchProviderException e) {
            log.debug("-----------发送失败----------");
            e.printStackTrace();
        } catch (MessagingException e) {
            log.debug("-----------发送失败----------");
            e.printStackTrace();
        } catch (java.security.NoSuchProviderException e) {
            e.printStackTrace();
        }
        log.debug("-----------发送成功----------");

    }
    public SysUserEntity getuser(){
        return getUser();
    }


    /**
     * 入参的配置  接收服务器邮件
     * @throws NoSuchProviderException
     * @throws MessagingException
     */
    public static void receive(CrmEmailReceiverDao crmEmailReceiverDao, CrmEmailAttachmentService crmEmailAttachmentService, CrmEmailAccountService crmEmailAccountService) {
/*        CrmEmailAccountEntity mailConfig = new CrmEmailAccountEntity();
        CrmEmailSendEntity mailBody = new CrmEmailSendEntity();
        mailConfig.setMailAddress("caowenlong@sinosoft.com.cn");
        mailConfig.setPassword("Cwl19990228");
        mailConfig.setReceiveMailServer("imap.exmail.qq.com");
        mailConfig.setReceiveServerPort("993");
        mailConfig.setSendMailServer("smtp.exmail.qq.com");
        mailConfig.setSendServerPort("465");

//        设置多收件人
        mailBody.setReceiver("cwl231561@163.com;caowenlong@sinosoft.com.cn");
//        设置密送人
        mailBody.setSecuritySend("2318009561@qq.com;");
//        设置是否使用加密传输
        mailConfig.setIsSecretySend("2");
        mailBody.setMailSubject("你好");
        mailBody.setMailContent("测试3秒发送一次");
        mailBody.setMailCopy("caowenlong@sinosoft.com.cn");*/
        //        获取本用户的email

        //log.debug("---email-----"+CrmEmailAccountEntity);
//      邮箱的配置
        //        获取本用户的email
        ;
/*        String email = new ScheduledService().getuser().getEmail();
        log.debug("---email-----"+email);
//      邮箱的配置
        CrmEmailAccountEntity mailConfig= crmEmailAccountService.getOne(new QueryWrapper<CrmEmailAccountEntity>()
                .eq(StringUtils.isNotBlank(email), "mail_address", email));


        try {
            //List<CrmEmailReceiverEntity> list=new ReceiveEmailHandlerImpl(new StringBuilder("D:\\testEmail")).receiveMails(0,mailConfig,crmEmailReceiverDao);
            List<List<CrmEmailReceiverEntity>> list=new ReceiveEmailHandlerImpl(new StringBuilder("D:\\testEmail")).receiveMails(0,mailConfig,crmEmailReceiverDao);
            log.debug("-----list------"+list);
            for (List<CrmEmailReceiverEntity> list1 :list) {
                for (CrmEmailReceiverEntity crmEmailReceiverEntity:list1) {
                    crmEmailReceiverDao.insert(crmEmailReceiverEntity);
                    if(crmEmailReceiverEntity.getMailResource()!=null){
                        for (CrmEmailAttachmentEntity crmEmailAttachmentEntity:crmEmailReceiverEntity.getMailResource()) {
//                            设置URL地址
                            if(crmEmailAttachmentEntity.getUrl()!=null){
                                log.debug("-----getUrl------"+crmEmailAttachmentEntity.getUrl());
                                String res[]= crmEmailAttachmentEntity.getUrl().split(":");
                                crmEmailAttachmentEntity.setUrl(res[1]);
                            }
//                            设置文件的名称
                            if(crmEmailAttachmentEntity.getName()!=null){
                                crmEmailAttachmentEntity.setName(crmEmailAttachmentEntity.getName());
                            }
//                            设置文件所属于者
                            crmEmailAttachmentEntity.setMrCreater(crmEmailReceiverEntity.getId());
//                            设置创建时间
                            crmEmailAttachmentEntity.setCreateTime(new Date());
                            crmEmailAttachmentService.save(crmEmailAttachmentEntity);
                        }
                    }
                }



            }

        } catch (Exception e) {
            log.debug("-----------接受邮件失败----------");
            e.printStackTrace();
        }
        log.debug("-----------接受邮件成功----------");

    }*/
    }
}
