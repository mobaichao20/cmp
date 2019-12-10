package io.cmp.modules.mail.handle;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPMessage;
import com.sun.mail.imap.IMAPStore;
import com.sun.mail.pop3.POP3Folder;
import io.cmp.common.utils.SpringContextUtils;
import io.cmp.modules.job.service.ScheduleJobLogService;
import io.cmp.modules.mail.entity.CrmEmailAccountEntity;
import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;
import io.cmp.modules.mail.entity.CrmEmailReceiverEntity;
import io.cmp.modules.mail.entity.CrmEmailSendEntity;
import io.cmp.modules.mail.service.CrmEmailAttachmentService;
import io.cmp.modules.mail.service.CrmEmailReceiverService;
import io.cmp.modules.mail.service.impl.CrmEmailAttachmentServiceImpl;
import io.cmp.modules.mail.utils.FilesUpload;
import io.cmp.modules.mail.utils.ScheduledService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.AndTerm;
import javax.mail.search.FromStringTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchProviderException;
import java.util.*;

/**
 * 创建发送器
 */
@Slf4j
public class MailUtil {
    @Autowired
    private Session session; // 邮件会话对象

    private Properties props; // 系统属性


    /**
     * 发送单封邮件
     *
     * @param mailConfig 邮箱配置信息
     * @param mailBody   邮件主体
     * @return
     * @throws NoSuchProviderException
     */
    public synchronized List<CrmEmailSendEntity> send(CrmEmailAccountEntity mailConfig, CrmEmailSendEntity mailBody) throws NoSuchProviderException, MessagingException {
        List<CrmEmailSendEntity> mailBodyList = new ArrayList<CrmEmailSendEntity>();
        mailBodyList.add(mailBody);
        return send(mailConfig, mailBodyList);
    }


    /**
     * 获取邮件会话对象
     *
     * @param mailConfig 邮箱配置信息
     * @return
     */
    protected Session openMailSession(CrmEmailAccountEntity mailConfig) {
        // 获得系统属性对象
        props = System.getProperties();

        // 设置SMTP认证用户名
        props.put("mail.smtp.host", mailConfig.getSendMailServer());

        String smtpPort = "25";
        if (mailConfig.getSendServerPort() != null) {
            smtpPort = mailConfig.getSendServerPort();
        }
        // 设置SMTPP认证端口
        props.put("mail.smtp.port", smtpPort);

        //邮件发送服务器安全连接协议
        if ("2".equals(mailConfig.getIsSecretySend())) {
            log.debug("==== 开始构造邮箱参数：SSL协议");
            props.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接
            props.put("mail.smtp.socketFactory.port", smtpPort);//设置ssl端口
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        } else if ("3".equals(mailConfig.getIsSecretySend())) {
            log.debug("==== 开始构造邮箱参数：STARTTLS协议");
            props.put("mail.smtp.starttls.enable", "true");// 设置是否使用starttls安全连接
            props.put("mail.smtp.ssl.checkserveridentity", "false");
        } /*else {
            log.debug("==== 开始构造邮箱参数：STARTTLS协议");
            props.put("mail.smtp.starttls.enable", "true");// 设置是否使用starttls安全连接
            props.put("mail.smtp.ssl.checkserveridentity", "false");
        }*/




        //设置链接超时  60秒
        props.put("mail.smtp.timeout", "60000");

        // 设置SMTP认证用户名
        if (mailConfig != null) {
            props.put("mail.smtp.user", mailConfig.getMailAddress());
        }

        // 设置SMTP认证用户名的密码
        if (mailConfig.getPassword() != null) {

            props.put("mail.smtp.password", mailConfig.getPassword());


        }

        props.put("mail.smtp.auth", "true");

        final String username = props.get("mail.smtp.user").toString();
        final String password = props.get("mail.smtp.password").toString();
        props.setProperty("proxySet", "true");

        //return Session.getDefaultInstance(props, new Authenticator() {
        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    /**
     * 发送多封邮件
     *
     * @param mailConfig 邮箱配置信息
     * @param mailList   多封邮件
     * @throws NoSuchProviderException
     * @throws Exception
     */
    public synchronized List<CrmEmailSendEntity> send(CrmEmailAccountEntity mailConfig, List<CrmEmailSendEntity> mailList) throws NoSuchProviderException, MessagingException {
        log.debug("==== 开始构造邮箱参数");
        session = openMailSession(mailConfig);
        log.debug("==== 完成构造邮箱参数");

        session.setDebug(true);
        Transport transport = session.getTransport("smtp");
        List<CrmEmailSendEntity> sendMailedList = new ArrayList<CrmEmailSendEntity>();

        try {
            try {
                log.debug("==== 开始连接目标服务器：" + (String) props.get("mail.smtp.host"));
                log.debug("==== mail.smtp.user：" + (String) props.get("mail.smtp.user"));
                log.debug("==== mail.smtp.password：" + (String) props.get("mail.smtp.password"));
                log.debug("==== " + transport.isConnected());
                if (!transport.isConnected()) {
                    transport.connect((String) props.get("mail.smtp.host"), (String) props.get("mail.smtp.user"), (String) props.get("mail.smtp.password"));
                } else {
                    log.debug("已经连接目标服务器，无需再重新连接");
                }
            } catch (MessagingException me) {
                throw new MessagingException("无法连接目标服务器！");
            } catch (Exception e) {
                log.debug(e.getMessage());
                e.printStackTrace();
            }

            log.debug("==== 目标服务器连接成功，准备发送邮件");
            log.debug("==== 邮件主体封数：" + mailList.size());
            Iterator<CrmEmailSendEntity> mailBoydsIter = mailList.iterator();

            while (mailBoydsIter.hasNext()) {
                CrmEmailSendEntity mail = mailBoydsIter.next();
                MimeMessage mimeMsg = new MimeMessage(session);
                Multipart mp = new MimeMultipart();
                log.debug("======== 设置发信人信息");
                // 设置发信人
                mimeMsg.setFrom(new InternetAddress(mailConfig.getMailAddress()));
                // setReplyTo(mimeMsg, mail);

                log.debug("======== 设置邮件主题");
                mimeMsg.setSubject(MimeUtility.encodeText(mail.getMailSubject(), "utf-8", "b"));

                log.debug("======== 设置邮件内容");
                BodyPart bp = new MimeBodyPart();
                bp.setContent(mail.getMailContent(), "text/html;charset=gb2312");
                mp.addBodyPart(bp);
                mimeMsg.setContent(mp);
                log.debug("======== 设置邮件中的附件");
                // 添加所有附件

                String mail1=mailConfig.getMailAddress();
                log.debug("-------mail1----------"+mail1);
                CrmEmailAttachmentService crmEmailAttachmentService = (CrmEmailAttachmentService) SpringContextUtils.getBean("crmEmailAttachmentService");

                List<CrmEmailAttachmentEntity> list= crmEmailAttachmentService.list(new QueryWrapper<CrmEmailAttachmentEntity>().eq(StringUtils.isNotBlank(mail1),"mr_creater", mail1));
                log.debug("-------list11----------"+list);
//                存放上传附件的绝对路径
                List<CrmEmailAttachmentEntity> url=new ArrayList();
//              取出本人发送的邮件附件
                for (CrmEmailAttachmentEntity crmEmailAttachmentEntity:list) {
                    if(crmEmailAttachmentEntity.getMbMailType()!=null){
//                        只有是发件附件的时候才去发送
                        if(crmEmailAttachmentEntity.getMbMailType()==1){
                            url.add(crmEmailAttachmentEntity);
                        }

                    }

                }
                log.debug("-------url----------"+url);
                mail.setMailResource(url);

                if (mail.getMailType().equals("7")) {
                    log.debug("-------保存附件中-------");
                    String email = new ScheduledService().getuser().getEmail();

//                    通过Id查询出来邮件
                    CrmEmailReceiverService crmEmailReceiverService = (CrmEmailReceiverService) SpringContextUtils.getBean("crmEmailReceiverService");

                    CrmEmailReceiverEntity crmEmailReceiverEntity = crmEmailReceiverService.getById(mail.getId());

//                    然后根据Id查询附件
                    List<CrmEmailAttachmentEntity> list1 = crmEmailAttachmentService.list(new QueryWrapper<CrmEmailAttachmentEntity>().eq("mr_creater", email));

//                    把附件重新上传
                    for (CrmEmailAttachmentEntity crmEmailAttachmentEntity : list1) {

                                log.debug("-------设置转发的附件----------");
                                //if (mailRecource.getMrType() == 1) {
                                bp = new MimeBodyPart();

                                FileDataSource fileds = new FileDataSource(crmEmailAttachmentEntity.getAbsolutePath());
                                bp.setDataHandler(new DataHandler(fileds));
                                String fileName = crmEmailAttachmentEntity.getName();
                                bp.setFileName(MimeUtility.encodeText(fileName, "gbk", "B"));
                                mp.addBodyPart(bp);
                                mimeMsg.saveChanges();
                                // }

                        /*String path=crmEmailAttachmentEntity.getAbsolutePath();

                        FilesUpload.copy(crmEmailAttachmentEntity.getAbsolutePath(),"");*/

                    }


//                    在保存一份到附件表

                }else {
                    if (mail.getMailResource() != null) {
                        Iterator<CrmEmailAttachmentEntity> iter = mail.getMailResource().iterator();
                        while (iter.hasNext()) {
                            log.debug("-------设置附件sdgagasdf----------");
                            CrmEmailAttachmentEntity mailRecource = iter.next();
                            //if (mailRecource.getMrType() == 1) {
                            bp = new MimeBodyPart();

                            FileDataSource fileds = new FileDataSource(mailRecource.getAbsolutePath());
                            bp.setDataHandler(new DataHandler(fileds));
                            String fileName = mailRecource.getName();
                            bp.setFileName(MimeUtility.encodeText(fileName, "gbk", "B"));
                            //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                            //bp.setFileName("=?GBK?B?"+enc.encode(fileName.getBytes())+"?=");
                            mp.addBodyPart(bp);
                            mimeMsg.saveChanges();
                            // }
                        }
                    }
                }

                // log.debug("======== 设置收件人：" + mail.getMbToaddr());
                // 设置收件人
                setRecipients(mimeMsg, mail);
                //mimeMsg.set
                mimeMsg.setSentDate(new Date());
                mimeMsg.saveChanges();

                log.debug("======== 开始发送邮件到所有地址");
                // 发送邮件到所有地址
                transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
                log.debug("======== 发送邮件到所有地址-成功");

                log.debug("======== 开始将邮件放入集合中");
                sendMailedList.add(mail);
                log.debug("======== 将邮件放入集合中-成功:" + sendMailedList.size());

                log.debug("======== end");
                log.debug("");

               // Thread.sleep(100);
            }
        } catch (Exception e) {
            //sendMailedList.clear();
            log.debug(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (transport.isConnected()) {
                    transport.close();
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return sendMailedList;
    }

    /**
     * 邮件配置测试按钮
     *
     * @param mailConfig 邮箱配置信息
     * @return 0 : 测试成功   1：测试失败
     * @throws NoSuchProviderException
     * @throws Exception
     */
    public synchronized String send(CrmEmailAccountEntity mailConfig) throws NoSuchProviderException, MessagingException {
        String testReturn = "0";
        session = openMailSession(mailConfig);
        session.setDebug(true);
        Transport transport = session.getTransport("smtp");

        try {
            log.debug("-------------邮件发送测试-----------------");
            log.debug("开始连接目标服务器：" + (String) props.get("mail.smtp.host"));

            //测试连接邮件服务器是否成功
            transport.connect((String) props.get("mail.smtp.host"), (String) props.get("mail.smtp.user"), (String) props.get("mail.smtp.password"));

            MailUtil handler = new MailUtil();
            CrmEmailSendEntity mailBody = new CrmEmailSendEntity();
            mailBody.setMailContent("如果收到此封邮件，说明邮箱配置可用");
            mailBody.setSendAddress(mailConfig.getMailAddress());
            mailBody.setReceiver("cwl231561@163.com");
            mailBody.setMailSubject("测试邮件");

            try {
                //测试账户时，需要发送测试邮件，打开此方法即可
                List<CrmEmailSendEntity> list = handler.send(mailConfig, mailBody);
                if (list.size() == 0) {
                    testReturn = "1";
                }
            } catch (Exception e) {
                testReturn = "1";
                e.printStackTrace();
            }
        } catch (MessagingException me) {
            log.debug(me.getMessage());
        } finally {
            try {
                if (transport.isConnected())
                    transport.close();

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        return testReturn;
    }

    private void setReplyTo(MimeMessage mimeMsg, CrmEmailAccountEntity mail)
            throws MessagingException, MessagingException {

        String sendAddr = mail.getMailAddress();//发件人地址

        if (sendAddr != null) {
            Address[] replyTos = new InternetAddress[1];
            replyTos[0] = new InternetAddress(sendAddr);
            mimeMsg.setReplyTo(replyTos);
        }
    }

    /**
     * 设置收件人，包括to,cc,bcc
     *
     * @param mail 邮件
     * @throws AddressException
     * @throws MessagingException
     */
    private void setRecipients(MimeMessage mimeMsg, CrmEmailSendEntity mail)
            throws AddressException, MessagingException {

        setMailRecipients(mimeMsg, mail.getReceiver(), "to");//收件地址

        if (mail.getMailCopy() != null) {
            setMailRecipients(mimeMsg, mail.getMailCopy(), "cc");//抄送地址
        }

        if (mail.getSecuritySend() != null) {
            setMailRecipients(mimeMsg, mail.getSecuritySend(), "bcc");//密送地址
        }
    }

    /**
     * @param mimeMsg
     * @param mailAddressString
     * @param recipientType
     * @throws AddressException
     * @throws MessagingException
     */

    private void setMailRecipients(MimeMessage mimeMsg, String mailAddressString, String recipientType)
            throws AddressException, MessagingException {

        Message.RecipientType type = Message.RecipientType.TO;

        if (recipientType.equalsIgnoreCase("to")) {
            type = Message.RecipientType.TO;
        } else if (recipientType.equalsIgnoreCase("cc")) {
            type = Message.RecipientType.CC;
        } else if (recipientType.equalsIgnoreCase("bcc")) {
            type = Message.RecipientType.BCC;
        } else {
            type = Message.RecipientType.TO;
        }

        if (mailAddressString.endsWith(";")) {
            mailAddressString = mailAddressString.substring(0, mailAddressString.length() - 1);
        }

        if (mailAddressString != null && mailAddressString.indexOf(";") > 0) {
            String[] mailRecipientArr = mailAddressString.split(";");

            for (int i = 0; i < mailRecipientArr.length; i++) {
                String recipient = mailRecipientArr[i];
                mimeMsg.addRecipients(type, InternetAddress.parse(recipient));
            }
        } else {
            mimeMsg.addRecipients(type, InternetAddress.parse(mailAddressString));
        }
    }

    /**
     * 测试配置信息是否可用
     */

    public synchronized String receive(CrmEmailAccountEntity mailServerInfo) throws MessagingException {
        String testReturn = "0";
        String username = mailServerInfo.getMailAddress();
        if (mailServerInfo.getMcSmtpName() != null) {
            username = mailServerInfo.getMcSmtpName();
        }
        String password = mailServerInfo.getPassword();

        if (mailServerInfo.getMcSmtpPwd() != null) {
            password = mailServerInfo.getMcSmtpPwd();
        }
//        收件服务器地址
        String host = mailServerInfo.getReceiveMailServer();
        Store store = null;

        Session session = null;
        try {

            final String uName = username, pwd = password;
            Properties props = new Properties();
//         发件服务器地址
            props.put("mail.smtp.host", mailServerInfo.getSendMailServer());
            props.put("mail.smtp.auth", "true");

            session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(uName, pwd);
                }
            });
            session.setDebug(true);
            int port = 110;

            if (mailServerInfo.getReceiveServerPort() != null) {
                port = Integer.parseInt(mailServerInfo.getReceiveServerPort());
            }
            log.debug("---------port----邮件发送测试-----------------" + port);
            log.debug("---------host----邮件发送测试-----------------" + host);
            log.debug("---------username----邮件发送测试-----------------" + username);
            log.debug("---------password----邮件发送测试-----------------" + password);
            store = session.getStore("pop3");
            store.connect(host, username, password);         // 获取inbox文件

            store.close();
            log.debug("-------------目标服务器连接成功-----------------");

        } catch (Exception e) {
            testReturn = "1";
            throw new MessagingException("无法连接目标服务器！");
        }

        return testReturn;
    }


    /**
     * 接收邮件
     */

    public static synchronized String receiveMail(CrmEmailAccountEntity mailServerInfo) throws MessagingException {
        String testReturn = "0";
        String username = mailServerInfo.getMailAddress();
        if (mailServerInfo.getMcSmtpName() != null) {
            username = mailServerInfo.getMcSmtpName();
        }
        String password = mailServerInfo.getPassword();

        if (mailServerInfo.getMcSmtpPwd() != null) {
            password = mailServerInfo.getMcSmtpPwd();
        }
//        收件服务器地址
        String host = mailServerInfo.getReceiveMailServer();
        Store store = null;

        Session session = null;
        try {

            final String uName = username, pwd = password;
            Properties props = new Properties();
            // props.setProperty("mail.smtp.host", "smtp.sina.com");
            props.setProperty("mail.smtp.auth", "true");
//props.setProperty("mail.transport.protocol", "smtp");
            session = Session.getDefaultInstance(props, null);
            URLName urlname = new URLName("pop3", host, 110, null, uName, pwd);
//URLName urlname = new URLName("pop3","pop.exmail.qq.com",110,null,"xxxxx","xxxxx");
            store = session.getStore(urlname);
            store.connect();
            Folder folder = store.getFolder("INBOX");
// folder.open(Folder.READ_ONLY);
            folder.open(Folder.READ_WRITE);
            Message msgs[] = folder.getMessages();
            for (Message m : msgs) {
                int count = msgs.length;
                POP3Folder inbox = (POP3Folder) folder;
                //获取邮件的UID
                String uid = inbox.getUID(m);
                System.out.println("Message Count:" + count);
                if (uid.equals("ZC0609-7VJlP_snVXY6LgVY7tQlW9k")) {
                    m.setFlag(Flags.Flag.DELETED, true);
                    log.debug("----------------success-------------");
                    m.saveChanges();
                }
            }


            //msgs[count-1].saveChanges();
            //folder.expunge();
            folder.close(true);
            store.close();
        } catch (Exception e) {
            log.debug("" + e.getMessage());

        }
        return "1";
    }

    /**
     * 解析邮件
     *
     * @param messages 删除邮件
     */
    public static void deleteMessage(Folder folder, Message... messages) throws MessagingException, IOException {
        if (messages == null || messages.length < 1)
            throw new MessagingException("未找到要解析的邮件!");

        // 解析所有邮件
        for (int i = 0, count = messages.length; i < count; i++) {

            /**
             *   邮件删除
             */
            Message message = messages[i];
            POP3Folder inbox = (POP3Folder) folder;
            //获取邮件的UID
            String uid = inbox.getUID(message);
            String subject = message.getSubject();
            log.debug("--UID----" + uid);
            if (uid.equals("ZC0609-7VJlP_snVXY6LgVY7tQlW9k")) {
                log.debug("------------删除进了------" + message.isSet(Flags.Flag.DELETED));
                //set the DELETE flag to true
                // message.setFlag(Flags.Flag.DELETED, true);
                message.setFlag(Flags.Flag.DELETED, true);//设置已删除状态为true
                log.debug("------------删除进了------" + message.isSet(Flags.Flag.DELETED));
                if (message.isSet(Flags.Flag.DELETED)) {
                    System.out.println("已经删除第" + i + "邮件。。。。。。。。。");
                }
                log.debug("------------ 保存设置状态------" + message.isSet(Flags.Flag.DELETED));
//                保存设置状态
                message.saveChanges();
                System.out.println("Marked DELETE for message: " + subject);


            }


        }
    }


    public static void main(String[] args) throws NoSuchProviderException, MessagingException {
        CrmEmailAccountEntity mailConfig = new CrmEmailAccountEntity();
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
        mailBody.setMailContent("测试成功！！");
        mailBody.setMailCopy("caowenlong@sinosoft.com.cn");
        //  mailBody.setSendAddress("");
//        测试发送邮件
        new MailUtil().send(mailConfig, mailBody);
        System.out.println("=============================================");
        //MailUtil.receiveMail(mailConfig);


//      测试是否可以连接到服务器
        //System.out.println("============================================="+new MailUtil().receive(mailConfig));
        // new MailUtil().receive(mailConfig);
    }

    /**
     * imap方式删除成功---但是不符合逻辑
     * @throws MessagingException
     */
    @Test
    public void f() throws MessagingException {

        String imapserver = "imap.exmail.qq.com";
        String user = "caowenlong@sinosoft.com.cn";
        String pwd = "Cwl19990228";
       // String todelfrom = "zzzzzz@126.com";
        //String todelsubject = ".*测试";
        Properties prop = System.getProperties();
        prop.put("mail.imap.host", imapserver);
        prop.put("mail.imap.auth.plain.disable", "true");//不知道为什么，以plain方式登录出错，我就禁用它了
        Session mailsession = Session.getInstance(prop, null);
        mailsession.setDebug(false);

        IMAPStore store = null;
        try {
            store = (IMAPStore) mailsession.getStore("imap");
        } catch (javax.mail.NoSuchProviderException e) {
            e.printStackTrace();
        }
        try {
            store.connect(imapserver, user, pwd);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX");

        boolean delresult = false;
        try {
            folder.open(Folder.READ_WRITE);
        } catch (MessagingException ex) {
            System.err.println("不能以读写方式打开邮箱!");
        }
        int total = folder.getMessageCount();
        System.out.println("total:" + total);
        Message uid[]=  folder.getMessages();
        for (int i = 1; i <= total; i++) {

            IMAPMessage msg = (IMAPMessage) folder.getMessage(i);

            String gfrom = InternetAddress.toString(msg.getFrom());
            String from = gfrom.substring(gfrom.indexOf("<") + 1, gfrom.indexOf(">"));
            //获取邮件的UID

            log.debug("--UID----" +uid);
           // if (from.equals(todelfrom) && msg.getSubject().matches(todelsubject)) {
            if (uid.equals("ZC0609-7VJlP_snVXY6LgVY7tQlW9k")) {
              //  msg.setFlag(Flags.Flag.DELETED, true); // set the DELETED flag
                delresult = true;
            }
            //}
        }


        folder.close(true); //退出收件箱时,删除做了删除标识的邮件
        if (delresult)
            System.out.println("成功删除该邮件！");
        else
            System.out.println("删除该邮件失败,或该邮件不存在！");
        store.close();
    }

    /**
     * 测试删除二
     * @throws MessagingException
     * @throws IOException
     */
    @Test
public void f2() throws MessagingException, IOException {

        // 连接pop3服务器的主机名、协议、用户名、密码
        String pop3Server = "imap.exmail.qq.com";
        String protocol = "pop3";
        String user = "caowenlong@sinosoft.com.cn";
        String pwd = "Cwl19990228";

        // 创建一个有具体连接信息的Properties对象
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", protocol);
        props.setProperty("mail.pop3.host", pop3Server);

        // 使用Properties对象获得Session对象
        Session session = Session.getInstance(props);
        session.setDebug(true);

        // 利用Session对象获得Store对象，并连接pop3服务器
        Store store = session.getStore();
        store.connect(pop3Server, user, pwd);

        // 获得邮箱内的邮件夹Folder对象，以"读-写"打开
        Folder folder = store.getFolder("inbox");
        folder.open(Folder.READ_WRITE);

        POP3Folder inbox = (POP3Folder) folder;
        // 搜索发件人为 test_hao@sina.cn 和主题为"测试1"的邮件
        SearchTerm st = new AndTerm(
                new FromStringTerm("cwl231561@163.com"),
                new SubjectTerm("s"));

//      // 获得邮件夹Folder内的所有邮件Message对象
//      Message [] messages = folder.getMessages();

        // 不是像上面那样直接返回所有邮件，而是使用Folder.search()方法
        Message [] messages = folder.search(st);
        int mailCounts = messages.length;
        System.out.println("搜索过滤到" + mailCounts + " 封符合条件的邮件！");

        for(int i = 0; i < mailCounts; i++) {
log.debug("--------------"+inbox.getUID(messages[i]));
            String subject = messages[i].getSubject();
            String from = (messages[i].getFrom()[0]).toString();

            System.out.println("第 " + (i+1) + "封邮件的主题：" + subject);
            System.out.println("第 " + (i+1) + "封邮件的发件人地址：" + from);

            //System.out.println("是否删除该邮件(yes/no)?：");
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //String input = br.readLine();
            //if("yes".equalsIgnoreCase(input)) {
                // 直接输出到控制台中
//              messages[i].writeTo(System.out);
                // 设置删除标记，一定要记得调用saveChanges()方法
                messages[i].setFlag(Flags.Flag.DELETED, true);
                messages[i].saveChanges();
                System.out.println("成功设置了删除标记！");
           // }
        }
        // 关闭连接时设置了删除标记的邮件才会被真正删除，相当于"QUIT"命令
        folder.close(false);
        store.close();
    }


}
