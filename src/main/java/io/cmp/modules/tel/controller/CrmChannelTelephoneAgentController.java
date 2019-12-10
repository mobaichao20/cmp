package io.cmp.modules.tel.controller;

import java.util.Arrays;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import io.cmp.common.exception.RRException;
import io.cmp.modules.sys.entity.SysUserEntity;
import io.cmp.modules.sys.service.SysUserService;
import io.cmp.modules.tel.vo.CrmChannelTelephoneAgentVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cmp.modules.tel.entity.CrmChannelTelephoneAgentEntity;
import io.cmp.modules.tel.service.CrmChannelTelephoneAgentService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 渠道管理电话工号表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-01 10:46:46
 */
@RestController
@RequestMapping("tel/crmchanneltelephoneagent")
public class CrmChannelTelephoneAgentController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CrmChannelTelephoneAgentService crmChannelTelephoneAgentService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("tel:crmchanneltelephoneagent:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = crmChannelTelephoneAgentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("tel:crmchanneltelephoneagent:info")
    public R info(@PathVariable("id") String id){
		CrmChannelTelephoneAgentEntity crmChannelTelephoneAgent = crmChannelTelephoneAgentService.getById(id);

        return R.ok().put("crmChannelTelephoneAgent", crmChannelTelephoneAgent);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("tel:crmchanneltelephoneagent:save")
    public R save(@RequestBody CrmChannelTelephoneAgentEntity crmChannelTelephoneAgent){
		crmChannelTelephoneAgentService.save(crmChannelTelephoneAgent);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("tel:crmchanneltelephoneagent:update")
    public R update(@RequestBody CrmChannelTelephoneAgentEntity crmChannelTelephoneAgent){
		crmChannelTelephoneAgentService.updateById(crmChannelTelephoneAgent);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("tel:crmchanneltelephoneagent:delete")
    public R delete(@RequestBody String[] ids){
		crmChannelTelephoneAgentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 通过分机号息获取渠道管理电话工号信息
     */
    @RequestMapping("/queryTelephoneAgentInfoByExtension")
    public R queryTelephoneAgentInfoByExtension(@RequestParam Map<String, Object> params){
        CrmChannelTelephoneAgentVo crmChannelTelephoneAgentVo = crmChannelTelephoneAgentService.queryTelephoneAgentInfoByExtension(params);
        return R.ok().put("crmChannelTelephoneAgentVo", crmChannelTelephoneAgentVo);
    }


}
