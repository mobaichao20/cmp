package io.cmp.modules.cus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 数据追溯表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:57
 */
@Data
@TableName("crm_data_traceability")
public class DataTraceabilityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
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
	 * 开始修改间
	 */
	@TableField(exist=false)
	private String startUpdateTime;

	/**
	 * 结束修改间
	 */
	@TableField(exist=false)
	private String endUpdateTime;

}
