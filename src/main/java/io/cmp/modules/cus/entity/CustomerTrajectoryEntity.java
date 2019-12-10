package io.cmp.modules.cus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 客户轨迹表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
@Data
@TableName("crm_customer_trajectory")
public class CustomerTrajectoryEntity implements Serializable {
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
	 * 轨迹名称
	 */
	private String trajectoryName;
	/**
	 * 轨迹内容
	 */
	private String trajectoryContent;
	/**
	 * 轨迹类型
	 */
	private String trajectoryType;
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
	 * 客户轨迹子表列表
	 */
	@TableField(exist=false)
	private List<CustomerTrajectorySonEntity> customerTrajectorySonEntityList;

}
