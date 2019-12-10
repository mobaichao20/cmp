package io.cmp.modules.cam.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.cam.entity.CampaignRulefieldEntity;
import io.cmp.modules.cam.service.CampaignRulefieldService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 活动规则中间表
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@RestController
@RequestMapping("generator/campaignrulefield")
public class CampaignRulefieldController {
    @Autowired
    private CampaignRulefieldService campaignRulefieldService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:campaignrulefield:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = campaignRulefieldService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{campaignId}")
    @RequiresPermissions("generator:campaignrulefield:info")
    public R info(@PathVariable("campaignId") String campaignId){
		CampaignRulefieldEntity campaignRulefield = campaignRulefieldService.getById(campaignId);

        return R.ok().put("campaignRulefield", campaignRulefield);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:campaignrulefield:save")
    public R save(@RequestBody CampaignRulefieldEntity campaignRulefield){
		campaignRulefieldService.save(campaignRulefield);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:campaignrulefield:update")
    public R update(@RequestBody CampaignRulefieldEntity campaignRulefield){
		campaignRulefieldService.updateById(campaignRulefield);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:campaignrulefield:delete")
    public R delete(@RequestBody String[] campaignIds){
		campaignRulefieldService.removeByIds(Arrays.asList(campaignIds));

        return R.ok();
    }

}
