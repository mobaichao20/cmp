package io.cmp.modules.tel.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 渠道管理电话工号表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-01 10:46:46
 */
@Data
@TableName("crm_channel_telephone_agent")
public class CrmChannelTelephoneAgentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 渠道管理id
	 */
	private String channelId;
	/**
	 * 话务工号
	 */
	private String telephoneAgent;
	/**
	 * 工号密码
	 */
	private String agentPassword;
	/**
	 * 分机号
	 */
	private String extension;
	/**
	/**
	 * 分机号密码
	 */
	private String extensionPassword;
	/**
	 * 技能组
	 */
	private String skillsGroup;
	/**
	 * 后续处理时长
	 */
	private Integer processingTime;
	/**
	 * 振铃次数
	 */
	private Integer ringingCount;
	/**
	 * 绑定坐席工号
	 */
	private String agentNumber;
	/**
	 * 绑定坐席名称
	 */
	private String agentName;
	/**
	 * 是否自动接听 0:否 1:是
	 */
	private String isAnswer;
	/**
	 * 是否自动挂机自动转满意度 0:否 1:是
	 */
	private String isHangupSatisfaction;
	/**
	 * 是否自动播报工号 0:否 1:是
	 */
	private String isBroadcasterAgent;
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

}
