package io.cmp.modules.mail.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.cmp.modules.sys.controller.AbstractController;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.cmp.modules.mail.entity.CrmEmailAccountEntity;
import io.cmp.modules.mail.service.CrmEmailAccountService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 邮件账号信息表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
@RestController
@RequestMapping("mail/crmemailaccount")
public class CrmEmailAccountController extends AbstractController {
    @Autowired
    private CrmEmailAccountService crmEmailAccountService;

    /**
     * 分页列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mail:crmemailaccount:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = crmEmailAccountService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/select")
    //@RequiresPermissions("mail:crmemailaccount:list")
    public R select(){
        return R.ok().put("page", crmEmailAccountService.list());
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("mail:crmemailaccount:info")
    public R info(@PathVariable("id") String id){
		CrmEmailAccountEntity crmEmailAccount = crmEmailAccountService.getById(id);
        crmEmailAccountService.testsss();
        return R.ok().put("crmEmailAccount", crmEmailAccount);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mail:crmemailaccount:save")
    public R save(@RequestBody CrmEmailAccountEntity crmEmailAccount){
		crmEmailAccountService.save(crmEmailAccount);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mail:crmemailaccount:update")
    public R update(@RequestBody CrmEmailAccountEntity crmEmailAccount){
		crmEmailAccountService.updateById(crmEmailAccount);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
   // @RequiresPermissions("mail:crmemailaccount:delete")
    public R delete(@RequestBody String[] ids){


        boolean flag=crmEmailAccountService.deleteByIds(ids);
		if(flag){
            return R.ok();
        }else{
            return R.error("删除失败");
        }


    }
    /**
     * 查询配置中的签名
     */

    @GetMapping("/sinature")
    // @RequiresPermissions("mail:crmemailaccount:delete")
    public R sinature() {

        String email = getUser().getEmail();
//      邮箱的配置
        CrmEmailAccountEntity mailConfig = crmEmailAccountService.getOne(new QueryWrapper<CrmEmailAccountEntity>()
                .eq(StringUtils.isNotBlank(email), "mail_address", email));
        return R.ok().put("CrmEmailAccountEntity", mailConfig);


    }


}
