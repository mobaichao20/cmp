package io.cmp.modules.wot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_workorder_field")
public class SysWorkOrderFieldEntity {
    /**
     * 主键
     */
    @TableId("sysfield_id")
    private int sysfieldId;
    /**
     * 模板id
     */
    private String syswotpId;
    /**
     * 字段名
     */
    private String fieldName;
    /**
     *是否必填
     */
    private int isMust;
    /**
     * 字段类型
     */
    private int fieldType;
    /**
     * 验证类型
     */
    private int testType;
    /**
     * 字段长度
     */
    private int fieldLong;
    /**
     * 查询条件
     */
    private  String fieldQuery;
    /**
     * 提示信息
     */
    private String fieldTip;
    /**
     * 是否有源
     */
    private int isSource;
    /**
     * 下拉级联标识
     */
    private int casMarker;
    /**
     * 下拉默认值
     */
    private String dropdownDefault;
    /**
     * 有源时sql语句
     */
    private String sourceSql;
    /**
     * 创建人
     */
    private String createName;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateName;
    /**
     *更新时间
     */
    private Date updateTime;

}
