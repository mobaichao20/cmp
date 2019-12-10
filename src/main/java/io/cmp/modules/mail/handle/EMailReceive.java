package io.cmp.modules.mail.handle;

import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;
import io.cmp.modules.sys.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class EMailReceive extends AbstractController {
    private MimeMessage mimeMessage;
    private String saveAttachPath;

    private String accessPath;
    private String attachPath;
    private String saveMailContentPath;
    private String saveLogsFilePath;
    private boolean isSaveBodyToFile;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private String dateformat="YYYY-MM-dd HH:mm:ss";

    private StringBuilder mailTextBody = new StringBuilder();
    private StringBuilder mailHtmlBody_liuquanlin = new StringBuilder();
    private StringBuilder mailTextBody_liuquanlin = new StringBuilder();
    private List<CrmEmailAttachmentEntity> m_mailResourceList = null;

    /**
     * 获取邮件主题
     */
    public String getSubject() throws MessagingException {
        String subject = "";
        try {
            String rawvalue = mimeMessage.getHeader("Subject", null);
            int nStart = 0;
            int nEnd = 0;
            while(true) {
                nStart = rawvalue.indexOf("=?");
                if(nStart != -1) nEnd = rawvalue.indexOf("?=");
                else 			 nEnd = -1;
                if(nStart == -1 || nEnd == -1) {
                    subject += rawvalue;
                    break;
                }
                if(nStart > 0) subject += rawvalue.substring(0, nStart);
                String strEncode = rawvalue.substring(nStart, nEnd+2);
                if(strEncode.indexOf("=?gb2312?") == 0) {
                    strEncode = strEncode.replace("=?gb2312?", "=?gbk?");
                }
                strEncode = MimeUtility.decodeText(strEncode);
                subject += strEncode;
                rawvalue = rawvalue.substring(nEnd+2);
            }

            if(!"".equals(subject)){
                SinoDetect sinodetector =new SinoDetect();

                int result = sinodetector.detectEncoding(subject.getBytes());
                switch(result){
                    case 9:
                        subject = new String(subject.getBytes("ISO8859-1"),"utf-8");

                        break;
                }
            }else{
                subject = "无";
            }
        } catch (Exception exce) {
        }
        return subject;
    }

    /**
     * 获取邮件发送时间
     */
    public String getSentDate() throws MessagingException {
        String sendDate = "";
        try {
            if (mimeMessage.getSentDate() != null) {
                SimpleDateFormat format = new SimpleDateFormat(dateformat);
                sendDate = format.format(mimeMessage.getSentDate());
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return sendDate;
    }

    /**
     * 获取邮件发送人地址
     */
    public String getFrom() throws MessagingException {
        String fromaddr = "";
        try {
            InternetAddress address[] = (InternetAddress[]) mimeMessage.getFrom();
            if (address != null) {
                String personal = "";
                String org_from = address[0].toString();
                if(org_from.indexOf("?gb2312?") > -1) {
                    org_from = org_from.replace("?gb2312?", "?gbk?");
                }
                try {
                    org_from = MimeUtility.unfold(org_from);
                    personal = MimeUtility.decodeText(org_from);
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                fromaddr = personal;
            } else {
                fromaddr = "Do not specify a sender";
            }
        }catch (MessagingException e){
            e.printStackTrace();
            fromaddr = "Do not specify a sender";
        }

        //将发件箱地址中包含的'去掉
        fromaddr = fromaddr.replaceAll("'", "");
        fromaddr = fromaddr.replaceAll("\"", "");
        return fromaddr;
    }

    /**
     * 获取邮件接收人地址、抄送地址、密送地址
     * @param type 地址类型
     *
     */
    public String getMailAddress(String type) throws MessagingException {
        String mailaddr = "";
        String addtype = type.toUpperCase();
        InternetAddress[] address = null;
        if (addtype.equals("TO") || addtype.equals("CC")
                || addtype.equals("BCC")) {
            if (addtype.equals("TO")) {
                try {
                    address = (InternetAddress[]) mimeMessage
                            .getRecipients(Message.RecipientType.TO);
                } catch (MessagingException e) {

                }
            } else if (addtype.equals("CC")) {
                try {
                    address = (InternetAddress[]) mimeMessage
                            .getRecipients(Message.RecipientType.CC);
                } catch (MessagingException e) {

                    e.printStackTrace();
                }
            } else {
                try {
                    address = (InternetAddress[]) mimeMessage
                            .getRecipients(Message.RecipientType.BCC);
                } catch (MessagingException e) {

                    e.printStackTrace();
                }
            }

            if (address != null) {
                for (int i = 0; i < address.length; i++) {
                    String email = address[i].getAddress();
                    if (email == null)
                        email = "";
                    else {
                        try {
                            email = MimeUtility.decodeText(email);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    String personal = address[i].getPersonal();
                    if (personal == null)
                        personal = "";
                    else {
                        try {
                            personal = MimeUtility.decodeText(personal);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
//					String compositeto = personal + "<" + email + ">";
                    String compositeto = email;
                    mailaddr += ";" + compositeto;
                }
                if (mailaddr.length() != 0) {
                    mailaddr = mailaddr.substring(1);
                }
            }
        } else {
            try {
                throw new Exception("Error emailaddr type!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mailaddr;
    }

    /**
     * 获取邮件内容
     */
    public void getMailContent(Part part) {
        try {
            this.getMailContent_liuquanlin_20181030(part);
            mailHtmlBody = mailHtmlBody_liuquanlin;
            mailTextBody = mailTextBody_liuquanlin;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void getMailContent_liuquanlin_20181030(Part part) throws Exception {
        String strContentTye = part.getContentType();
        if (part.isMimeType("text/plain")) {
            String strContent = this.GetContentFromStream(part);
            strContent = this.txt2html(strContent);
            mailTextBody_liuquanlin.append(strContent);
            return;
        } else if (part.isMimeType("text/html")) {
            String strContent = this.GetContentFromStream(part);
            mailHtmlBody_liuquanlin.append(strContent);
            return;
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int nCount = multipart.getCount();
            for (int i = 0; i < nCount; i++) {
                getMailContent_liuquanlin_20181030(multipart.getBodyPart(i));
            }
            return ;
        } else if (part.isMimeType("message/rfc822")) {
            //getMailContent_liuquanlin_20181030((Part) part.getContent());
        }
        return ;
    }

    private String GetContentFromStream(Part part) throws Exception, MessagingException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        part.writeTo(out);

        String strStream;
        String strHeads = "";
        String strCharset = "";
        String strEncode = "";

        strStream = out.toString();
        int n = strStream.indexOf("\r\n\r\n");
        if(n == -1) {
            return "";
        }

        strHeads = strStream.substring(0, n);
        strStream = strStream.substring(n+4);

        if(strHeads.indexOf("utf-8") != -1) strCharset = "utf-8";
        if(strHeads.indexOf("gb2312") != -1) strCharset = "gbk";
        if(strHeads.indexOf("base64") != -1) strEncode = "B";
        if(strHeads.indexOf("quoted-printable") != -1) strEncode = "Q";

        if(strCharset == "gbk") {
            strStream = "=?" + strCharset +"?" + strEncode + "?" + strStream + "?=";
            if(strEncode.equals("Q")) strStream = strStream.replace("=\r\n", "");
            strStream = MimeUtility.decodeWord(strStream);
            //String strGbk = new String(strStream.getBytes("GBK"), "GBK");
            //strStream = strGbk;
        }else {
            strStream = part.getContent().toString();
        }

        return strStream;
    }

    private String txt2html(String s) {
        StringBuilder builder = new StringBuilder();
        boolean previousWasASpace = false;
        for( char c : s.toCharArray() ) {
            if( c == ' ' ) {
                if( previousWasASpace ) {
                    builder.append("&nbsp;");
                    previousWasASpace = false;
                    continue;
                }
                previousWasASpace = true;
            } else {
                previousWasASpace = false;
            }
            switch(c) {
                case '<': builder.append("&lt;"); break;
                case '>': builder.append("&gt;"); break;
                case '&': builder.append("&amp;"); break;
                case '"': builder.append("&quot;"); break;
                case '\n': builder.append("<br>"); break;
                // We need Tab support here, because we print StackTraces as HTML
                case '\t': builder.append("&nbsp; &nbsp; &nbsp;"); break;
                default:
                    if( c < 128 ) {
                        builder.append(c);
                    } else {
                        builder.append("&#").append((int)c).append(";");
                    }
            }
        }
        return builder.toString();
    }

    /**
     * 获取邮件内容
     */
    public String getBodyText() {
        String newContent = "";
        if (this.mailHtmlBody.length() != 0){
            newContent = mailHtmlBody.toString();

            newContent = this.filterEmoji(newContent);
        }else if (this.mailTextBody.length() != 0){
            newContent = mailTextBody.toString();

            newContent = this.filterEmoji(newContent);
        }else{
            newContent = "";
        }
        return newContent;
    }

    //过滤表情
    private String filterEmoji(String source) {
        if(source != null) {
            Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
            Matcher emojiMatcher = emoji.matcher(source);
            if ( emojiMatcher.find()) {
                source = emojiMatcher.replaceAll("*");
                return source ;
            }
            return source;
        }
        return source;
    }

    /**
     * 获取邮件附件
     */
    public List<CrmEmailAttachmentEntity> saveAttachMent(Part part, String mailId){
        m_mailResourceList = new ArrayList<CrmEmailAttachmentEntity>();
        this.saveAttachMent_sub(part, mailId);
        return m_mailResourceList;
    }

    private void saveAttachMent_sub(Part part, String mailId){
        try{
            if (part.isMimeType("multipart/*")){
                Multipart mp = (Multipart) part.getContent();
                int nMpCount = mp.getCount();
                for (int i = 0; i < nMpCount; i++){
                    BodyPart mpart = mp.getBodyPart(i);

                    if (mpart.isMimeType("message/rfc822")){
                        m_mailResourceList.add(savemrfile(mpart, mailId));
                    }
                    else if (mpart.isMimeType("multipart/*")){
                        saveAttachMent_sub(mpart,mailId);
                    }
                    else {
                        String disposition = mpart.getDisposition();
                        if ((disposition != null) && ((disposition.equals(Part.ATTACHMENT)) || (disposition.equals(Part.INLINE)))){
                            m_mailResourceList.add(savemrfile(mpart, mailId));
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private CrmEmailAttachmentEntity savemrfile(Part mpart, String mailId) {
        CrmEmailAttachmentEntity mailResource = new CrmEmailAttachmentEntity();
        try {

           // mailResource.setMrType(1);
            String fileName = mpart.getFileName();

            if(fileName != null && fileName.indexOf("=?gb2312?") == 0) {
                fileName = fileName.replace("=?gb2312?", "=?gbk?");
            }
            //subject = MimeUtility.decodeText(mimeMessage.getSubject());
            if(fileName != null) fileName = MimeUtility.decodeText(fileName);

            String contenttype = mpart.getContentType();

            if(fileName == null) {
                String strFileName = UUID.randomUUID().toString();
                String strExtName = "ext";
                if(contenttype.indexOf("message/rfc822") == 0) {
                    strExtName = "eml";
                } else {
                    String[] list1 = contenttype.split(";");
                    if(list1.length > 0) strExtName = list1[0];
                    strExtName = strExtName.replace('/', '.');
                }
                fileName = strFileName + "." + strExtName;
            }

            if (fileName != null){
                mailResource.setName(fileName);

                fileName = saveFile(fileName, mpart.getInputStream());

                if (fileName!=null&&!fileName.equals("")){
                    mailResource.setUrl(fileName);
//                    邮件所属者--可以是当前用户
                    //mailResource.setMrCreater(mailId);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return mailResource;
    }

    /**
     * 保存附件
     */
    private String saveFile(String fileName, InputStream in) throws Exception {
        if (fileName != null) {
            File storefile = new File(this.getAttachPath()+ this.getFileSeparator() + fileName);

            BufferedOutputStream bos = null;
            BufferedInputStream bis = null;
            try {
                if(storefile!=null){
                    FileOutputStream fileOutputStream = new FileOutputStream(storefile);
                    bos = new BufferedOutputStream(fileOutputStream);
                    bis = new BufferedInputStream(in);
                    int c;
                    while ((c = bis.read()) != -1) {
                        bos.write(c);
                        bos.flush();
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                throw new Exception("邮件附件保存失败");
            } finally {
                bos.close();
                bis.close();
            }

        }
        return this.getAttachPath()+ this.getFileSeparator() + fileName;
    }
/*    public Message newReplyMessage(Message message) throws MessagingException, IOException {
   *//*     // 出新初始化一个服务类，因为之前为获取邮件，协议为POP3，当前要发送邮件，协议为SMTP
        emailService = new EmailServiceEnity("", "", EmailServiceEnity.MAIL_PROTOCOL_SMTP);

        // 新创建一个邮件
        Message reply = emailService.getMessage();*//*

        // 获取原邮件的发件人--》为新邮件的接收人
        Address[] addressTo = message.getFrom();

        // 获取原邮件内容（暂支持文本和HTML单部件，多部件另需递归循环原邮件，并新增）
        Object content = message.getContent();

        // 获取原邮件主题
        String subObject = message.getSubject();

        // 获取邮件发送时间
        String date = message.getSentDate().toString();

        *//** 组装新邮件*//*
        // 设置回复内容，新内容+原文
        reply.setContent(content + "<br/><br/><br/>" + date +","+
                InternetAddress.toString(addressTo) + "写到:<br/>> " +
                content, "text/html;charset=utf-8");

        // 设置邮件回复主题
        reply.setSubject("Re：" + subObject);

        // 设置收件人：原发件人
        reply.addRecipients(Message.RecipientType.TO, addressTo);

        return reply;
    }*/
    /**
     * 系统参数
     */
    static final int GB2312 = 0;
    static final int GBK = 1;
    static final int HZ = 2;
    static final int BIG5 = 3;
    static final int EUC_TW = 4;
    static final int ISO_2022_CN = 5;
    static final int UTF8 = 6;
    static final int UNICODE = 7;
    static final int ASCII = 8;
    static final int OTHER = 9;

    static final int TOTAL_ENCODINGS = 10;
    public static String[] nicename;
    public static String[] codings;

    static{
        codings = new String[TOTAL_ENCODINGS];
        codings[GB2312] = "GB2312";
        codings[GBK] = "GBK";
        codings[HZ] = "HZ";
        codings[BIG5] = "BIG5";
        codings[EUC_TW] = "CNS11643";
        codings[ISO_2022_CN] = "ISO2022CN";
        codings[UTF8] = "UTF8";
        codings[UNICODE] = "Unicode";
        codings[ASCII] = "ASCII";
        codings[OTHER] = "OTHER";

        nicename = new String[TOTAL_ENCODINGS];
        nicename[GB2312] = "GB2312";
        nicename[GBK] = "GBK";
        nicename[HZ] = "HZ";
        nicename[BIG5] = "Big5";
        nicename[EUC_TW] = "CNS 11643";
        nicename[ISO_2022_CN] = "ISO 2022-CN";
        nicename[UTF8] = "UTF-8";
        nicename[UNICODE] = "Unicode";
        nicename[ASCII] = "ASCII";
        nicename[OTHER] = "OTHER";
    }

    public String getAccessPath() {
        return accessPath;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    public String getAttachPath() {
        return saveAttachPath;
    }

    public void setAttachPath(String attachpath) {
        this.saveAttachPath = attachpath;
    }

    public String getSaveMailContentPath() {
        return saveMailContentPath;
    }

    public void setSaveMailContentPath(String saveMailContentPath) {
        this.saveMailContentPath = saveMailContentPath;
    }


    //***************************************************************************/
    public static String decodeWord_s(String s) {
        if (s == null || s.equals("")) {
            return "";
        }
        if (!s.startsWith("=?")) {
            return s;
        }
        if (s.indexOf("=?") != -1) {
            int i = 2;
            int j;
            if ((j = s.indexOf(63, i)) == -1)
                return s;
            String s1 = (s.substring(i, j));
            i = j + 1;
            if ((j = s.indexOf(63, i)) == -1)
                return s;
            String s2 = s.substring(i, j);
            i = j + 1;
            if ((j = s.indexOf("?=", i)) == -1)
                return s;
            String s3 = s.substring(i, j);
            try {
                ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(
                        s3.getBytes());
                Object obj;
                if (s2.equalsIgnoreCase("B"))
                    obj = new com.sun.mail.util.BASE64DecoderStream(
                            bytearrayinputstream);
                else if (s2.equalsIgnoreCase("Q"))
                    obj = new com.sun.mail.util.QDecoderStream(
                            bytearrayinputstream);

                else
                    return s;
                int k = bytearrayinputstream.available();
                byte abyte0[] = new byte[k];
                k = ((InputStream) (obj)).read(abyte0, 0, k);
                return new String(abyte0, 0, k);
            }

            catch (Exception e) {
                return s;
            }
        }
        return s;
    }

    public void setDateFormat(String format) throws Exception {
        this.dateformat = format;
    }

    protected PasswordAuthentication getPasswordAuthentication(String username,
                                                               String password) {
        return new javax.mail.PasswordAuthentication(username, password);
    }

    public String getProjectPaht() {
        File file = new File(".");

        return file.getAbsolutePath().substring(0,file.getAbsolutePath().indexOf(this.getFileSeparator()));
    }

    public String getPhysicsPaht() {
        File file = new File(".");

        return file.getAbsolutePath().substring(0,file.getAbsolutePath().indexOf(getFileSeparator()) + 1);
    }

    //解码
    public  String base64Decoder(String s) throws Exception {
        return MimeUtility.decodeText(s);
    }

    public String getFileSeparator() {
        return System.getProperty("file.separator");
    }

    public String formatDateToString(String style, java.util.Date date) {
        SimpleDateFormat format = new SimpleDateFormat(style);

        return format.format(date);
    }

    public String getSaveLogsFilePath(){
        return saveLogsFilePath;
    }

    public void setSaveLogsFilePath(String saveLogsFilePath){
        this.saveLogsFilePath = saveLogsFilePath;
    }

    public StringBuilder getMailTextBody() {
        return mailTextBody;
    }

    public void setMailTextBody(StringBuilder mailTextBody) {
        this.mailTextBody = mailTextBody;
    }

    public StringBuilder getMailHtmlBody() {
        return mailHtmlBody;
    }

    public void setMailHtmlBody(StringBuilder mailHtmlBody) {
        this.mailHtmlBody = mailHtmlBody;
    }

    private StringBuilder mailHtmlBody = new StringBuilder();

    public boolean isSaveBodyToFile() {
        return isSaveBodyToFile;
    }

    public void setSaveBodyToFile(boolean isSaveBodyToFile) {
        this.isSaveBodyToFile = isSaveBodyToFile;
    }

    public void setMimeMessage(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }
}
