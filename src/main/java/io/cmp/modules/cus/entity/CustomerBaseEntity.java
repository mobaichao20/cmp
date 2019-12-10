package io.cmp.modules.cus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 客户信息表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-02 15:13:26
 */
@Data
@TableName("crm_customer_base")
public class CustomerBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 姓
	 */
	private String surname;
	/**
	 * 姓名
	 */
	private String customerName;
	/**
	 * 英文名
	 */
	private String enName;
	/**
	 * 曾用名
	 */
	private String beforeName;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 身份证号码
	 */
	private String idNumber;
	/**
	 * 驾照号码
	 */
	private String driverNumber;
	/**
	 * 国籍
	 */
	private String nationality;
	/**
	 * 民族
	 */
	private String nation;
	/**
	 * 籍贯
	 */
	private String nativePlace;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 生日
	 */

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	/**
	 * 身高
	 */
	private Integer height;
	/**
	 * 体重
	 */
	private Integer weight;
	/**
	 * 血型
	 */
	private String bloodType;
	/**
	 * 政治面貌
	 */
	private String politicalAppearance;
	/**
	 * 生育
	 */
	private String birth;
	/**
	 * 工龄
	 */
	private Integer workingYears;
	/**
	 * 婚姻状况
	 */
	private String maritalStatus;
	/**
	 * 客户学历
	 */
	private String customerEducation;
	/**
	 *头像地址
	 */
	private String headUrl;
	/**
	 * 公司
	 */
	private String company;
	/**
	 * 部门
	 */
	private String department;
	/**
	 * 职务
	 */
	private String duty;
	/**
	 * 所属坐席
	 */
	private String belongCode;
	/**
	 * 坐席所属机构
	 */
	private String belongInstitutions;
	/**
	 * 客户备注
	 */
	private String customerNotes;
	/**
	 * 是否有效 0-否 1-是
	 */
	private String isEffective;
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
	 * 开始修改时间
	 */
	@TableField(exist=false)
	private String startUpdateTime;

	/**
	 * 结束修改时间
	 */
	@TableField(exist=false)
	private String endUpdateTime;

	/**
	 * 是否删除 0：否 1：是
	 */
	private String isDelete;
}
