package io.cmp.modules.mail.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 邮件模板表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
@Data
@TableName("crm_email_module")
public class CrmEmailModuleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 模板名称
	 */
	private String moduleName;
	/**
	 * 模板内容
	 */
	private String moduleContent;
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
	/**
	 * 是否启用:0 否 1 是
	 */
	private String isStart;
	/**
	 * 是否删除 0：否 1：是
	 */
	private String isDelete;
	/**
	 * 附件列表
	 */
	@TableField(exist=false)
	List<CrmEmailAttachmentEntity> list;

	/**
	 * 抄送人
	 */
	private String mailCopy;
	/**
	 * 密送人
	 */
	private String securitySend;
	/**
	 * 收件人
	 */
	private String receiver;
	/**
	 * 邮件主题
	 */
	private String mailSubject;
	/**
	 * 模板类型 1邮件  2 短信
	 */
	private String templateType;



}
