package io.cmp.modules.mail.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 邮件账号信息表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
@Data
@TableName("crm_email_account")
public class CrmEmailAccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String configId;
	/**
	 * 邮箱地址
	 */
	private String mailAddress;
	/**
	 * 邮箱密码
	 */
	private String password;
	/**
	 * 显示名称
	 */
	private String displayedName;
	/**
	 * 发送名称
	 */
	private String sendName;
	/**
	 * 邮箱设置
	 */
	private String mailSet;
	/**
	 * 定时收取
	 */
	private Date scheduleGet;
	/**
	 * 邮箱类型
	 */
	private String mailType;
	/**
	 * 收件服务器
	 */
	private String receiveMailServer;
	/**
	 * 收件服务器端口
	 */
	private String receiveServerPort;
	/**
	 * 发件服务器
	 */
	private String sendMailServer;
	/**
	 * 发件服务器端口
	 */
	private String sendServerPort;
	/**
	 * 是否加密传输：  0  否，1  是，     如果服务器支持，就使用STARTTLS加密传输
	 */
	private String isSecretySend;
	/**
	 * 是否永久保存：  0  否，1  是，     服务器数据备份，在服务器上永久保存
	 */
	private String isPermanentSave;
	/**
	 * 是否进行发件服务器身份验证: 0  否   1  是
	 */
	private String isVertify;
	/**
	 * 邮件发送服务器验证用户名
	 */
	private String mcSmtpName;
	/**
	 * 邮件发送服务器验证密码
	 */
	private String mcSmtpPwd;

	/**
	 * 签名
	 */
	private String signature;
	/**
	 * 创建人代码
	 */
	private String createCode;
	/**
	 * 创建人
	 */
	private String createName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改人代码
	 */
	private String updateCode;
	/**
	 * 修改人
	 */
	private String updateName;
	/**
	 * 修改时间
	 */
	private Date updateTime;


}
