package io.cmp.modules.wot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.wot.entity.SysNodeEntity;

import java.util.Map;

/**
 * 节点表
 */
public interface SysNodeService extends IService<SysNodeEntity> {
    PageUtils queryPage(Map<String, Object> params);
    /**
     * 删除模板
     */
    void deleteBatch(String[] needDelwotps);

}
