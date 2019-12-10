package io.cmp.modules.mail.controller;

import java.util.Arrays;
import java.util.Map;

import io.cmp.modules.mail.utils.ScheduledService;
import io.cmp.modules.sys.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import io.cmp.modules.mail.entity.CrmEmailReceiverEntity;
import io.cmp.modules.mail.service.CrmEmailReceiverService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;


/**
 * 邮件收件箱
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
@Slf4j
@RestController
@RequestMapping("mail/crmemailreceiver")
@PropertySource(value = "classpath:application.yml")
public class CrmEmailReceiverController extends AbstractController {
    @Autowired
    private CrmEmailReceiverService crmEmailReceiverService;
    @Value("${com.upload.location}")
    private String location;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mail:crmemailreceiver:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = crmEmailReceiverService.queryPage(params,getUserId());
        crmEmailReceiverService.testss();
        return R.ok().put("page", page);
    }

    /**
     * 归档箱列表placeOnFile
     */
    @RequestMapping("/placeOnFileList")
    //@RequiresPermissions("mail:crmemailreceiver:list")
    public R placeOnFileList(@RequestParam Map<String, Object> params) {
        PageUtils page = crmEmailReceiverService.placeOnFileList(params);
        crmEmailReceiverService.testss();
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("mail:crmemailreceiver:info")
    public R info(@PathVariable("id") String id) {
        CrmEmailReceiverEntity crmEmailReceiver = crmEmailReceiverService.getById(id);

        return R.ok().put("crmEmailReceiver", crmEmailReceiver);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mail:crmemailreceiver:save")
    public R save(@RequestBody CrmEmailReceiverEntity crmEmailReceiver) {
        crmEmailReceiverService.save(crmEmailReceiver);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mail:crmemailreceiver:update")
    public R update(@RequestBody CrmEmailReceiverEntity crmEmailReceiver) {
        crmEmailReceiverService.updateById(crmEmailReceiver);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mail:crmemailreceiver:delete")
    public R delete(@RequestBody String[] ids) {
        crmEmailReceiverService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 接收全部邮件
     */
    @RequestMapping("/receiver")
    //@RequiresPermissions("mail:crmemailreceiver:save")
    public R receiver() {
        log.debug("======location=======" + location);

        crmEmailReceiverService.receiveMail(location,getUserId());

        return R.ok();
    }

    /**
     * 归档件
     */
    @RequestMapping("/placeOnFile/{id}")
    //@RequiresPermissions("mail:crmemailreceiver:delete")
    public R placeOnFile(@PathVariable("id") String id) {
        crmEmailReceiverService.placeOnFile(id);

        return R.ok();
    }

    /**
     * 绑定客户
     */

    @PostMapping("/bindingCustomer/{mailId}/{customId}")
    //@RequiresPermissions("mail:crmemailreceiver:delete")
    public R bindingCustomer(@PathVariable("mailId") String mailId,@PathVariable("customId") String customId) {
        R r=  crmEmailReceiverService.bindingCustomer(mailId,customId);

        return r;
    }

    /**
     * 过滤已经绑定过的客户
     */



}
