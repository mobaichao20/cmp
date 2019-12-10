package io.cmp.modules.mma.vo;

import lombok.Data;

@Data
public class SmsFile {
    //客户姓
    private String surname;
    //客户名
    private String customerName;
    //发送时间
    private String deliveryTime;
    //客户号码
    private String  mobile;
    //状态（已发送，发送失败，暂存）
    private String  status;
    //模板类型
    private String templateType;
    //开始时间
    private String startTime;
    //结束时间
    private String endTime;
}
