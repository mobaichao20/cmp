package io.cmp.modules.wot.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.sys.controller.AbstractController;
import io.cmp.modules.wot.entity.SysNodeEntity;
import io.cmp.modules.wot.service.SysNodeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

//@CrossOrigin
@RestController
@RequestMapping("wot/node")
public class SysNodeController extends AbstractController {

    private SysNodeService sysNodeService;

    /**
     * 列表
     */
    @ApiOperation(value ="展示节点数据", httpMethod ="GET", notes ="")
    @RequestMapping("/list")
//    @RequiresPermissions("wot:node:list")
    public R AllList(@RequestParam Map<String, Object> params){

        PageUtils page =  sysNodeService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 删除节点
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("wot:node:delete")
    public R delete(@RequestBody String[] needDelwotp){
        sysNodeService.deleteBatch(needDelwotp);
        return R.ok();
    }

    /**
     * 保存节点
     * @param sysNodeEntity
     * @return
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wot:nodeLocus:delete")
    public R save(@RequestBody SysNodeEntity sysNodeEntity){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String dateNow = dateFormat.format(date);
        ParsePosition pos = new ParsePosition(0);
        sysNodeEntity.setCreateName(this.getUser().getUsername());                //获取当前用户
        sysNodeEntity.setCreateTime(dateFormat.parse(dateNow,pos));              //获取当前时间
        sysNodeService.save(sysNodeEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SysNodeEntity sysNodeEntity){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String dateNow = dateFormat.format(date);
        ParsePosition pos = new ParsePosition(0);
        sysNodeEntity.setUpdateName(this.getUser().getUsername());                //获取当前用户
        sysNodeEntity.setUpdateTime(dateFormat.parse(dateNow,pos));              //获取当前时间
        sysNodeService.updateById(sysNodeEntity);
        return R.ok();
    }

}
