package io.cmp.modules.cam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 规则主表
 * 
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@Data
@TableName("crm_rulefield")
public class RulefieldEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String rulefieldId;
	/**
	 * 规则代码
	 */
	private String rulefieldCode;
	/**
	 * 规则名称
	 */
	private String rulefieldName;
	/**
	 * 规则描述
	 */
	private String rulefieldDes;
	/**
	 * 规则状态
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

}
