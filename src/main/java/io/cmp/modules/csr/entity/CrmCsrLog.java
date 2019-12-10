package io.cmp.modules.csr.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 客户满意度记录表
 */
@Data
@TableName("crm_csr_log")
public class CrmCsrLog {
    @TableId
    private String csrLogId;           //记录id

    private String customerId;         //客户id
    private String customerName;       //客户名
    private String userName;           //坐席名
    private String serviceId;          //会话id
    private Integer channelId;         //渠道id
    private Integer csrLog;       //满意度
    @TableField(exist=false)
    private String LogNum;       //满意度值

    private Date createTime;           //创建时间


}
