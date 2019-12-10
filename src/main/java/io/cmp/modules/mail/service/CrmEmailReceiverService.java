package io.cmp.modules.mail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.mail.dao.CrmEmailReceiverDao;
import io.cmp.modules.mail.entity.CrmEmailReceiverEntity;

import java.util.List;
import java.util.Map;

/**
 * 邮件收件箱
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
public interface CrmEmailReceiverService extends IService<CrmEmailReceiverEntity> {

    PageUtils queryPage(Map<String, Object> params,Long id);

    List<CrmEmailReceiverEntity> emailReceiverList(CrmEmailReceiverService crmEmailReceiverService);

    boolean testss();

    void receiveMail(String location,Long id);

    void placeOnFile(String id);

    PageUtils placeOnFileList(Map<String, Object> params);

    R bindingCustomer(String mailId, String customId);


}

