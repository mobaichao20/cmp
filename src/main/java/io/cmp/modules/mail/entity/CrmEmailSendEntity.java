package io.cmp.modules.mail.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 邮件发送箱表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
@Data
@TableName("crm_email_send")
public class CrmEmailSendEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 发送地址
	 */
	private String sendAddress;
	/**
	 * 发送类型：1  及时发送   2 定时发送
	 */
	private String sendType;
	/**
	 * 收件人
	 */
	private String receiver;
	/**
	 * 邮件主题
	 */
	private String mailSubject;
	/**
	 * 抄送人
	 */
	private String mailCopy;
	/**
	 * 密送人
	 */
	private String securitySend;
	/**
	 * 邮件内容
	 */
	private String mailContent;
	/**
	 * 定时发送时间
	 */
	private Date scheduleTime;
	/**
	 * 发件人
	 */
	private String sender;
	/**
	 * 邮件类型：1、 草稿件  2、及时发送件  3、定时件  4、收件  5、 归档件
	 */
	private String mailType;
	/**
	 * 邮件模板ID
	 */
	private String moduleId;
	/**
	 * 是否有邮件附件0：否 1：是
	 */
	private String isMailAttachment;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 是否删除 0：否 1：是
	 */
	private String isDelete;
	/**
	 * 1：邮件附件，2：邮件正文
	 */
	@TableField(exist=false)
	private List<CrmEmailAttachmentEntity> mailResource;

}
