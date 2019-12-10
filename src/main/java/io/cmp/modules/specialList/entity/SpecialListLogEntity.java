package io.cmp.modules.specialList.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 特殊名单日志表
 */
@Data
@TableName("crm_special_list_log")
public class SpecialListLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 日志ID
     */
    private String logId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求时间
     */
    private Date requestTime;

    /**
     * 请求参数
     */
    private String requestParam;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 描述
     */
    private String operation;
}
