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

import io.cmp.modules.cus.entity.FieldConfigurationEntity;
import io.cmp.modules.cus.service.FieldConfigurationService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 字段配置表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
@RestController
@RequestMapping("cus/fieldconfiguration")
public class FieldConfigurationController {
    @Autowired
    private FieldConfigurationService fieldConfigurationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("cus:fieldconfiguration:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = fieldConfigurationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("cus:fieldconfiguration:info")
    public R info(@PathVariable("id") String id){
		FieldConfigurationEntity fieldConfiguration = fieldConfigurationService.getById(id);

        return R.ok().put("fieldConfiguration", fieldConfiguration);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("cus:fieldconfiguration:save")
    public R save(@RequestBody FieldConfigurationEntity fieldConfiguration){
		fieldConfigurationService.save(fieldConfiguration);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("cus:fieldconfiguration:update")
    public R update(@RequestBody FieldConfigurationEntity fieldConfiguration){
		fieldConfigurationService.updateById(fieldConfiguration);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("cus:fieldconfiguration:delete")
    public R delete(@RequestBody String[] ids){
		fieldConfigurationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
