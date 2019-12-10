package io.cmp.modules.mail.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 邮件附件表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
@Data
@TableName("crm_email_attachment")
@PropertySource(value = "classpath:application.yml")
public class CrmEmailAttachmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 附件名字
	 */
	private String name;
	/**
	 * 附件路径
	 */
	private String url;
	/**
	 * 绝对路径
	 */
	private String absolutePath;
	/**
	 * 附件创建时间
	 */
	private Date createTime;

	/**
	 * 邮件所属者
	 */
	private String	mrCreater;
    /**
     * 邮件类型(1:发件，2:收件)
     */
    private Integer mbMailType;

	/**
	 *附件
	 */
	@TableField(exist=false)
	private MultipartFile attachment;
	/**
	 * 上传图片的地址
	 */
	@TableField(exist=false)
	@Value("${com.upload.location}")
	private String  location;

	@TableField(exist=false)
	private String mail;

	private String moduleId;

}
