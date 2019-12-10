package io.cmp.modules.wot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.wot.entity.SysNodeLocusEntity;

import java.util.Map;

/**
 * 节点轨迹
 */
public interface SysNodeLocusService  extends IService<SysNodeLocusEntity> {
    PageUtils queryPage(Map<String, Object> params);
    /**
     * 删除节点轨迹
     */
    void deleteBatch(String[] needDelwotps);

}
