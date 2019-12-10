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

import io.cmp.modules.mma.entity.CrmMediaTemplateFileEntity;
import io.cmp.modules.mma.service.CrmMediaTemplateFileService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 多媒体模板附件表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
@RestController
@RequestMapping("mma/crmmediatemplatefile")
public class CrmMediaTemplateFileController {
    @Autowired
    private CrmMediaTemplateFileService crmMediaTemplateFileService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mma:crmmediatemplatefile:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = crmMediaTemplateFileService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("mma:crmmediatemplatefile:info")
    public R info(@PathVariable("id") String id){
		CrmMediaTemplateFileEntity crmMediaTemplateFile = crmMediaTemplateFileService.getById(id);

        return R.ok().put("crmMediaTemplateFile", crmMediaTemplateFile);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mma:crmmediatemplatefile:save")
    public R save(@RequestBody CrmMediaTemplateFileEntity crmMediaTemplateFile){
		crmMediaTemplateFileService.save(crmMediaTemplateFile);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mma:crmmediatemplatefile:update")
    public R update(@RequestBody CrmMediaTemplateFileEntity crmMediaTemplateFile){
		crmMediaTemplateFileService.updateById(crmMediaTemplateFile);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mma:crmmediatemplatefile:delete")
    public R delete(@RequestBody String[] ids){
		crmMediaTemplateFileService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
