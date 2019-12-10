package io.cmp.modules.cam.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.cam.entity.RulefieldEntity;
import io.cmp.modules.cam.service.RulefieldService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 规则主表
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@RestController
@RequestMapping("generator/rulefield")
public class RulefieldController {
    @Autowired
    private RulefieldService rulefieldService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:rulefield:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = rulefieldService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{rulefieldId}")
    @RequiresPermissions("generator:rulefield:info")
    public R info(@PathVariable("rulefieldId") String rulefieldId){
		RulefieldEntity rulefield = rulefieldService.getById(rulefieldId);

        return R.ok().put("rulefield", rulefield);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:rulefield:save")
    public R save(@RequestBody RulefieldEntity rulefield){
		rulefieldService.save(rulefield);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:rulefield:update")
    public R update(@RequestBody RulefieldEntity rulefield){
		rulefieldService.updateById(rulefield);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:rulefield:delete")
    public R delete(@RequestBody String[] rulefieldIds){
		rulefieldService.removeByIds(Arrays.asList(rulefieldIds));

        return R.ok();
    }

}
