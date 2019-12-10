package io.cmp.modules.mail.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.cmp.modules.sys.controller.AbstractController;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;
import io.cmp.modules.mail.service.CrmEmailAttachmentService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;


/**
 * 邮件附件表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
@RestController
@RequestMapping("mail/crmemailattachment")
public class CrmEmailAttachmentController extends AbstractController {
    @Autowired
    private CrmEmailAttachmentService crmEmailAttachmentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mail:crmemailattachment:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = crmEmailAttachmentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("mail:crmemailattachment:info")
    public R info(@PathVariable("id") String id) {
        CrmEmailAttachmentEntity crmEmailAttachment = crmEmailAttachmentService.getById(id);

        return R.ok().put("crmEmailAttachment", crmEmailAttachment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mail:crmemailattachment:save")
    public R save(@RequestBody CrmEmailAttachmentEntity crmEmailAttachment) {
        crmEmailAttachmentService.save(crmEmailAttachment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mail:crmemailattachment:update")
    public R update(@RequestBody CrmEmailAttachmentEntity crmEmailAttachment) {
        crmEmailAttachmentService.updateById(crmEmailAttachment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mail:crmemailattachment:delete")
    public R delete(@RequestBody String[] ids) {
        crmEmailAttachmentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 通过名字删除附件
     */
    @RequestMapping("/remove/{name}")
    //@RequiresPermissions("mail:crmemailattachment:info")
    public R remove(@PathVariable("name") String name) {
//        当窗口关闭的时候删除所有未上传的附件
        if(name.equals("del")){
            crmEmailAttachmentService.remove(new QueryWrapper<CrmEmailAttachmentEntity>().eq("mr_creater", getUser().getEmail()));

        }else {
//        先查询出符合条件的所有附件--只删除第一条数据
           List<CrmEmailAttachmentEntity> list= crmEmailAttachmentService.list(new QueryWrapper<CrmEmailAttachmentEntity>().eq("mr_creater", getUser().getEmail()).eq(StringUtils.isNotBlank(name), "name", name));
            for (CrmEmailAttachmentEntity crmEmailAttachmentEntity:list) {
                crmEmailAttachmentService.removeById(crmEmailAttachmentEntity.getId());
                return  R.ok();
            }

           // crmEmailAttachmentService.remove(new QueryWrapper<CrmEmailAttachmentEntity>().eq("mr_creater", getUser().getEmail()).eq(StringUtils.isNotBlank(name), "name", name));

        }
        return R.ok();
    }


}
