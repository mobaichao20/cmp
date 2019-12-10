package io.cmp.modules.mail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.mail.entity.CrmEmailAccountEntity;

import java.util.Map;

/**
 * 邮件账号信息表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
public interface CrmEmailAccountService extends IService<CrmEmailAccountEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean deleteByIds(String[] ids);


    boolean testsss();

    void mailRelay(String id);
}

