package io.cmp.modules.csr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cmp.modules.csr.entity.CrmCsrLog;
import io.cmp.modules.csr.vo.CrmCsrLogVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 客户满意度
 */
@Mapper
public interface CrmCsrLogDao extends BaseMapper<CrmCsrLog> {
        List<CrmCsrLogVO> getAll();   //获得所有满意度数据

}
