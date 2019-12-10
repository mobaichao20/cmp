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

import io.cmp.modules.cus.entity.DataTraceabilityEntity;
import io.cmp.modules.cus.service.DataTraceabilityService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 数据追溯表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:57
 */
@RestController
@RequestMapping("cus/datatraceability")
public class DataTraceabilityController {
    @Autowired
    private DataTraceabilityService dataTraceabilityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("cus:datatraceability:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = dataTraceabilityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("cus:datatraceability:info")
    public R info(@PathVariable("id") String id){
		DataTraceabilityEntity dataTraceability = dataTraceabilityService.getById(id);

        return R.ok().put("dataTraceability", dataTraceability);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("cus:datatraceability:save")
    public R save(@RequestBody DataTraceabilityEntity dataTraceability){
		dataTraceabilityService.save(dataTraceability);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("cus:datatraceability:update")
    public R update(@RequestBody DataTraceabilityEntity dataTraceability){
		dataTraceabilityService.updateById(dataTraceability);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //s@RequiresPermissions("cus:datatraceability:delete")
    public R delete(@RequestBody String[] ids){
		dataTraceabilityService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
