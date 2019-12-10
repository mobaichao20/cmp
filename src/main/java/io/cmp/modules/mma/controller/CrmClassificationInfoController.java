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

import io.cmp.modules.mma.entity.CrmClassificationInfoEntity;
import io.cmp.modules.mma.service.CrmClassificationInfoService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 分类信息表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
@RestController
@RequestMapping("mma/crmclassificationinfo")
public class CrmClassificationInfoController {
    @Autowired
    private CrmClassificationInfoService crmClassificationInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mma:crmclassificationinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = crmClassificationInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("mma:crmclassificationinfo:info")
    public R info(@PathVariable("id") String id){
		CrmClassificationInfoEntity crmClassificationInfo = crmClassificationInfoService.getById(id);

        return R.ok().put("crmClassificationInfo", crmClassificationInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mma:crmclassificationinfo:save")
    public R save(@RequestBody CrmClassificationInfoEntity crmClassificationInfo){
		crmClassificationInfoService.save(crmClassificationInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mma:crmclassificationinfo:update")
    public R update(@RequestBody CrmClassificationInfoEntity crmClassificationInfo){
		crmClassificationInfoService.updateById(crmClassificationInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mma:crmclassificationinfo:delete")
    public R delete(@RequestBody String[] ids){
		crmClassificationInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
