package io.cmp.modules.mar.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 营销任务操作历史表
 * 
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@Data
@TableName("crm_marketing_task_log")
public class MarketingTaskLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String logId;
	/**
	 * 营销任务ID
	 */
	private String marketingTaskId;
	/**
	 * 营销任务状态
	 */
	private Integer marketingTaskStatus;
	/**
	 * 任务开始时间
	 */
	private Date beginTime;
	/**
	 * 任务结束时间
	 */
	private Date endTime;
	/**
	 * 结束码
	 */
	private Integer endCode;
	/**
	 * 联络时间
	 */
	private Date contactTime;
	/**
	 * 联系电话
	 */
	private String customerTelephone;
	/**
	 * 预约ID
	 */
	private String bookingId;
	/**
	 * 预约类型
	 */
	private Integer bookingType;
	/**
	 * 预约时间
	 */
	private Date bookingTime;
	/**
	 * 录音ID
	 */
	private String recordingsId;
	/**
	 * 营销任务备注信息
	 */
	private String remark;
	/**
	 * 操作人
	 */
	private String userCode;
	/**
	 * 操作姓名
	 */
	private String userName;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
