package io.cmp.modules.mma.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 多媒体模板表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
@Data
@TableName("crm_media_template")
public class CrmMediaTemplateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 模板名称
	 */
	private String templateName;
	/**
	 * 模板类型 1:短信 2:邮件
	 */
	private String templateType;
	/**
	 * 是否启用 0:否 1:是(模板状态)
	 */
	private String isEnabled;
	/**
	 * 模板正文
	 */
	private String templateContent;
	/**
	 * 是否有附件 0:否 1:是
	 */
	private String isFile;
	/**
	 * 备注
	 */
	private String remark;
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
	private String createTime;
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
	private String updateTime;

}
