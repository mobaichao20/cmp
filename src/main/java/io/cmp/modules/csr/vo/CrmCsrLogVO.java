package io.cmp.modules.csr.vo;

import lombok.Data;

import java.util.Date;
@Data
public class CrmCsrLogVO {
    private String csrLogId;           //记录id

    private String customerId;         //客户id
    private String customerName;       //客户名
    private String userName;           //坐席名
    private String serviceId;          //会话id
    // private String service;            //会话
    private Integer channelId;         //渠道id
    private String channel;            //渠道
    private Integer csrLog;            //满意度
    private String LogNum;             //满意度值
    private Date createTime;           //创建时间
}
