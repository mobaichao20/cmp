package io.cmp.modules.wot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 工单节点
 */
@Data
@TableName("sys_node")
public class SysNodeEntity {
    /**
     * 主键
     */
    @TableId("node_id")
    private String nodeId;
    /**
     * 节点名
     */
    private String nodeName;
    /**
     * 是否分支
     */
    private int isBranch;
    /**
     * 是否可以结束
     */
    private int isEnd;

    /**
     * 创建人
     */
    private String createName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateName;
    /**
     * 更新时间
     */
    private Date updateTime;


}
