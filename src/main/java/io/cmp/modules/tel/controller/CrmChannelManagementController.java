package io.cmp.modules.tel.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cmp.modules.tel.entity.CrmChannelManagementEntity;
import io.cmp.modules.tel.service.CrmChannelManagementService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 渠道管理表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-01 10:46:46
 */
@RestController
@RequestMapping("tel/crmchannelmanagement")
public class CrmChannelManagementController {
    @Autowired
    private CrmChannelManagementService crmChannelManagementService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("tel:crmchannelmanagement:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = crmChannelManagementService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("tel:crmchannelmanagement:info")
    public R info(@PathVariable("id") String id){
		CrmChannelManagementEntity crmChannelManagement = crmChannelManagementService.getById(id);

        return R.ok().put("crmChannelManagement", crmChannelManagement);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("tel:crmchannelmanagement:save")
    public R save(@RequestBody CrmChannelManagementEntity crmChannelManagement){
		crmChannelManagementService.save(crmChannelManagement);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("tel:crmchannelmanagement:update")
    public R update(@RequestBody CrmChannelManagementEntity crmChannelManagement){
		crmChannelManagementService.updateById(crmChannelManagement);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("tel:crmchannelmanagement:delete")
    public R delete(@RequestBody String[] ids){
		crmChannelManagementService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
