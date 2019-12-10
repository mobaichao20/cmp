package io.cmp.modules.out.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cmp.modules.out.entity.OutriggerMessageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 外联数据表
 * 
 * @author mobaichao
 * @email mobcsh@sinosoft.com.cn
 * @date 2019-12-06 10:12:42
 */
@Mapper
public interface OutriggerMessageDao extends BaseMapper<OutriggerMessageEntity> {

    /**
     * 查询未使用数据
     * @return
     */
	List<OutriggerMessageEntity> queryNotUsed();
}
