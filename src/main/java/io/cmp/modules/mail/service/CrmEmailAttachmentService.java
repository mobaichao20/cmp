package io.cmp.modules.mail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;

import java.util.Map;

/**
 * 邮件附件表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
public interface CrmEmailAttachmentService extends IService<CrmEmailAttachmentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

