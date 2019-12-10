package io.cmp.modules.mar.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.mar.entity.MarketingTaskLogEntity;
import io.cmp.modules.mar.service.MarketingTaskLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 营销任务操作历史表
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@RestController
@RequestMapping("generator/marketingtasklog")
public class MarketingTaskLogController {
    @Autowired
    private MarketingTaskLogService marketingTaskLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:marketingtasklog:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = marketingTaskLogService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{logId}")
    @RequiresPermissions("generator:marketingtasklog:info")
    public R info(@PathVariable("logId") String logId){
		MarketingTaskLogEntity marketingTaskLog = marketingTaskLogService.getById(logId);

        return R.ok().put("marketingTaskLog", marketingTaskLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:marketingtasklog:save")
    public R save(@RequestBody MarketingTaskLogEntity marketingTaskLog){
		marketingTaskLogService.save(marketingTaskLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:marketingtasklog:update")
    public R update(@RequestBody MarketingTaskLogEntity marketingTaskLog){
		marketingTaskLogService.updateById(marketingTaskLog);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:marketingtasklog:delete")
    public R delete(@RequestBody String[] logIds){
		marketingTaskLogService.removeByIds(Arrays.asList(logIds));

        return R.ok();
    }

}
