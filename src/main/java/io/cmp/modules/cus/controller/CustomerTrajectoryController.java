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

import io.cmp.modules.cus.entity.CustomerTrajectoryEntity;
import io.cmp.modules.cus.service.CustomerTrajectoryService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 客户轨迹表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-06-26 17:02:58
 */
@RestController
@RequestMapping("cus/customertrajectory")
public class CustomerTrajectoryController {
    @Autowired
    private CustomerTrajectoryService customerTrajectoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("cus:customertrajectory:list")
    public R list(@RequestParam Map<String, Object> params){


        PageUtils page = customerTrajectoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("cus:customertrajectory:info")
    public R info(@PathVariable("id") String id){
		CustomerTrajectoryEntity customerTrajectory = customerTrajectoryService.getById(id);

        return R.ok().put("customerTrajectory", customerTrajectory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("cus:customertrajectory:save")
    public R save(@RequestBody CustomerTrajectoryEntity customerTrajectory){
        customerTrajectory.setCreateTime(new Date());
		//customerTrajectoryService.save(customerTrajectory);
        customerTrajectoryService.saveCustomerTrajectory(customerTrajectory);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("cus:customertrajectory:update")
    public R update(@RequestBody CustomerTrajectoryEntity customerTrajectory){
		//customerTrajectoryService.updateById(customerTrajectory);
        customerTrajectoryService.updateCustomerTrajectory(customerTrajectory);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("cus:customertrajectory:delete")
    public R delete(@RequestBody String[] ids){
		//customerTrajectoryService.removeByIds(Arrays.asList(ids));
        customerTrajectoryService.deleteBatch(ids);
        return R.ok();
    }

}
