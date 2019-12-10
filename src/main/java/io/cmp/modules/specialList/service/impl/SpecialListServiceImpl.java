package io.cmp.modules.specialList.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;
import io.cmp.modules.specialList.dao.SpecialListDao;
import io.cmp.modules.specialList.entity.SpecialListEntity;
import io.cmp.modules.specialList.vo.VoEntity;
import io.cmp.modules.specialList.service.SpecialListService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service("specialListService")
public class SpecialListServiceImpl extends ServiceImpl<SpecialListDao, SpecialListEntity> implements SpecialListService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String id = params.get("id").toString();
        String customerName =  params.get("customerName").toString();
        String creator =  params.get("creator").toString();
        String createTime =  params.get("createTime").toString();
        String verifier =  params.get("verifier").toString();
        String verifierTime =  params.get("verifierTime").toString();
        String status =  params.get("status").toString();
        String listType = params.get("listType").toString();

        IPage<SpecialListEntity> page = this.page(
                new Query<SpecialListEntity>().getPage(params),
                new QueryWrapper<SpecialListEntity>()
                        .eq(StringUtils.isNotBlank(id),"special_list_id",id)
                        .like(StringUtils.isNotBlank(customerName),"customer",customerName)
                        .like(StringUtils.isNotBlank(creator),"creator",creator)
                        .like(StringUtils.isNotBlank(createTime),"create_time",createTime)
                        .like(StringUtils.isNotBlank(verifier),"verifier",verifier)
                        .like(StringUtils.isNotBlank(verifierTime),"verifier_time",verifierTime)
                        .eq(StringUtils.isNotBlank(status),"list_status",status)
                        .eq(StringUtils.isNotBlank(listType),"list_type",listType)
                        .orderByDesc("create_time")
        );
        return new PageUtils(page);
    }

    @Override
    public List<VoEntity> getByPhone(Map<String, Object> params) {

        return baseMapper.getByPhone(params);
    }


    @Override
    public List<SpecialListEntity> queryBySel(Map<String, Object> params) {
        return baseMapper.queryBySel(params);
    }

    @Override
    public void deleteBatch(String[] listIds) {
        this.removeByIds(Arrays.asList(listIds));
    }
}
