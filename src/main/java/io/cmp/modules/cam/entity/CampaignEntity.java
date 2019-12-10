package io.cmp.modules.cam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动主表
 * 
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@Data
@TableName("crm_campaign")
public class CampaignEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String campaignId;
	/**
	 * 活动代码
	 */
	private String campaignCode;
	/**
	 * 活动名称
	 */
	private String campaignName;
	/**
	 * 业务类型
	 */
	private Integer businessType;
	/**
	 * 外拨方式
	 */
	private String outgoingMode;
	/**
	 * 预览时长
	 */
	private Date previewDuration;
	/**
	 * 单号最大拨打次数
	 */
	private Integer maxDialNumber;
	/**
	 * 最大振铃时长
	 */
	private Date maxRingingNumber;
	/**
	 * 拨号模式
	 */
	private String dialMode;
	/**
	 * 逾期提醒
	 */
	private Integer overdueReminder;
	/**
	 * 数据来源
	 */
	private String dataSources;
	/**
	 * 拨号日程起
	 */
	private Date dialScheduleBegin;
	/**
	 * 拨号日程止
	 */
	private Date dialScheduleEnd;
	/**
	 * 活动有效期起
	 */
	private Date termOfValidityBegin;
	/**
	 * 活动有效期止
	 */
	private Date termOfValidityEnd;
	/**
	 * 当日任务最大值
	 */
	private Integer maxTaskNum;
	/**
	 * 当日处理目标值
	 */
	private Integer excuteTaskNumber;
	/**
	 * 当日完成目标值
	 */
	private Integer finishTarget;
	/**
	 * 活动状态
	 */
	private Integer status;
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
	/**
	 * 外联表表名
	 */
	private String outriggerTableName;
	/**
	 * 外联表主键列名
	 */
	private String outriggerIdName;
	/**
	 * 查询外联表SQL
	 */
	private String outriggerQuerySql;

}
