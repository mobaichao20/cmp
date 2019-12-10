package io.cmp.modules.out.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 外联数据表
 * 
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 14:30:51
 */
@Data
@TableName("crm_outrigger_message")
public class OutriggerMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String outriggerId;
	/**
	 * 活动ID
	 */
	private String campaignId;
	/**
	 * 客户姓名
	 */
	private String customerName;
	/**
	 * 客户姓别  1:男   2:女  3:保密
	 */
	private Integer customerGender;
	/**
	 * 客户证件号
	 */
	private String customerCredentialNo;
	/**
	 * 客户证件类型
	 */
	private Integer customerCredentialType;
	/**
	 * 客户电话
	 */
	private String customerTelephone;
	/**
	 * 客户邮件
	 */
	private String customerEmail;
	/**
	 * 状态  1:未使用  2:已使用
	 */
	private Integer outriggerStatus;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
