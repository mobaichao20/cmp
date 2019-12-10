package io.cmp.modules.csr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.csr.entity.CrmCsrLog;
import io.cmp.modules.csr.vo.CrmCsrLogVO;

import java.util.List;
import java.util.Map;

/**
 * 客户满意度
 */
public interface CrmCsrLogService extends IService<CrmCsrLog> {

    PageUtils queryPage(Map<String, Object> params);
    List<CrmCsrLogVO> getAll();   //获得所有满意度数据
}
