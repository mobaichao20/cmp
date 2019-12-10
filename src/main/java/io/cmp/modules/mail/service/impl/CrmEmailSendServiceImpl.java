package io.cmp.modules.mail.service.impl;


import io.cmp.common.exception.RRException;
import io.cmp.common.utils.DateUtils;
import io.cmp.modules.mail.entity.CrmEmailAccountEntity;
import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;
import io.cmp.modules.mail.entity.CrmEmailReceiverEntity;
import io.cmp.modules.mail.handle.MailUtil;
import io.cmp.modules.mail.service.CrmEmailAttachmentService;
import io.cmp.modules.mail.service.CrmEmailReceiverService;
import io.cmp.modules.mail.utils.FilesUpload;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.mail.dao.CrmEmailSendDao;
import io.cmp.modules.mail.entity.CrmEmailSendEntity;
import io.cmp.modules.mail.service.CrmEmailSendService;

import javax.mail.MessagingException;

@Slf4j
@Service("crmEmailSendService")
public class CrmEmailSendServiceImpl extends ServiceImpl<CrmEmailSendDao, CrmEmailSendEntity> implements CrmEmailSendService {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private MailUtil mailUtil = new MailUtil();
    @Autowired
    private CrmEmailAttachmentService crmEmailAttachmentService;
    @Autowired
    private CrmEmailReceiverService crmEmailReceiverService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CrmEmailSendEntity> page = this.page(
                new Query<CrmEmailSendEntity>().getPage(params),
                new QueryWrapper<CrmEmailSendEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void sendMail(CrmEmailAccountEntity mailConfig1, CrmEmailSendEntity crmEmailSend) {
        crmEmailSend.setSendAddress(mailConfig1.getDisplayedName());
        crmEmailSend.setIsDelete("0");
        List<CrmEmailSendEntity> sendMessage = null;
        try {
            sendMessage = mailUtil.send(mailConfig1, crmEmailSend);
        } catch (NoSuchProviderException e) {
            log.debug("--------服务连接异常----------");
            e.printStackTrace();
        } catch (MessagingException e) {
            log.debug("--------发送信息异常----------");
            e.printStackTrace();
        }
//        最后拼接出来邮箱
        StringBuilder sb = new StringBuilder();
//        往数据库中添加发送的邮件
        if (sendMessage != null) {
            for (CrmEmailSendEntity crmEmailSendEntity : sendMessage) {

//此处为转发
                if (crmEmailSendEntity.getMailType().equals("7")) {
                    log.debug("-------保存附件中-------");
//                    通过Id查询出来邮件
                    CrmEmailReceiverEntity crmEmailReceiverEntity = crmEmailReceiverService.getById(crmEmailSendEntity.getId());
//保存进发件箱
                    crmEmailSendEntity.setId(null);
                    this.save(crmEmailSendEntity);
/*
//                    然后根据Id查询附件
                    List<CrmEmailAttachmentEntity> list = crmEmailAttachmentService.list(new QueryWrapper<CrmEmailAttachmentEntity>().eq("mr_creater", crmEmailReceiverEntity.getId()));

//                    把附件重新上传
                    for (CrmEmailAttachmentEntity crmEmailAttachmentEntity : list) {
                        crmEmailAttachmentEntity.setId(null);
                        crmEmailAttachmentEntity.setMrCreater(crmEmailSendEntity.getId());
                        crmEmailAttachmentEntity.setCreateTime(new Date());
                        crmEmailAttachmentService.save(crmEmailAttachmentEntity);
                        *//*String path=crmEmailAttachmentEntity.getAbsolutePath();

                        FilesUpload.copy(crmEmailAttachmentEntity.getAbsolutePath(),"");*//*

                    }*/


//                    在保存一份到附件表

                } else {
                    log.debug("已经回复---------" + crmEmailSendEntity.getMailType());

                    if (crmEmailSendEntity.getMailType().equals("6")) {
                        log.debug("crmEmailSendEntity.getId()---------" + crmEmailSendEntity.getId());

//                    判断邮件是否回复了
                        CrmEmailReceiverEntity crmEmailReceiverEntity = crmEmailReceiverService.getById(crmEmailSendEntity.getId());
//                    更新成已经回复的邮件
                        if(crmEmailReceiverEntity!=null){
                            crmEmailReceiverEntity.setIsReplay("1");
                            crmEmailReceiverService.updateById(crmEmailReceiverEntity);
                        }

                    }

                    crmEmailSendEntity.setId(null);
                    baseMapper.insert(crmEmailSendEntity);
                    if (crmEmailSendEntity.getReceiver() != null) {
                        sb.append(crmEmailSendEntity.getReceiver());
                        sb.append(";");
                    }
                    if (crmEmailSendEntity.getMailCopy() != null) {
                        sb.append(crmEmailSendEntity.getMailCopy());
                        sb.append(";");
                    }
                    if (crmEmailSendEntity.getSecuritySend() != null) {
                        sb.append(crmEmailSendEntity.getSecuritySend());
                        sb.append(";");
                    }
                }
//
                    log.debug("---------------sb.toString()---------------" + sb.toString());
//        添加附件【更新】
                    String mail = mailConfig1.getMailAddress();
                    List<CrmEmailAttachmentEntity> list = crmEmailAttachmentService.list(new QueryWrapper<CrmEmailAttachmentEntity>().eq(StringUtils.isNotBlank(mail), "mr_creater", mail));

                    for (CrmEmailAttachmentEntity crmEmailAttachmentEntity : list) {
                        crmEmailAttachmentEntity.setMrCreater(crmEmailSendEntity.getId());
//            发件附件
                        crmEmailAttachmentEntity.setMbMailType(1);
//                    附件类型改为已发送状态
                        crmEmailAttachmentService.updateById(crmEmailAttachmentEntity);
                    }
                }
            }
        }
/*        log.debug("---------------sb.toString()---------------"+sb.toString());
//        添加附件【更新】
        String mail=mailConfig1.getMailAddress();
     List<CrmEmailAttachmentEntity> list= crmEmailAttachmentService.list(new QueryWrapper<CrmEmailAttachmentEntity>().eq(StringUtils.isNotBlank(mail),"mr_creater", mail));

        for (CrmEmailAttachmentEntity crmEmailAttachmentEntity:list) {
            crmEmailAttachmentEntity.setMrCreater(sb.toString());
//            发件附件
            crmEmailAttachmentEntity.setMbMailType(1);
            crmEmailAttachmentService.updateById(crmEmailAttachmentEntity);
        }*/




    @Override
    public void upload(CrmEmailAttachmentEntity crmEmailAttachmentEntity) {

//      文件路径
        String paths[] = new String[2];
//      文件类型
        String contentTypes[] = new String[2];
//      文件名
        String name = null;
        // 接收参数username
        logger.debug("uploadPath = " + crmEmailAttachmentEntity.getLocation());

        // 如果文件不为空，写入上传路径
        if (crmEmailAttachmentEntity.getAttachment() != null) {
            // 接收参数username

            // 如果文件不为空，写入上传路径
            if (!crmEmailAttachmentEntity.getAttachment().isEmpty()) {
                //上传文件路径
                String path = crmEmailAttachmentEntity.getLocation() + crmEmailAttachmentEntity.getMail() + "/send/" + DateUtils.format(new Date()) + "/accessories/";
                // logger.debug("URL = " + request.getRequestURL());

                logger.debug("path = " + path);
                paths = path.split(":");
                String contentType = crmEmailAttachmentEntity.getAttachment().getContentType();

                contentTypes = contentType.split("/");
                logger.debug("contentType = " + contentTypes[1]);
                // 上传文件名
                //  if (contentTypes[1].trim().equals("jpg") || contentTypes[1].trim().equals("jpeg") || contentTypes[1].trim().equals("png")) {
                String filename = crmEmailAttachmentEntity.getAttachment().getOriginalFilename();
                File filepath = new File(path, filename);
                // 判断路径是否存在，如果不存在就创建一个
                if (!filepath.getParentFile().exists()) {
                    filepath.getParentFile().mkdirs();
                }
                // 将上传文件保存到一个目标文件当中
                name = File.separator + UUID.randomUUID().toString().replace("-", "");
                name = name.substring(3);

                try {
                    //user.getHeadPortrait().transferTo(new File(path + name+"."+filename));
                    crmEmailAttachmentEntity.getAttachment().transferTo(new File(path + name + "." + filename));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                crmEmailAttachmentEntity.setAbsolutePath(path + name + "." + filename);
                crmEmailAttachmentEntity.setUrl(paths[1] + name + "." + filename);
                crmEmailAttachmentEntity.setName(filename);
                crmEmailAttachmentEntity.setMbMailType(0);
                crmEmailAttachmentEntity.setCreateTime(new Date());
//                    默认是未发送的附件
                crmEmailAttachmentEntity.setMbMailType(6);
                //} else {
                // throw new RRException("图片格式支持jpg、png、jepg");
                //}


            }
        }
        crmEmailAttachmentEntity.setMbMailType(1);
        crmEmailAttachmentService.save(crmEmailAttachmentEntity);
    }

    @Override
    public void saveDraft(CrmEmailSendEntity crmEmailSend, CrmEmailAccountEntity email) {
        String mail = email.getMailAddress();
        //        设置发送人
        crmEmailSend.setSender(mail);
//        设置为草稿件
        crmEmailSend.setMailType("1");
//        保存为草稿件
        crmEmailSend.setId(null);
        this.save(crmEmailSend);
//        更新附件
        List<CrmEmailAttachmentEntity> list = crmEmailAttachmentService.list(new QueryWrapper<CrmEmailAttachmentEntity>().eq(StringUtils.isNotBlank(mail), "mr_creater", mail));
        for (CrmEmailAttachmentEntity crmEmailAttachmentEntity : list) {
//            更新为草稿附件
            crmEmailAttachmentEntity.setMbMailType(4);
            crmEmailAttachmentEntity.setMrCreater(crmEmailSend.getId());
            crmEmailAttachmentService.updateById(crmEmailAttachmentEntity);
        }


    }

}