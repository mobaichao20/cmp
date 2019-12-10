package io.cmp.modules.cus.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cmp.modules.cus.entity.FilterFieldEntity;
import io.cmp.modules.cus.service.FilterFieldService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 过滤器字段表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:57
 */
@RestController
@RequestMapping("cus/filterfield")
public class FilterFieldController {
    @Autowired
    private FilterFieldService filterFieldService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("cus:filterfield:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = filterFieldService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("cus:filterfield:info")
    public R info(@PathVariable("id") String id){
		FilterFieldEntity filterField = filterFieldService.getById(id);

        return R.ok().put("filterField", filterField);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("cus:filterfield:save")
    public R save(@RequestBody FilterFieldEntity filterField){
		filterFieldService.save(filterField);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("cus:filterfield:update")
    public R update(@RequestBody FilterFieldEntity filterField){
		filterFieldService.updateById(filterField);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("cus:filterfield:delete")
    public R delete(@RequestBody String[] ids){
		filterFieldService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
