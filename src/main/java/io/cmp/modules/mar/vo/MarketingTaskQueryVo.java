package io.cmp.modules.mar.vo;

import io.cmp.modules.out.entity.OutriggerMessageEntity;
import lombok.Data;

import java.util.Date;

@Data
public class MarketingTaskQueryVo {
    private OutriggerMessageEntity outriggerMessageEntity;

    private String marketingId;
    private String outriggerId;
    private String bookingId;
    private String taskName;
    private String customerName;
    private String customerTelephone;
    private String customerCredentialNo;
    private Integer customerCredentialType;
    private String customerEmail;
    private Date beginTime;
    private Date endTime;
    private Integer endCode;
    private Integer taskType;
    private String userCode;
    private String organCode;
    private Integer source;
    private Integer firstStatus;
    private Integer secondStatus;
    private Integer thirdStatus;
    private Integer isBooking;
    private Integer bookingType;
    private Date bookingTime;
    private Date overdueTime;
    private Long batch;
    private String campaignId;
    private String description;
    private String createId;
    private Date createTime;
}
