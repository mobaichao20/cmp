package io.cmp.modules.mma.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cmp.modules.mma.entity.CrmMediaVariableEntity;
import io.cmp.modules.mma.service.CrmMediaVariableService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 多媒体变量表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
@RestController
@RequestMapping("mma/crmmediavariable")
public class CrmMediaVariableController {
    @Autowired
    private CrmMediaVariableService crmMediaVariableService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mma:crmmediavariable:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = crmMediaVariableService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("mma:crmmediavariable:info")
    public R info(@PathVariable("id") String id){
		CrmMediaVariableEntity crmMediaVariable = crmMediaVariableService.getById(id);

        return R.ok().put("crmMediaVariable", crmMediaVariable);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mma:crmmediavariable:save")
    public R save(@RequestBody CrmMediaVariableEntity crmMediaVariable){
		crmMediaVariableService.save(crmMediaVariable);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mma:crmmediavariable:update")
    public R update(@RequestBody CrmMediaVariableEntity crmMediaVariable){
		crmMediaVariableService.updateById(crmMediaVariable);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mma:crmmediavariable:delete")
    public R delete(@RequestBody String[] ids){
		crmMediaVariableService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
