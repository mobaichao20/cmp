package io.cmp.modules.cam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动规则中间表
 * 
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@Data
@TableName("crm_campaign_rulefield")
public class CampaignRulefieldEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 活动ID
	 */
	@TableId
	private String campaignId;
	/**
	 * 规则ID
	 */
	private String rulefieldId;
	/**
	 * 规则优先级
	 */
	private Integer rulefieldLeave;
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
