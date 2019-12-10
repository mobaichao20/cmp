package io.cmp.modules.mma.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 分类信息表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
@Data
@TableName("crm_classification_info")
public class CrmClassificationInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 分类名称
	 */
	private String classificationName;
	/**
	 * 信息类型 crm_customer_base:客户信息表 crm_customer_communication:客户信息通讯表
	 */
	private String infoType;
	/**
	 * 颜色
	 */
	private String color;
	/**
	 * 颜色代码
	 */
	private String colorCode;
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

}
