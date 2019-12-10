package io.cmp.modules.cus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 客户信息通讯表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-02 15:13:26
 */
@Data
@TableName("crm_customer_communication")
public class CustomerCommunicationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 客户id
	 */
	private String customerId;
	/**
	 * 通讯类型 1-移动电话 2-固定电话 3-qq 4-微信 5-邮件 6-传真 7-通讯地址  
	 */
	private String communicationType;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 区号
	 */
	private String areaCode;
	/**
	 * 座机
	 */
	private String phone;
	/**
	 * 分机号
	 */
	private String extensionNum;
	/**
	 * qq
	 */
	private String qq;
	/**
	 * 微信
	 */
	private String weixin;
	/**
	 * 电子邮件
	 */
	private String email;
	/**
	 * 传真
	 */
	private String fax;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区
	 */
	private String area;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 邮政编码
	 */
	private String postCode;
	/**
	 * 是否常用联系方式 0-否 1-是
	 */
	private String isContact;
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
	 * 开始修改间
	 */
	@TableField(exist=false)
	private String startUpdateTime;

	/**
	 * 结束修改间
	 */
	@TableField(exist=false)
	private String endUpdateTime;

	/**
	 * 是否删除 0：否 1：是
	 */
	private String isDelete;

}
