package io.cmp.modules.mma.vo;

import lombok.Data;

import java.util.List;

/**
 * 短信发送时需要的对象
 */
@Data
public class SendMsg {
    //传固定值sendSMSMD5
    private String command;
    //短信发送账号
    private String username;
    //登录密码的MD5(32位加密且大写)
    private String pwd;
    //手机号码（多个号码用英文逗号或者分号隔开），不能超过100个号码
    private String sendNumber;
    //短信内容(最多140字符)
    private String content;
    //字符在传递过程中的编码,与outcode保持一致,GBK或UTF-8
    private String incode;
    //字符初始编码与incode保持一致, GBK或UTF-8
    private String outcode;
    //可选参数。“text”，返回结果为普通字符串格式；
    // “xml”，返回结果为xml格式。不填该参数，则默认为text
    private String rstype;
    //pfex为违禁词过滤开关: 0表示开启 ; 1表示关闭 ; 空表示默认开启
    private String pfex;
    //扩展码参数只允许数字字符
    private String extCode;
    //客户姓名
    private String customerName;
    //当前用户姓名
    private String sendName;
    //当前用户工号
    private String createCode;
    //模板名称
    private String templateName;
    //客户姓名
    private String cusNameId;
    //短信发送记录ID
    private String id;
   //分辨是发送还是保存
    private String sendFlag;
    //预约发送时间
    private String deliveryTime;

}
