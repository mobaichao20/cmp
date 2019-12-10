package io.cmp.modules.specialList.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 特殊名单表
 */
@Data
@TableName("crm_special_list")
public class SpecialListEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 名单ID
     */
    @TableId("special_list_id")
    private String id;
    /**
     * 客户
     */
    private String customer;
    /**
     * 名单类型
     */
    private String listType;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private String modifier;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 审核人
     */
    private String verifier;
    /**
     * 审核时间
     */
    private Date verifierTime;
    /**
     * 名单状态
     */
    private String listStatus;
    /**
     * 失效时间
     */
    private String failureTime;
}
