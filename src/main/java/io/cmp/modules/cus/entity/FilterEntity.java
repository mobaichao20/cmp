package io.cmp.modules.cus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 过滤器表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
@Data
@TableName("crm_filter")
public class FilterEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 过滤器名称
	 */
	private String filterName;
	/**
	 * 过滤器描述
	 */
	private String filterDescription;
	/**
	 * 是否公有 0-否 1-是
	 */
	private String isPublic;
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
	 * 开始创建时间
	 */
	@TableField(exist=false)
	private String startCreateTime;

	/**
	 * 结束创建时间
	 */
	@TableField(exist=false)
	private String endCreateTime;

	@TableField(exist=false)
	private List<FilterFieldEntity> filterFieldList;

}
