package io.cmp.modules.out.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.out.entity.OutriggerMessageEntity;
import io.cmp.modules.out.service.OutriggerMessageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 外联数据表
 *
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@RestController
@RequestMapping("generator/outriggermessage")
public class OutriggerMessageController {
    @Autowired
    private OutriggerMessageService outriggerMessageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:outriggermessage:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = outriggerMessageService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{outriggerId}")
    @RequiresPermissions("generator:outriggermessage:info")
    public R info(@PathVariable("outriggerId") String outriggerId){
		OutriggerMessageEntity outriggerMessage = outriggerMessageService.getById(outriggerId);

        return R.ok().put("outriggerMessage", outriggerMessage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:outriggermessage:save")
    public R save(@RequestBody OutriggerMessageEntity outriggerMessage){
		outriggerMessageService.save(outriggerMessage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:outriggermessage:update")
    public R update(@RequestBody OutriggerMessageEntity outriggerMessage){
		outriggerMessageService.updateById(outriggerMessage);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:outriggermessage:delete")
    public R delete(@RequestBody String[] outriggerIds){
		outriggerMessageService.removeByIds(Arrays.asList(outriggerIds));

        return R.ok();
    }

}
