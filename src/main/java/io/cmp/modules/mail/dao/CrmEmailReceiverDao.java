package io.cmp.modules.mail.dao;

import io.cmp.modules.mail.entity.CrmEmailReceiverEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 邮件收件箱
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
@Mapper
public interface CrmEmailReceiverDao extends BaseMapper<CrmEmailReceiverEntity>{

    List<CrmEmailReceiverEntity> emailReceiverList();
}
