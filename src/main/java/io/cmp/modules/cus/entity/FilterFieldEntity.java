package io.cmp.modules.cus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 过滤器字段表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:57
 */
@Data
@TableName("crm_filter_field")
public class FilterFieldEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 过滤器id
	 */
	private String filterId;
	/**
	 * 数据名称
	 */
	private String dataName;
	/**
	 * 数据字段属性
	 */
	private String dataAttribute;
	/**
	 * 显示名称
	 */
	private String displayName;
	/**
	 * 表类型
	 */
	private String type;
	/**
	 * 数据值
	 */
	private String dataValue;

}
