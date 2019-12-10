package io.cmp.modules.mail.handle;

import com.sun.mail.pop3.POP3Folder;
import io.cmp.common.utils.DateUtils;
import io.cmp.modules.mail.dao.CrmEmailReceiverDao;
import io.cmp.modules.mail.entity.CrmEmailAccountEntity;
import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;
import io.cmp.modules.mail.entity.CrmEmailReceiverEntity;
import io.cmp.modules.mail.entity.CrmEmailSendEntity;
import io.cmp.modules.mail.service.CrmEmailReceiverService;
import io.cmp.modules.mail.service.impl.CrmEmailReceiverServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Slf4j
public class ReceiveEmailHandlerImpl extends EMailReceive {
    //@Autowired
    private CrmEmailReceiverServiceImpl mailReceiveLogService = new CrmEmailReceiverServiceImpl();

    private String baseSavePath;

    //base路径
    public ReceiveEmailHandlerImpl(StringBuilder baseSavePath) {
        this.baseSavePath = baseSavePath.toString();
    }


    public static boolean isNew(MimeMessage message ) throws MessagingException {
        boolean isnew = false;
        Flags flags = ((Message) message).getFlags();
        log.debug("-----flags111-------"+flags);
        Flags.Flag[] flag = flags.getSystemFlags();
        log.debug("-----flag22222-------"+flag.length);
//	System.out.println("flags's　length:　" + flag.length);
        for (int i = 0; i < flag.length; i++) {
            log.debug("-----flag[i]-------"+flag[i]);
            if (flag[i] == Flags.Flag.SEEN) {
                isnew = true;
//	System.out.println("seen　Message.......");
                break;
            }
        }
        return isnew;
    }


    public static boolean getReplySign(MimeMessage mimeMessage) throws MessagingException {
        boolean replysign = false;
        String needreply[] = mimeMessage
                .getHeader("Disposition-Notification-To");
        if (needreply != null) {
            replysign = true;
        }
        return replysign;
    }

    /**
     * 接收邮件
     */
    public synchronized List receiveMails(int times, CrmEmailAccountEntity mailServerInfo, CrmEmailReceiverService crmEmailReceiverService) {
        log.debug("------------------mailReceiveLogService----------------------" + mailReceiveLogService);
        log.debug("------------------crmEmailReceiverDao----------------------" + crmEmailReceiverService);
        List<List> objList = new ArrayList<List>();

        List<CrmEmailReceiverEntity> receiveMailList = new ArrayList<CrmEmailReceiverEntity>();
        // List<MailReceiveLogEntity> receiveList = new ArrayList<MailReceiveLogEntity>();

        //邮件接收服务器地址
        String host = mailServerInfo.getReceiveMailServer();

        //邮箱用户名
        String username = mailServerInfo.getMailAddress();
        if (mailServerInfo.getMcSmtpName() != null) {
            username = mailServerInfo.getMcSmtpName();
        }

        //邮箱用户密码
        String password = mailServerInfo.getPassword();

        if (mailServerInfo.getMcSmtpPwd() != null) {

            password = mailServerInfo.getMcSmtpPwd();

        }

        Store store = null;
        Folder folder = null;
        Session session = null;
        Connection conn = null;

        try {
            final String uName = username, pwd = password;
            // conn = Derby.openConnection();
            //Access access = new Access(conn);
            Properties props = new Properties();

            props.put("mail.smtp.host", mailServerInfo.getSendMailServer());   //邮件发送服务器地址
            props.put("mail.smtp.auth", "true");

            session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(uName, pwd);
                }
            });
            session.setDebug(true);

            int port = 110;

            //邮件接收服务器端口
            if (mailServerInfo.getReceiveServerPort() != null) {
                port = Integer.parseInt(mailServerInfo.getReceiveServerPort());
            }

            //邮件接收服务器地址
            store = session.getStore("pop3");
            store.connect(host, username, password);
            // store.connect(host, username, password);

            folder = store.getFolder("INBOX");

            folder.open(Folder.READ_WRITE);
            int loop = 0;
            if (times >= folder.getMessageCount()) {
                loop = times;
            } else {
                loop = folder.getMessageCount();
            }

            Message[] messages = folder.getMessages();

            int i = 0;
            POP3Folder inbox = (POP3Folder) folder;

            for (int im = 0, count = messages.length; im < count; im++) {

                MimeMessage message = (MimeMessage) messages[im];
//                message.get
               log.debug("========================================是否回复========================================="+isNew(message));
                //给 EMailReceive 中的变量赋值，以便后续代码使用
                super.setMimeMessage(message);

                if (i == loop) {
                    break;
                }

                StringBuilder saveFilePath = new StringBuilder(200).append(this.baseSavePath);
                String senderAddress = super.getFrom();

                if (senderAddress.indexOf("<") != -1) {
                    senderAddress = senderAddress.substring(senderAddress.indexOf("<") + 1, senderAddress.length());
                }

                if (senderAddress.indexOf(">") != -1) {
                    senderAddress = senderAddress.substring(0, senderAddress.indexOf(">"));
                }

                //获取邮件的UID
                String uid = inbox.getUID(message);

                log.debug("uid------------------------------------------------------" + uid + "--");

//              先去查询数据库中的邮件

                List<CrmEmailReceiverEntity> result = mailReceiveLogService.emailReceiverList(crmEmailReceiverService);
                boolean flag = false;
                log.debug("--------数据库中是否存在-flag1--------------------------" + flag);
                if (result != null) {

                    for (CrmEmailReceiverEntity c : result) {
                        if (c.getId().trim().equals(uid)) {
                            flag = true;
                        }
                    }

                }
                log.debug("--------数据库中是否存在-flag2--------------------------" + flag);
                //与本地接收库的UID 比较，判断是否新邮件
                if (!flag) {
                    log.debug("-----------------senderAddress------------"+senderAddress);
                    saveFilePath.append(senderAddress);
                    saveFilePath.append(getFileSeparator());
//                    这是接收邮件
                    saveFilePath.append("receiver");
                    saveFilePath.append(getFileSeparator());
                    saveFilePath.append(DateUtils.format(new Date()));
                    saveFilePath.append(getFileSeparator());
                    saveFilePath.append(java.util.UUID.randomUUID());
                    saveFilePath.append(getFileSeparator());

                    StringBuilder saveAccessoriesFilePath = new StringBuilder(200);
                    StringBuilder saveTextFilePath = new StringBuilder(200);
                    StringBuilder saveLogsFilePath = new StringBuilder(200);
                    StringBuilder accessFilePath = new StringBuilder(200);

                    accessFilePath.append(saveFilePath).append("db").append(getFileSeparator());
                    saveAccessoriesFilePath.append(saveFilePath).append("accessories").append(getFileSeparator());
                    saveTextFilePath.append(saveFilePath).append("text").append(getFileSeparator());
                    saveLogsFilePath.append(saveFilePath).append("logs").append(getFileSeparator());

                    File saveAccessoriesFileDir = new File(saveAccessoriesFilePath.toString());
                    File saveTextFileDir = new File(saveTextFilePath.toString());
                    File saveLogsFileDir = new File(saveLogsFilePath.toString());

                    if (!saveAccessoriesFileDir.canWrite() || !saveAccessoriesFileDir.isDirectory()
                            || !saveAccessoriesFileDir.exists()) {
                        saveAccessoriesFileDir.mkdirs();
                        System.out.println(" 创建邮件附件存储路径 ： " + saveAccessoriesFilePath.toString());
                    }

                    if (!saveTextFileDir.canWrite() || !saveTextFileDir.isDirectory()
                            || !saveTextFileDir.exists()) {
                        saveTextFileDir.mkdirs();
                        System.out.println(" 创建正文附件存储路径 ： " + saveTextFilePath.toString());
                    }

                    if (!saveLogsFileDir.canWrite() || !saveLogsFileDir.isDirectory()
                            || !saveLogsFileDir.exists()) {
                        saveLogsFileDir.mkdirs();
                        System.out.println(" 创建正文附件存储路径 ： " + saveLogsFilePath.toString());
                    }


                    this.setAccessPath((accessFilePath.toString()));
                    this.setAttachPath(saveAccessoriesFilePath.toString());
                    this.setSaveMailContentPath(saveTextFilePath.toString());
                    this.setSaveLogsFilePath(saveLogsFilePath.toString());
                    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

                    getMailHtmlBody().delete(0, getMailHtmlBody().length());
                    getMailTextBody().delete(0, getMailTextBody().length());
                    setMimeMessage((MimeMessage) message);
                    super.getMailContent((Part) message);

                    //邮件实体类
                    CrmEmailReceiverEntity mail = new CrmEmailReceiverEntity();
                    mail.setId(uid);
                    mail.setSender(getFrom());                        //发件人
                    mail.setReceiver(getMailAddress("to"));        //收件人
                    mail.setMailCopy(getMailAddress("cc"));        //抄送
                    mail.setSecuritySend(getMailAddress("bcc"));        //密送
                    mail.setMailSubject(getSubject());                    //主题
                    mail.setMailContent(getBodyText());                    //正文
                    mail.setReceiceTime(message.getSentDate());            //接收时间
                    mail.setMailType("1");
                    mail.setMediumType("邮件");
//                    默认未回复
                    mail.setIsReplay("0");
//                    是否有邮件附件
                    mail.setIsMailAttachment("1");
                    //mail.setMbSendTime(message.getSentDate());            //发送时间
                    //mail.setMbMailType(0);                                //1：出  0：入
                    //mail.setMbMail(mailServerInfo.getMcId());            //邮件来源
                    mail.setIsDelete("0");

                    //获取附件
                    List<CrmEmailAttachmentEntity> m_mailResourceList = saveAttachMent((Part) message, mailServerInfo.getConfigId());
                    log.debug(">>>>>>>>>>>>>>>>>> m_mailResourceList " + uid + "==" + m_mailResourceList.size());
                    mail.setMailResource(m_mailResourceList);        //附件

                    //将每一封邮件放入邮件集合
                    receiveMailList.add(mail);

                    /*将本地接收库放入集合*/
                /*    MailReceiveLogEntity mailReceive = new MailReceiveLogEntity();
                    mailReceive.setMrcUid(uid);
                    mailReceive.setMrcMailTime(new Date());
                    receiveList.add(mailReceive);*/

                    i++;
                }
            }
            store.close();
            objList.add(receiveMailList);
            //objList.add(receiveList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Derby.closeConnection(conn);

            if (folder != null && folder.isOpen()) {
                try {
                    folder.close(true);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }

            if (folder.getStore() != null && folder.getStore().isConnected()) {
                try {
                    folder.getStore().close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }

        return objList;
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
        //new MailUtil().send(mailConfig, mailBody);
        // new ReceiveEmailHandlerImpl(new StringBuilder("D:\\testEmail")).receiveMails(0,mailConfig);
        // log.debug("---------------------------------------"+new ReceiveEmailHandlerImpl(new StringBuilder("D:\\testEmail")).receiveMails(0,mailConfig,crmEmailReceiverDao));


        // new MailUtil().receive(mailConfig);
    }

    /**
     * 通过冒号去分割储存路径
     */
    public static String spit(String str) {
        String result = "";
        if (str != null && str.trim() != "") {
            String[] res = str.split(":");
            result = res[0] + ":receiver/" + res[1];
        }

        return result;
    }

}
