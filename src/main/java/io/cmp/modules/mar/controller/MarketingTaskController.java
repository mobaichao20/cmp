package io.cmp.modules.mar.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.mar.entity.MarketingTaskEntity;
import io.cmp.modules.mar.service.MarketingTaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 营销任务主表
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@RestController
@RequestMapping("generator/marketingtask")
public class MarketingTaskController {
    @Autowired
    private MarketingTaskService marketingTaskService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:marketingtask:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = marketingTaskService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{marketingId}")
    @RequiresPermissions("generator:marketingtask:info")
    public R info(@PathVariable("marketingId") String marketingId){
		MarketingTaskEntity marketingTask = marketingTaskService.getById(marketingId);

        return R.ok().put("marketingTask", marketingTask);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:marketingtask:save")
    public R save(@RequestBody MarketingTaskEntity marketingTask){
		marketingTaskService.save(marketingTask);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:marketingtask:update")
    public R update(@RequestBody MarketingTaskEntity marketingTask){
		marketingTaskService.updateById(marketingTask);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:marketingtask:delete")
    public R delete(@RequestBody String[] marketingIds){
		marketingTaskService.removeByIds(Arrays.asList(marketingIds));

        return R.ok();
    }

}
