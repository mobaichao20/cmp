package io.cmp.modules.tel.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Data
public class CrmChannelTelephoneAgentVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 渠道管理id
     */
    private String channelId;
    /**
     * 话务工号
     */
    private String telephoneAgent;
    /**
     * 工号密码
     */
    private String agentPassword;
    /**
     * 分机号
     */
    private String extension;
    /**
     /**
     * 分机号密码
     */
    private String extensionPassword;
    /**
     * 技能组
     */
    private String skillsGroup;
    /**
     * 后续处理时长
     */
    private Integer processingTime;
    /**
     * 振铃次数
     */
    private Integer ringingCount;
    /**
     * 绑定坐席工号
     */
    private String agentNumber;
    /**
     * 绑定坐席名称
     */
    private String agentName;
    /**
     * 是否自动接听 0:否 1:是
     */
    private String isAnswer;
    /**
     * 是否自动挂机自动转满意度 0:否 1:是
     */
    private String isHangupSatisfaction;
    /**
     * 是否自动播报工号 0:否 1:是
     */
    private String isBroadcasterAgent;

    /**
     * 话务品牌
     */
    private String trafficBrand;
    /**
     * CTI地址主
     */
    private String ctiAddressMaster;
    /**
     * CTI地址从
     */
    private String ctiAddressSlave;
    /**
     * 满意度节点号
     */
    private String satisfactionNodeNumber;
    /**
     * 播报工号节点
     */
    private String broadcasterNode;
    /**
     * 每日工作时间开始
     */
    private Time workingHoursBegin;
    /**
     * 每日工作时间结束
     */
    private Time workingHoursEnd;
    /**
     * 是否启用黑名单 0:否 1:是
     */
    private String isBlacklist;

}
