package io.cmp.modules.mma.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 多媒体变量表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
@Data
@TableName("crm_media_variable")
public class CrmMediaVariableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 变量名称
	 */
	private String variableName;
	/**
	 * 变量存储字段
	 */
	private String variableField;
	/**
	 * 变量存储属性
	 */
	private String variableAttribute;
	/**
	 * 变量类型1:系统 2:常用 3:自定义
	 */
	private String variableType;
	/**
	 * 变量所属分类
	 */
	private String variableClassificationId;
	/**
	 * 引用次数
	 */
	private Integer referencesCount;
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
