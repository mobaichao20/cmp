package io.cmp.modules.wot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 工单节点轨迹
 */
@Data
@TableName("sys_node_locus")
public class SysNodeLocusEntity {
    /**
     * 主键
     */
    @TableId("locus_id")
    private String locusId;
    /**
     * 模板id
     */
    private String syswotpId;
    /**
     * 模板名
     */
    @TableField(exist=false)
    private String syswotpName;
    /**
     * 当前节点
     */
    private String startId;
    /**
     * 当前节点名
     */
    @TableField(exist=false)
    private String startName;
    /**
     * 下一个节点
     */
    private String nextId;
    /**
     * 下一个节点名
     */
    @TableField(exist=false)
    private String nextName;
    /**
     * 顺序
     */
    private int sequence;
    /**
     * 是否分支
     */
    private int isBranch;
    /**
     * 创建人
     */
    private String createName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新人
     */
    private String updateName;

}
