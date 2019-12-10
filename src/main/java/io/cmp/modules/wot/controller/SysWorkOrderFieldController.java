package io.cmp.modules.wot.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.sys.controller.AbstractController;
import io.cmp.modules.wot.entity.SysWorkOrderFieldEntity;
import io.cmp.modules.wot.service.SysWorkOrderFieldService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("wot/woField")
public class SysWorkOrderFieldController extends AbstractController {

    @Autowired
    private SysWorkOrderFieldService sysWorkOrderFieldService;

    /**
     * 列表
     */
    @ApiOperation(value ="展示工单模板节点", httpMethod ="GET", notes ="")
    @RequestMapping("/list")
    public R AllList(@RequestParam Map<String, Object> params){

        PageUtils page =  sysWorkOrderFieldService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 保存
     * @param sysWorkOrderFieldEntity
     * @return
     */
    @RequestMapping("/save")
    public R save(@RequestBody SysWorkOrderFieldEntity sysWorkOrderFieldEntity){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String dateNow = dateFormat.format(date);
        ParsePosition pos = new ParsePosition(0);
        sysWorkOrderFieldEntity.setCreateName(this.getUser().getUsername());                //获取当前用户
        sysWorkOrderFieldEntity.setCreateTime(dateFormat.parse(dateNow,pos));              //获取当前时间
        sysWorkOrderFieldService.save(sysWorkOrderFieldEntity);
        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] needDel){
        Map<String,Object> map = new HashMap<>();
        map.put("sysfield_id",needDel[0]);
        sysWorkOrderFieldService.removeByMap(map);
        return R.ok();
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SysWorkOrderFieldEntity sysWorkOrderFieldEntity){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String dateNow = dateFormat.format(date);
        ParsePosition pos = new ParsePosition(0);
        sysWorkOrderFieldEntity.setUpdateName(this.getUser().getUsername());                //获取当前用户
        sysWorkOrderFieldEntity.setUpdateTime(dateFormat.parse(dateNow,pos));              //获取当前时间
        sysWorkOrderFieldService.updateById(sysWorkOrderFieldEntity);
        return R.ok();
    }
}
