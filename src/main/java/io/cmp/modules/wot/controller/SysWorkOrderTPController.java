package io.cmp.modules.wot.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.sys.controller.AbstractController;
import io.cmp.modules.wot.entity.SysWorkOrderTPEntity;
import io.cmp.modules.wot.service.SysWorkOrderTPService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

//@CrossOrigin
@RestController
@RequestMapping("wot/wotTP")
public class SysWorkOrderTPController extends AbstractController {

    @Autowired
    private SysWorkOrderTPService sysWorkOrderTPService;


    /**
     * 列表
     */
    @ApiOperation(value ="展示工单模板数据", httpMethod ="GET", notes ="")
    @RequestMapping("/list")
//    @RequiresPermissions("wot:wotTP:list")
    public R AllList(@RequestParam Map<String, Object> params){

            PageUtils page =  sysWorkOrderTPService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 删除模板
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("wot:wotTP:delete")
    public R delete(@RequestBody String[] needDelwotp){
        sysWorkOrderTPService.deleteBatch(needDelwotp);

        return R.ok();
    }

    /**
     * 保存
     * 入参：
     * 返回： ok
     */
    @RequestMapping("/save")
   // @RequiresPermissions("wot:wotTP:save")
    public R save(@RequestBody SysWorkOrderTPEntity sysWorkOrderTPEntity){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String dateNow = dateFormat.format(date);
        ParsePosition pos = new ParsePosition(0);
        sysWorkOrderTPEntity.setCreateName(this.getUser().getUsername());                //获取当前用户
        sysWorkOrderTPEntity.setCreateTime(dateFormat.parse(dateNow,pos));              //获取当前时间

        sysWorkOrderTPService.save(sysWorkOrderTPEntity);

        return R.ok();
    }

    /**
     * 修改模板
     */
    @RequestMapping("/update")
    //@RequiresPermissions("wot:wotTP:update")
    public R update(@RequestBody SysWorkOrderTPEntity syswotpEntity){

        sysWorkOrderTPService.updateById(syswotpEntity);
        return R.ok();
    }

    /**
     * 随机一个新的模板号
     */
    @RequestMapping("/getNew")
    public R getNewId(){
        String syswotpId = randomId();
        List<SysWorkOrderTPEntity> list = sysWorkOrderTPService.list();
       for (int i = 0;i<list.size();i++){
           if(list.get(i).getSyswotpId().equals(syswotpId)){
               syswotpId = randomId();
               i=-1;
           }
       }

        return R.ok().put("syswotpId",syswotpId);
    }

    /**
     * 获取六位随机数
     * @return String类型 结果
     */
    public String randomId(){
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }
        return result;
    }
}
