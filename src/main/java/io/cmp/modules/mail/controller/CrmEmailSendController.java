package io.cmp.modules.mail.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.cmp.common.annotation.SysLog;
import io.cmp.common.utils.Constant;
import io.cmp.common.utils.MailUtil;
import io.cmp.common.validator.ValidatorUtils;
import io.cmp.modules.mail.entity.CrmEmailAccountEntity;
import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;
import io.cmp.modules.mail.service.CrmEmailAccountService;
import io.cmp.modules.mail.service.CrmEmailAttachmentService;
import io.cmp.modules.mail.utils.ScheduledService;
import io.cmp.modules.sys.controller.AbstractController;
import io.cmp.modules.sys.entity.SysUserEntity;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.web.bind.annotation.*;

import io.cmp.modules.mail.entity.CrmEmailSendEntity;
import io.cmp.modules.mail.service.CrmEmailSendService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * 邮件发送箱表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
@Slf4j
@RestController
@RequestMapping("mail/crmemailsend")
@PropertySource(value = "classpath:application.yml")

public class CrmEmailSendController extends AbstractController {
    /*    @Autowired
        private JavaMailSender mailSender;*/
    @Autowired
    private CrmEmailSendService crmEmailSendService;
    @Autowired
    private CrmEmailAccountService crmEmailAccountService;

/*    @Autowired
    private JobDetail firstJobDetail;*/
@Value("${com.upload.location}")
private String location;
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mail:crmemailsend:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = crmEmailSendService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("mail:crmemailsend:info")
    public R info(@PathVariable("id") String id) {
        CrmEmailSendEntity crmEmailSend = crmEmailSendService.getById(id);



        return R.ok().put("crmEmailSend", crmEmailSend);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mail:crmemailsend:save")
    public R save(@RequestBody CrmEmailSendEntity crmEmailSend) {
        crmEmailSendService.save(crmEmailSend);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mail:crmemailsend:update")
    public R update(@RequestBody CrmEmailSendEntity crmEmailSend) {
        crmEmailSendService.updateById(crmEmailSend);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mail:crmemailsend:delete")
    public R delete(@RequestBody String[] ids) {
        crmEmailSendService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 发邮件
     */
    @PostMapping("/sendMail")
    // @RequestMapping(value = "/sendMail",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    //@RequiresPermissions("mail:crmemailsend:update")
    public R sendMail(HttpServletRequest request, @RequestBody CrmEmailSendEntity crmEmailSend) {
//        获取本用户的email
        String email = getUser().getEmail();
        log.debug("---email-----"+email);
//      邮箱的配置
       CrmEmailAccountEntity mailConfig1= crmEmailAccountService.getOne(new QueryWrapper<CrmEmailAccountEntity>()
                .eq(StringUtils.isNotBlank(email), "mail_address", email));
        crmEmailSendService.sendMail(mailConfig1,crmEmailSend);
        log.debug("-----crmEmailSend-----" + crmEmailSend);
        return R.ok();
    }
    /**
     * 附件上传
     */
    @PostMapping(value = "/upload")
    //@PostMapping(value = "/upload",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    //@RequiresPermissions("sys:user:upload")
    public R upload(@ApiParam(value = "附件") MultipartFile attachment,HttpServletRequest request) {

        CrmEmailAttachmentEntity crmEmailAttachmentEntity = new CrmEmailAttachmentEntity();
      crmEmailAttachmentEntity.setLocation(location);  //location
        log.debug("location = " + crmEmailAttachmentEntity.getLocation());
        String email = getUser().getEmail();
//        创建的时候直接拼接上路径
        crmEmailAttachmentEntity.setMail(email);
        log.debug("attachment\t"+attachment);
        crmEmailAttachmentEntity.setAttachment(attachment);
//        为了识别是哪一个用户上传的头像
        crmEmailAttachmentEntity.setMrCreater(email);
        //user.setCreateUserId(getUserId());

        crmEmailSendService.upload(crmEmailAttachmentEntity);



        return R.ok();
    }
    /**
     * 保存为草稿件
     */

    @PostMapping("/draft")
    // @RequestMapping(value = "/sendMail",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    //@RequiresPermissions("mail:crmemailsend:update")
    public R draft(@RequestBody CrmEmailSendEntity crmEmailSend) {
        //MailUtil.createMailSender().sen
//        获取本用户的email
        String email = getUser().getEmail();
        log.debug("---email-----"+email);
//      邮箱的配置
        CrmEmailAccountEntity mailConfig= crmEmailAccountService.getOne(new QueryWrapper<CrmEmailAccountEntity>()
                .eq(StringUtils.isNotBlank(email), "mail_address", email));

        crmEmailSendService.saveDraft(crmEmailSend,mailConfig);
        log.debug("-----crmEmailSend-----" + crmEmailSend);
        return R.ok();
    }
    /**
     * 判断是否是转发   然后去查询附件
     */

    @PostMapping("/mailRelay/{id}")
    //@RequiresPermissions("mail:crmemailsend:info")
    public R mailRelay(@PathVariable("id") String id) {

        crmEmailAccountService.mailRelay(id);



        return R.ok();
    }



}
