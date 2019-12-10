package io.cmp.modules.mma.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.cmp.modules.cus.entity.CustomerBaseEntity;
import io.cmp.modules.sys.entity.SysUserEntity;
import lombok.Data;

/**
 * 短信发送记录表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
@Data
@TableName("crm_sms_send_records")
public class CrmSmsSendRecordsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 模板id
	 */
	private String templateId;
	/**
	 * 客户id
	 */
	private String customerId;
	/**
	 * 发送号码
	 */
	private String sendNumber;
	/**
	 * 发送内容
	 */
	private String content;
	/**
	 * 状态 1:成功 2:失败 3:暂存
	 */
	private String status;
	/**
	 * 是否定时发送 0:否 1:是
	 */
	private String isDelivery;
	/**
	 * 预计发送时间
	 */
	private Date deliveryTime;
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
	 * 是否删除
	 */
	private String isDelete;

	/**
	 * 用户列表
	 */
	@TableField(exist=false)
	private List<CustomerBaseEntity> CustomerBaseEntityList;

	/**
	 * 模板列表
	 */
	@TableField(exist=false)
	private List<CrmMediaTemplateEntity> CrmMediaTemplateEntityList;

	/**
	 * 客户姓名
	 */
	private String customerName;

	/**
	 * 模板类型
	 */
	private String templateType;
	//开始时间
	@TableField(exist=false)
	private String startTime;
	//结束时间
	@TableField(exist=false)
	private String endTime;
	/**
	 * 实际发送时间
	 */
	private Date realSendTime;
	/**
	 * 发送批次标志
	 */
	private String batchFlag;
	/**
	 * 返回信息
	 */
	private String returnMsg;


}
