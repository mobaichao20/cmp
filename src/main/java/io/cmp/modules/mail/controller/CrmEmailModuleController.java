package io.cmp.modules.mail.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;
import io.cmp.modules.mail.service.CrmEmailAttachmentService;
import io.cmp.modules.sys.controller.AbstractController;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cmp.modules.mail.entity.CrmEmailModuleEntity;
import io.cmp.modules.mail.service.CrmEmailModuleService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 邮件模板表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
@RestController
@RequestMapping("mail/crmemailmodule")
public class CrmEmailModuleController extends AbstractController {
    @Autowired
    private CrmEmailModuleService crmEmailModuleService;
    @Autowired
    private CrmEmailAttachmentService crmEmailAttachmentService;
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mail:crmemailmodule:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = crmEmailModuleService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 查询出所有的模板
     */
    @RequestMapping("/select")
    //@RequiresPermissions("mail:crmemailmodule:list")
    public R select(){
      List<CrmEmailModuleEntity> list=  crmEmailModuleService.list();

        return R.ok().put("page", list);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("mail:crmemailmodule:info")
    public R info(@PathVariable("id") String id){
		CrmEmailModuleEntity crmEmailModule = crmEmailModuleService.getById(id);
//		通过模板ID查询附件
      List<CrmEmailAttachmentEntity> list=  crmEmailAttachmentService.list(new QueryWrapper<CrmEmailAttachmentEntity>().eq(StringUtils.isNotBlank(id),"module_id", id));
        crmEmailModule.setList(list);

        return R.ok().put("crmEmailModule", crmEmailModule);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mail:crmemailmodule:save")
    public R save(@RequestBody CrmEmailModuleEntity crmEmailModule){
		crmEmailModuleService.save(crmEmailModule);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mail:crmemailmodule:update")
    public R update(@RequestBody CrmEmailModuleEntity crmEmailModule){
		crmEmailModuleService.updateById(crmEmailModule);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mail:crmemailmodule:delete")
    public R delete(@RequestBody String[] ids){
		crmEmailModuleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    /**
     * 模板附件
     */
    @RequestMapping("/saveTemplate")
    //@RequiresPermissions("mail:crmemailmodule:save")
    public R saveTemplate(@RequestBody CrmEmailModuleEntity crmEmailModule){
        crmEmailModuleService.saveTemplate(crmEmailModule,getUser().getEmail());

        return R.ok();
    }

}
