package io.cmp.modules.mail.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.cmp.common.utils.Constant;
import io.cmp.common.utils.R;
import io.cmp.modules.cus.entity.CustomerBaseEntity;
import io.cmp.modules.cus.entity.CustomerCommunicationEntity;
import io.cmp.modules.cus.service.CustomerBaseService;
import io.cmp.modules.cus.service.CustomerCommunicationService;
import io.cmp.modules.mail.entity.CrmEmailAccountEntity;
import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;
import io.cmp.modules.mail.handle.ReceiveEmailHandlerImpl;
import io.cmp.modules.mail.service.CrmEmailAccountService;
import io.cmp.modules.mail.service.CrmEmailAttachmentService;
import io.cmp.modules.mail.utils.ScheduledService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.mail.dao.CrmEmailReceiverDao;
import io.cmp.modules.mail.entity.CrmEmailReceiverEntity;
import io.cmp.modules.mail.service.CrmEmailReceiverService;
import org.springframework.transaction.annotation.Transactional;


@Service("crmEmailReceiverService")
@Slf4j
public class CrmEmailReceiverServiceImpl extends ServiceImpl<CrmEmailReceiverDao, CrmEmailReceiverEntity> implements CrmEmailReceiverService {
    @Autowired
    private CrmEmailReceiverService crmEmailReceiverService;
    @Autowired
    private CrmEmailAttachmentService crmEmailAttachmentService;
    @Autowired
    private CrmEmailAccountService crmEmailAccountService;
    @Autowired
    private CustomerCommunicationService customerCommunicationService;
    @Autowired
    private CustomerBaseService customerBaseService;

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long id) {
        String mediumType= (String)params.get("mediumType");
        String fullText= (String)params.get("fullText");
        String mailSubject= (String)params.get("mailSubject");
        String receiver= (String)params.get("receiver");
        String sender= (String)params.get("sender");


        IPage<CrmEmailReceiverEntity> page = this.page(
                new Query<CrmEmailReceiverEntity>().getPage(params),
                new QueryWrapper<CrmEmailReceiverEntity>()
                        .like(StringUtils.isNotBlank(mediumType), "medium_type", mediumType)
                        .like(StringUtils.isNotBlank(fullText), "full_text", fullText)
                        .like(StringUtils.isNotBlank(mailSubject), "mail_subject", mailSubject)
                        .like(StringUtils.isNotBlank(receiver), "receiver", receiver)
                        .like(StringUtils.isNotBlank(sender), "sender", sender)

                        .eq("mail_type", 1)
                        .eq("mail_owner", id + "")
        );
//        放入附件
        List<CrmEmailReceiverEntity> list = page.getRecords();


        for (CrmEmailReceiverEntity crmEmailReceiverEntity : list) {

            List<CrmEmailAttachmentEntity> resource = crmEmailAttachmentService.list(new QueryWrapper<CrmEmailAttachmentEntity>().eq("mb_mail_type", 1).eq("mr_creater", crmEmailReceiverEntity.getId()));
            crmEmailReceiverEntity.setMailResource(resource);
        }

        return new PageUtils(page);
    }

    @Override
    public List<CrmEmailReceiverEntity> emailReceiverList(CrmEmailReceiverService crmEmailReceiverService) {
        log.debug("crmEmailReceiverDao" + crmEmailReceiverService);


        List<CrmEmailReceiverEntity> result = crmEmailReceiverService.list();
        log.debug("---------result-----" + result);

        return result;
    }

    @Override
    public boolean testss() {

        //System.out.println("----------- boolean isNewMail(String uid)-------app------"+crmEmailReceiverDao);
        return false;
    }

    @Override
    public void receiveMail(String location, Long id) {
//处理后的邮件内容
        String result = null;
//        对发件人进行处理
        String sender = null;
        log.debug("---location-----" + location);
        String email = new ScheduledService().getuser().getEmail();
        log.debug("---email-----" + email);
//      邮箱的配置
        CrmEmailAccountEntity mailConfig = crmEmailAccountService.getOne(new QueryWrapper<CrmEmailAccountEntity>()
                .eq(StringUtils.isNotBlank(email), "mail_address", email));


        try {
            //List<CrmEmailReceiverEntity> list=new ReceiveEmailHandlerImpl(new StringBuilder("D:\\testEmail")).receiveMails(0,mailConfig,crmEmailReceiverDao);
            List<List<CrmEmailReceiverEntity>> list = new ReceiveEmailHandlerImpl(new StringBuilder(location)).receiveMails(0, mailConfig, crmEmailReceiverService);
            log.debug("-----list------" + list);
            for (List<CrmEmailReceiverEntity> list1 : list) {
                for (CrmEmailReceiverEntity crmEmailReceiverEntity : list1) {
                    sender = crmEmailReceiverEntity.getSender();
//                    对发件人进行处理
                   if (crmEmailReceiverEntity.getSender() != null) {
                     if(sender.contains("<")){
                         log.debug("=====================");

                         int start = sender.indexOf("<");
                         int end = sender.indexOf(">");
                         crmEmailReceiverEntity.setSender(sender.substring(start + 1, end));
                     }else{
                         crmEmailReceiverEntity.setSender(sender);
                     }


                    }


//                            对接收的邮件内容进行处理
                    if (crmEmailReceiverEntity.getMailContent() != null) {
                        String content = crmEmailReceiverEntity.getMailContent();
                        result = content.replace("blockquote", "div");
                        crmEmailReceiverEntity.setMailContent(result);
                    }
                    crmEmailReceiverEntity.setMailOwner(id + "");

                    crmEmailReceiverService.save(crmEmailReceiverEntity);
                    if (crmEmailReceiverEntity.getMailResource() != null) {
                        for (CrmEmailAttachmentEntity crmEmailAttachmentEntity : crmEmailReceiverEntity.getMailResource()) {
//                            设置URL地址
                            String res[] = new String[20];
                            if (crmEmailAttachmentEntity.getUrl() != null) {
                                log.debug("-----getUrl------" + crmEmailAttachmentEntity.getUrl());
                                res = crmEmailAttachmentEntity.getUrl().split(":");
                                crmEmailAttachmentEntity.setUrl(res[1]);
                            }
//                            设置文件的名称<>
                            if (crmEmailAttachmentEntity.getName() != null) {
                                crmEmailAttachmentEntity.setName(crmEmailAttachmentEntity.getName());
                            }
                            crmEmailAttachmentEntity.setMbMailType(1);
//                            设置文件所属于者
                            crmEmailAttachmentEntity.setMrCreater(crmEmailReceiverEntity.getId());
//                            设置创建时间
                            crmEmailAttachmentEntity.setCreateTime(new Date());

                            crmEmailAttachmentEntity.setAbsolutePath(location.split(":")[0] + ":" + res[1]);


                            crmEmailAttachmentService.save(crmEmailAttachmentEntity);
                        }
                    }
                }


            }

        } catch (Exception e) {
            log.debug("-----------接受邮件失败----------");
            e.printStackTrace();
        }
        log.debug("-----------接受邮件成功----------");

    }

    @Override
    public void placeOnFile(String id) {
        if (id != null) {
//            查询出来收件
            CrmEmailReceiverEntity crmEmailReceiverEntity = crmEmailReceiverService.getById(id);
//            防止查询出脏数据
            if (crmEmailReceiverEntity != null) {
//                更新为归档件
                crmEmailReceiverEntity.setMailType("5");
                crmEmailReceiverService.updateById(crmEmailReceiverEntity);

                List<CrmEmailAttachmentEntity> list = crmEmailAttachmentService.list(new QueryWrapper<CrmEmailAttachmentEntity>().eq(StringUtils.isNotBlank(id), "mr_creater", id));
                for (CrmEmailAttachmentEntity c : list) {
                    c.setMbMailType(5);
                    crmEmailAttachmentService.updateById(c);
                }
            }
        }

    }

    @Override
    public PageUtils placeOnFileList(Map<String, Object> params) {
        IPage<CrmEmailReceiverEntity> page = this.page(
                new Query<CrmEmailReceiverEntity>().getPage(params),
                new QueryWrapper<CrmEmailReceiverEntity>()
                        .eq("mail_type", 5)
        );

        return new PageUtils(page);
    }

    @Override
    public R bindingCustomer(String mailId, String customId) {
        boolean flag = true;


//       通过客户id查询客户--获取到姓名和客户Id
        List<CustomerCommunicationEntity> list = customerCommunicationService.list(new QueryWrapper<CustomerCommunicationEntity>()
                .eq("customer_id", customId)
        );
//        通过详情信息表查看邮件是否已经绑定了
//        通过客户Id查询关系表获取邮箱地址
        CrmEmailReceiverEntity crmEmailReceiverEntity = this.getById(mailId);
      for (CustomerCommunicationEntity customerCommunicationEntity:list) {
            if(customerCommunicationEntity.getEmail().equals(crmEmailReceiverEntity.getSender())){
                flag=false;


            }
        }
//      为客户绑定邮件
      if(flag){
          CustomerCommunicationEntity customerCommunicationEntity=new CustomerCommunicationEntity();
          customerCommunicationEntity.setCustomerId(customId);
          customerCommunicationEntity.setEmail(crmEmailReceiverEntity.getSender());
          customerCommunicationService.save(customerCommunicationEntity);
      }



        return R.ok().put("flag", flag);
//        更新收件箱中的客户
        //return R.ok().put("flag", "");

    }


}

