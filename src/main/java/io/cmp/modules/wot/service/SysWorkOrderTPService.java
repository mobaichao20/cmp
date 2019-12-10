package io.cmp.modules.wot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.wot.entity.SysWorkOrderTPEntity;

import java.util.Map;

/**
 * 工单模板
 */
public interface SysWorkOrderTPService extends IService<SysWorkOrderTPEntity> {

    PageUtils queryPage(Map<String, Object> params);


    /**
     * 删除模板
     */
    void deleteBatch(String[] needDelwotps);

}
