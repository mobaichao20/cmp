package io.cmp.common.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
/**
 * 创建发送器
 */
public class MailUtil {
    public static JavaMailSenderImpl createMailSender(){

        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        sender.setHost("exmail.qq.com");

        sender.setPort(25);

        sender.setUsername("caowenlong@sinosoft.com.cn");

        sender.setPassword("Cwl19990228");

        sender.setDefaultEncoding("Utf-8");

        Properties p = new Properties();

        p.setProperty("mail.smtp.timeout",1000+"");

        p.setProperty("mail.smtp.auth","true");

        sender.setJavaMailProperties(p);

        return sender;

    }

    public static void main(String[] args) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cwl231561@163.com");
        message.setTo("cwl231561@163.com");
        message.setSubject("测试");
        message.setText("测试");
        createMailSender().send(message);
    }

}
