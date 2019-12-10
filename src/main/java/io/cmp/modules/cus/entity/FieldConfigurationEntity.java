package io.cmp.modules.cus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 字段配置表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
@Data
@TableName("crm_field_configuration")
public class FieldConfigurationEntity implements Serializable {
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
	 * 字段类型 1字符 2-数值 3-日期
	 */
	private String fieldType;
	/**
	 * 表类型
	 */
	private String type;
	/**
	 * 是否启用 0-否 1-是
	 */
	private String isEnable;
	/**
	 * 是否关键字 0-否1-是
	 */
	private String isKeyword;
	/**
	 * 是否查询条件 0-否 1-是
	 */
	private String isQuery;
	/**
	 * 表单类型 1-精确查询(文本) 2-模糊查询(文本)  3-下拉 4-单选 5-复选 6-区间查询左(文本)  7-区间查询右(文本) 
	 */
	private String formType;
	/**
	 * 基础数据类型
	 */
	private String baseDatatype;
	/**
	 * 是否列表字段 0-否 1-是
	 */
	private String isListfield;
	/**
	 * 查询序号
	 */
	private Integer queryNum;
	/**
	 * 列表序号
	 */
	private Integer listNum;
	/**
	 * 详情序号
	 */
	private Integer detailsNum;
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
