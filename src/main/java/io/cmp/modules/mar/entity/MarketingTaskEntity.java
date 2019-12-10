package io.cmp.modules.mar.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 营销任务主表
 * 
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 14:30:51
 */
@Data
@TableName("crm_marketing_task")
public class MarketingTaskEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String marketingId;
	/**
	 * 所属活动ID
	 */
	private String campaignId;
	/**
	 * 外联表主键
	 */
	private String outriggerId;
	/**
	 * 预约事件ID
	 */
	private String bookingId;
	/**
	 * 任务名称
	 */
	private String taskName;
	/**
	 * 客户姓名
	 */
	private String customerName;
	/**
	 * 客户电话
	 */
	private String customerTelephone;
	/**
	 * 客户证件号
	 */
	private String customerCredentialNo;
	/**
	 * 客户证件类型
	 */
	private Integer customerCredentialType;
	/**
	 * 客户邮件
	 */
	private String customerEmail;
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
	 * 任务状态，1：未开始，2：进行中，3：已完成，4：注销
	 */
	private Integer taskStatus;
	/**
	 * 所属坐席
	 */
	private String userCode;
	/**
	 * 所属机构
	 */
	private String organCode;
	/**
	 * 来源
	 */
	private Integer source;
	/**
	 * 第一级状态
	 */
	private Integer firstStatus;
	/**
	 * 第二级状态
	 */
	private Integer secondStatus;
	/**
	 * 第三级状态
	 */
	private Integer thirdStatus;
	/**
	 * 是否预约
	 */
	private Integer isBooking;
	/**
	 * 预约类型
	 */
	private Integer bookingType;
	/**
	 * 预约时间
	 */
	private Date bookingTime;
	/**
	 * 逾期时间
	 */
	private Date overdueTime;
	/**
	 * 批次
	 */
	private Long batch;
	/**
	 * 备注
	 */
	private String description;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人ID
	 */
	private String createCode;
	/**
	 * 创建人姓名
	 */
	private String createName;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 修改人ID
	 */
	private String updateCode;
	/**
	 * 修改人姓名
	 */
	private String updateName;

}
