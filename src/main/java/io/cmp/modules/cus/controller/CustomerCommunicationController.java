package io.cmp.modules.cus.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.cus.entity.CustomerCommunicationEntity;
import io.cmp.modules.cus.service.CustomerCommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;



/**
 * 客户信息通讯表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-02 15:13:26
 */
@RestController
@RequestMapping("cus/customercommunication")
public class CustomerCommunicationController {
    @Autowired
    private CustomerCommunicationService customerCommunicationService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("cus:customercommunication:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = customerCommunicationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("cus:customercommunication:info")
    public R info(@PathVariable("id") String id){
		CustomerCommunicationEntity customerCommunication = customerCommunicationService.getById(id);

        return R.ok().put("customerCommunication", customerCommunication);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("cus:customercommunication:save")
    public R save(@RequestBody CustomerCommunicationEntity customerCommunication){
        customerCommunication.setCreateTime(new Date());
        customerCommunication.setIsDelete("0");
		customerCommunicationService.save(customerCommunication);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("cus:customercommunication:update")
    public R update(@RequestBody CustomerCommunicationEntity customerCommunication){
        customerCommunication.setUpdateTime(new Date());
		customerCommunicationService.updateById(customerCommunication);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("cus:customercommunication:delete")
    public R delete(@RequestBody String[] ids){
		customerCommunicationService.updateByIds(ids);

        return R.ok();
    }

    /**
     * 分页查询客户信息通讯表
     */
    @RequestMapping("/queryCustomerCommunicationCustomerBaselist")
    public R queryCustomerCommunicationCustomerBaselist(@RequestParam Map<String, Object> params){
        PageUtils page = customerCommunicationService.queryCustomerCommunicationCustomerBaselist(params);
        return R.ok().put("page", page);
    }
}
