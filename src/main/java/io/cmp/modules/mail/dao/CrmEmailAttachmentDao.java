package io.cmp.modules.mail.dao;

import io.cmp.modules.mail.entity.CrmEmailAttachmentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 邮件附件表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
@Mapper
public interface CrmEmailAttachmentDao extends BaseMapper<CrmEmailAttachmentEntity> {
	
}
