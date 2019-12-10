package io.cmp.modules.cus.controller;

import java.util.*;

import io.cmp.modules.cus.entity.FilterEntity;
import io.cmp.modules.cus.entity.FilterFieldEntity;
import io.cmp.modules.cus.service.FilterFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cmp.modules.cus.service.FilterService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 过滤器表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
@RestController
@RequestMapping("cus/filter")
public class FilterController {
    @Autowired
    private FilterService filterService;
    @Autowired
    private FilterFieldService filterFieldService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("cus:filter:list")
    public R list(@RequestParam Map<String, Object> params){
       // System.out.println("startdate========================="+params.get("startCreateTime"));
        //System.out.println("enddate========================="+params.get("endCreateTime"));

        PageUtils page = filterService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("cus:filte:info")
    public R info(@PathVariable("id") String id){
		FilterEntity filter = filterService.getById(id);
        List<FilterFieldEntity> filterFieldList=filterFieldService.queryFilterFieldList(id);
        filter.setFilterFieldList(filterFieldList);
        return R.ok().put("filter", filter);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("cus:filter:save")
    public R save(@RequestBody FilterEntity filter){
        filter.setCreateTime(new Date());
		//filterService.save(filter);
        filterService.saveFilter(filter);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("cus:filter:update")
    public R update(@RequestBody FilterEntity filter){
		//filterService.updateById(filter);
        filterService.updateFilter(filter);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("cus:filter:delete")
    public R delete(@RequestBody String[] ids){
		//filterService.removeByIds(Arrays.asList(ids));
        filterService.deleteBatch(ids);
        return R.ok();
    }

}
