package io.cmp.modules.cus.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cmp.modules.cus.entity.CustomerTrajectorySonEntity;
import io.cmp.modules.cus.service.CustomerTrajectorySonService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 客户轨迹子表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
@RestController
@RequestMapping("cus/customertrajectoryson")
public class CustomerTrajectorySonController {
    @Autowired
    private CustomerTrajectorySonService customerTrajectorySonService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("cus:customertrajectoryson:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = customerTrajectorySonService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("cus:customertrajectoryson:info")
    public R info(@PathVariable("id") String id){
		CustomerTrajectorySonEntity customerTrajectorySon = customerTrajectorySonService.getById(id);

        return R.ok().put("customerTrajectorySon", customerTrajectorySon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("cus:customertrajectoryson:save")
    public R save(@RequestBody CustomerTrajectorySonEntity customerTrajectorySon){
        customerTrajectorySon.setCreateTime(new Date());
		customerTrajectorySonService.save(customerTrajectorySon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("cus:customertrajectoryson:update")
    public R update(@RequestBody CustomerTrajectorySonEntity customerTrajectorySon){
		customerTrajectorySonService.updateById(customerTrajectorySon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("cus:customertrajectoryson:delete")
    public R delete(@RequestBody String[] ids){
		customerTrajectorySonService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
