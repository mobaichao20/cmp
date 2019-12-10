

package io.cmp.modules.sys.controller;

import com.alibaba.fastjson.JSON;
import io.cmp.common.annotation.SysLog;
import io.cmp.common.exception.RRException;
import io.cmp.common.utils.Constant;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.common.validator.Assert;
import io.cmp.common.validator.ValidatorUtils;
import io.cmp.common.validator.group.AddGroup;
import io.cmp.common.validator.group.UpdateGroup;
import io.cmp.modules.sys.entity.SysUserEntity;
import io.cmp.modules.sys.form.PasswordForm;
import io.cmp.modules.sys.service.SysUserRoleService;
import io.cmp.modules.sys.service.SysUserService;
import io.cmp.modules.tel.entity.CrmChannelTelephoneAgentEntity;
import io.cmp.modules.tel.service.CrmChannelTelephoneAgentService;
import io.cmp.modules.tel.vo.CrmChannelTelephoneAgentVo;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private CrmChannelTelephoneAgentService crmChannelTelephoneAgentService;

	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("page", page);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public R info(){
		return R.ok().put("user", getUser());
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@PostMapping("/password")
	public R password(@RequestBody PasswordForm form){
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");
		
		//sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		//sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();
				
		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);

		if(getUserId()==1L){
			return R.error("系统管理员不能修改密码");
		}
		if(getUserId()==2L){
			return R.error("系统管理员不能修改密码");
		}

		if(!flag){
			return R.error("原密码不正确");
		}
		
		return R.ok();
	}
	
	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.getById(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		
		//user.setCreateUserId(getUserId());
		sysUserService.saveUser(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){

		if(user.getUserId()==1L){
			return R.error("系统管理员不能修改");
		}
		if(user.getUserId()==2L){
			return R.error("系统管理员不能修改");
		}
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		//user.setCreateUserId(getUserId());
		sysUserService.update(user);
		
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		if(ArrayUtils.contains(userIds, 2L)){
			return R.error("系统管理员不能删除");
		}
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		
		return R.ok();
	}

	/**
	 * 绑定分机号
	 */
	@PostMapping("/bindingExtension")
	public R bindingExtension(@RequestBody SysUserEntity user){

		String isIsExtension=user.getIsExtension();
		logger.info("isIsExtension="+isIsExtension);

		if("1".equals(isIsExtension)) {

			CrmChannelTelephoneAgentEntity crmChannelTelephoneAgent2 = crmChannelTelephoneAgentService.queryrRandomTelephoneAgentInfo();
			//logger.info(JSON.toJSONString("crmChannelTelephoneAgent2=" + crmChannelTelephoneAgent2));

			if (crmChannelTelephoneAgent2 == null) {
				throw new RRException("已没有可分配的分机号");
			} else {

				SysUserEntity user2 =new SysUserEntity();
				user2.setUserId(user.getUserId());
				user2.setExtension(crmChannelTelephoneAgent2.getExtension());
				user2.setIsExtension("1");
				sysUserService.updateById(user2);

				CrmChannelTelephoneAgentEntity crmChannelTelephoneAgent3 = new CrmChannelTelephoneAgentEntity();
				crmChannelTelephoneAgent3.setId(crmChannelTelephoneAgent2.getId());
				crmChannelTelephoneAgent3.setAgentNumber(user.getUsername());
				crmChannelTelephoneAgent3.setAgentName(user.getRealname());
				crmChannelTelephoneAgentService.updateById(crmChannelTelephoneAgent3);

			}
		}
		else
		{
			SysUserEntity user2 =new SysUserEntity();
			user2.setUserId(user.getUserId());
			user2.setExtension("");
			user2.setIsExtension("0");
			sysUserService.updateById(user2);

			String extension =user.getExtension();
			String agentNumber =user.getUsername();
			logger.info("extension="+extension);
			logger.info("agentNumber="+agentNumber);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("extension",extension);
			params.put("agentNumber",agentNumber);

			CrmChannelTelephoneAgentVo crmChannelTelephoneAgentVo = crmChannelTelephoneAgentService.queryTelephoneAgentInfoByExtension(params);
			CrmChannelTelephoneAgentEntity crmChannelTelephoneAgent3 = new CrmChannelTelephoneAgentEntity();
			crmChannelTelephoneAgent3.setId(crmChannelTelephoneAgentVo.getId());
			crmChannelTelephoneAgent3.setAgentNumber("");
			crmChannelTelephoneAgent3.setAgentName("");
			crmChannelTelephoneAgentService.updateById(crmChannelTelephoneAgent3);
		}
		return R.ok();
	}



}
