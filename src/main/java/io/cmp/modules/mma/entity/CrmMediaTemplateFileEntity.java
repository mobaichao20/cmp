package io.cmp.modules.mma.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 多媒体模板附件表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
@Data
@TableName("crm_media_template_file")
public class CrmMediaTemplateFileEntity implements Serializable {
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
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件名称地址
	 */
	private String fileUrl;

}
