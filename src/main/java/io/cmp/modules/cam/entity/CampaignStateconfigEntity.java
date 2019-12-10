package io.cmp.modules.cam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动状态配置
 * 
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@Data
@TableName("crm_campaign_stateconfig")
public class CampaignStateconfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId
	private String stateconfigId;
	/**
	 * 活动ID
	 */
	private String campaignId;
	/**
	 * 结束码
	 */
	private String stateconfigName;
	/**
	 * 结束码类型（1：进行中,2：完成，3：结束，4注销，5：逾期再开）
	 */
	private Integer stateconfigType;
	/**
	 * 预约时限
	 */
	private String stateconfigNameDes;
	/**
	 * 时限单位（只针对时间单位选择）
	 */
	private Integer stateconfigUnit;
	/**
	 * 上级清单状态ID
	 */
	private String upperStateconfigId;
	/**
	 * 清单状态同层序号
	 */
	private Integer stateconfigSequence;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人ID
	 */
	private String createCode;
	/**
	 * 创建人姓名
	 */
	private String createName;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 修改人ID
	 */
	private String updateCode;
	/**
	 * 修改人姓名
	 */
	private String updateName;

}
