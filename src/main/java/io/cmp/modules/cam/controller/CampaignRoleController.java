package io.cmp.modules.cam.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.cam.entity.CampaignRoleEntity;
import io.cmp.modules.cam.service.CampaignRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 活动权限中间表
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@RestController
@RequestMapping("generator/campaignrole")
public class CampaignRoleController {
    @Autowired
    private CampaignRoleService campaignRoleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:campaignrole:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = campaignRoleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{campaignId}")
    @RequiresPermissions("generator:campaignrole:info")
    public R info(@PathVariable("campaignId") String campaignId){
		CampaignRoleEntity campaignRole = campaignRoleService.getById(campaignId);

        return R.ok().put("campaignRole", campaignRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:campaignrole:save")
    public R save(@RequestBody CampaignRoleEntity campaignRole){
		campaignRoleService.save(campaignRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:campaignrole:update")
    public R update(@RequestBody CampaignRoleEntity campaignRole){
		campaignRoleService.updateById(campaignRole);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:campaignrole:delete")
    public R delete(@RequestBody String[] campaignIds){
		campaignRoleService.removeByIds(Arrays.asList(campaignIds));

        return R.ok();
    }

}
