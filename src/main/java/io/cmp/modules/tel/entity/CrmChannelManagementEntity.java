package io.cmp.modules.tel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import lombok.Data;

/**
 * 渠道管理表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-01 10:46:46
 */
@Data
@TableName("crm_channel_management")
public class CrmChannelManagementEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 话务品牌
	 */
	private String trafficBrand;
	/**
	 * CTI地址主
	 */
	private String ctiAddressMaster;
	/**
	 * CTI地址从
	 */
	private String ctiAddressSlave;
	/**
	 * 满意度节点号
	 */
	private String satisfactionNodeNumber;
	/**
	 * 播报工号节点
	 */
	private String broadcasterNode;
	/**
	 * 每日工作时间开始
	 */
	private Time workingHoursBegin;
	/**
	 * 每日工作时间结束
	 */
	private Time workingHoursEnd;
	/**
	 * 是否启用黑名单 0:否 1:是
	 */
	private String isBlacklist;
	/**
	 * 创建人代码
	 */
	private String createCode;
	/**
	 * 创建人
	 */
	private String createName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 开始创建时间
	 */
	@TableField(exist=false)
	private String startCreateTime;
	/**
	 * 结束创建时间
	 */
	@TableField(exist=false)
	private String endCreateTime;
	/**
	 * 修改人代码
	 */
	private String updateCode;
	/**
	 * 修改人
	 */
	private String updateName;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 开始修改时间
	 */
	@TableField(exist=false)
	private String startUpdateTime;

	/**
	 * 结束修改时间
	 */
	@TableField(exist=false)
	private String endUpdateTime;

}
