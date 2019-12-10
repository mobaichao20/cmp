package io.cmp.modules.mma.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.cmp.modules.mma.vo.CsrFile;
import io.cmp.modules.sys.controller.AbstractController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cmp.modules.mma.entity.CrmMediaTemplateEntity;
import io.cmp.modules.mma.service.CrmMediaTemplateService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 多媒体模板表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
@RestController
@RequestMapping("mma/crmmediatemplate")
public class CrmMediaTemplateController extends AbstractController {
    @Autowired
    private CrmMediaTemplateService crmMediaTemplateService;
    private CsrFile csrFile;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mma:crmmediatemplate:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = crmMediaTemplateService.queryPage(params);
        return R.ok().put("page", page);
    }
    /**
     * 查询当前信息
     */
    @RequestMapping("/msg")
    public CsrFile findCsrById(){
        CsrFile csrFile = new CsrFile();
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        csrFile.setDate(dateFormat.format(date));
        csrFile.setOrgan(getUser().getUsername());
        csrFile.setLander(getUser().getRealname());
        Date dates = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        csrFile.setTime(sdf.format(dates));
        return csrFile;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("mma:crmmediatemplate:info")
    public R info(@PathVariable("id") String id){
        System.out.println("");
		CrmMediaTemplateEntity crmMediaTemplate = crmMediaTemplateService.getById(id);
        return R.ok().put("crmMediaTemplate", crmMediaTemplate);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mma:crmmediatemplate:save")
    public R save (@RequestBody CrmMediaTemplateEntity crmMediaTemplate){
        crmMediaTemplate.setCreateName(getUser().getRealname());
        crmMediaTemplate.setCreateCode(getUser().getUsername());
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        crmMediaTemplate.setCreateTime(dateFormat.format(date));
        crmMediaTemplateService.save(crmMediaTemplate);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mma:crmmediatemplate:update")
    public R update(@RequestBody CrmMediaTemplateEntity crmMediaTemplate){
        crmMediaTemplate.setUpdateName(getUser().getRealname());
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        crmMediaTemplate.setUpdateTime(dateFormat.format(date));
        crmMediaTemplate.setUpdateCode(getUser().getUsername());
		crmMediaTemplateService.updateById(crmMediaTemplate);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mma:crmmediatemplate:delete")
    public R delete(@RequestBody String[] ids){
        System.out.println("这里就是删除的信息啦！");
        System.out.println("传过来的ids的值是："+ids);
		crmMediaTemplateService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 删除单条数据
     */
    @RequestMapping("/deleteById/{id}")
    //@RequiresPermissions("mma:crmmediatemplate:info")
    public R deleteById(@PathVariable("id") String id){
  //      System.out.println("这里传进来的id为:"+id);
   //     System.out.println("111111111111111111111111111111111");
        boolean flag =  crmMediaTemplateService.deleteById(id);
  //      System.out.println("111111111111111111111111111-------flag = "+flag);

        if(flag){
            return R.ok();
        }else{
            return R.error(1,"删除失败");
        }
    }

}
