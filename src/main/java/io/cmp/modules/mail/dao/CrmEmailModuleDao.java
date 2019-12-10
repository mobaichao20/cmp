package io.cmp.modules.mail.dao;

import io.cmp.modules.mail.entity.CrmEmailModuleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 邮件模板表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-05 11:01:10
 */
@Mapper
public interface CrmEmailModuleDao extends BaseMapper<CrmEmailModuleEntity> {
	
}
