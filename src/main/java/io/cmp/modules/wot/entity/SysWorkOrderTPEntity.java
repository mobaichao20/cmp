package io.cmp.modules.wot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 工单模板
 */
@Data
@TableName("sys_workorder_tp")
public class SysWorkOrderTPEntity {
    /**
     * 主键
     */
    @TableId
    private String syswotpId;
    /**
     *工单模板名
     */
    @TableField
    private String syswotpName;
    /**
     * 创建人
     */
    private String createName;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateName;
    /**
     *更新时间
     */
    private Date updateTime;
    /**
     *备注
     */
    private String remark;
    /**
     *提醒类型
     */
    private int remindType;
    /**
     *提醒次数
     */
    private int remindNum;
    /**
     *提示时间
     */
    private int promTime;
    /**
     *是否缓存
     */
    private int isCache;
    /**
     *  工单类型
     */
    private int woType;


}
