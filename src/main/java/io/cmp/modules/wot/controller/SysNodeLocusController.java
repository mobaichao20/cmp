package io.cmp.modules.wot.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.sys.controller.AbstractController;
import io.cmp.modules.wot.entity.SysNodeLocusEntity;
import io.cmp.modules.wot.service.SysNodeLocusService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("wot/nodeLocus")
public class SysNodeLocusController  extends AbstractController {

    @Autowired
    private SysNodeLocusService sysNodeLocusService;

    /**
     * 列表
     */
    @ApiOperation(value ="展示节点轨迹数据", httpMethod ="GET", notes ="")
    @RequestMapping("/list")
    public R AllList(@RequestParam Map<String, Object> params){

        PageUtils page =  sysNodeLocusService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 删除节点轨迹
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("wot:nodeLocus:delete")
    public R delete(@RequestBody String[] needDel){
        Map<String,Object> map = new HashMap<>();
        map.put("locus_id",needDel[0]);
        sysNodeLocusService.removeByMap(map);
        return R.ok();
    }
    /**
     * 保存节点轨迹
     * @param sysNodeLocusEntity
     * @return
     */
    @RequestMapping("/save")
    //@RequiresPermissions("wot:nodeLocus:delete")
    public R save(@RequestBody SysNodeLocusEntity sysNodeLocusEntity){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String dateNow = dateFormat.format(date);
        ParsePosition pos = new ParsePosition(0);
        sysNodeLocusEntity.setCreateName(this.getUser().getUsername());                //获取当前用户
        sysNodeLocusEntity.setCreateTime(dateFormat.parse(dateNow,pos));              //获取当前时间
        sysNodeLocusService.save(sysNodeLocusEntity);
        return R.ok();
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SysNodeLocusEntity sysNodeLocusEntity){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String dateNow = dateFormat.format(date);
        ParsePosition pos = new ParsePosition(0);
        sysNodeLocusEntity.setUpdateName(this.getUser().getUsername());                //获取当前用户
        sysNodeLocusEntity.setUpdateTime(dateFormat.parse(dateNow,pos));              //获取当前时间
        sysNodeLocusService.updateById(sysNodeLocusEntity);
        return R.ok();
    }
}
