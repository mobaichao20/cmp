package io.cmp.modules.mail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.mail.entity.CrmEmailAccountEntity;
import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;
import io.cmp.modules.mail.entity.CrmEmailSendEntity;

import java.util.Map;

/**
 * 邮件发送箱表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
public interface CrmEmailSendService extends IService<CrmEmailSendEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void sendMail(CrmEmailAccountEntity mailConfig,CrmEmailSendEntity crmEmailSend);

    void upload(CrmEmailAttachmentEntity crmEmailAttachmentEntity);

    void saveDraft(CrmEmailSendEntity crmEmailSend,CrmEmailAccountEntity email);
}

