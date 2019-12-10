package io.cmp.modules.specialList.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.specialList.entity.SpecialListEntity;
import io.cmp.modules.specialList.vo.VoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 特殊名单
 */
public interface SpecialListService extends IService<SpecialListEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<VoEntity> getByPhone(Map<String, Object> params);

    List<SpecialListEntity> queryBySel(Map<String, Object> params);

    void deleteBatch(String[] listIds);
}
