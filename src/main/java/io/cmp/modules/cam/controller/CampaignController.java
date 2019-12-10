package io.cmp.modules.cam.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.cam.entity.CampaignEntity;
import io.cmp.modules.cam.service.CampaignService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 活动主表
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@RestController
@RequestMapping("generator/campaign")
public class CampaignController {
    @Autowired
    private CampaignService campaignService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:campaign:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = campaignService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{campaignId}")
    @RequiresPermissions("generator:campaign:info")
    public R info(@PathVariable("campaignId") String campaignId){
		CampaignEntity campaign = campaignService.getById(campaignId);

        return R.ok().put("campaign", campaign);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:campaign:save")
    public R save(@RequestBody CampaignEntity campaign){
		campaignService.save(campaign);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:campaign:update")
    public R update(@RequestBody CampaignEntity campaign){
		campaignService.updateById(campaign);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:campaign:delete")
    public R delete(@RequestBody String[] campaignIds){
		campaignService.removeByIds(Arrays.asList(campaignIds));

        return R.ok();
    }

}
