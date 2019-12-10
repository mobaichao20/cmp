package io.cmp.modules.cam.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.cam.entity.CampaignStateconfigEntity;
import io.cmp.modules.cam.service.CampaignStateconfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 活动状态配置
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@RestController
@RequestMapping("generator/campaignstateconfig")
public class CampaignStateconfigController {
    @Autowired
    private CampaignStateconfigService campaignStateconfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:campaignstateconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = campaignStateconfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{stateconfigId}")
    @RequiresPermissions("generator:campaignstateconfig:info")
    public R info(@PathVariable("stateconfigId") String stateconfigId){
		CampaignStateconfigEntity campaignStateconfig = campaignStateconfigService.getById(stateconfigId);

        return R.ok().put("campaignStateconfig", campaignStateconfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:campaignstateconfig:save")
    public R save(@RequestBody CampaignStateconfigEntity campaignStateconfig){
		campaignStateconfigService.save(campaignStateconfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:campaignstateconfig:update")
    public R update(@RequestBody CampaignStateconfigEntity campaignStateconfig){
		campaignStateconfigService.updateById(campaignStateconfig);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:campaignstateconfig:delete")
    public R delete(@RequestBody String[] stateconfigIds){
		campaignStateconfigService.removeByIds(Arrays.asList(stateconfigIds));

        return R.ok();
    }

}
