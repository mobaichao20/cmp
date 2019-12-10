package io.cmp.modules.mma.dao;

import io.cmp.modules.mma.entity.CrmSmsSendRecordsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cmp.modules.mma.vo.SmsFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 短信发送记录表
 * 
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
@Mapper
public interface CrmSmsSendRecordsDao extends BaseMapper<CrmSmsSendRecordsEntity> {
    //假删除客户信息记录
    public boolean updateByIds(String[] ids);

    List<SmsFile> findMailBySms(@Param("smsFile")SmsFile smsFile);
}
