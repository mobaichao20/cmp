package io.cmp.modules.tel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.modules.tel.entity.CrmChannelTelephoneAgentEntity;
import io.cmp.modules.tel.vo.CrmChannelTelephoneAgentVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 渠道管理电话工号表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-08-01 10:46:46
 */
public interface CrmChannelTelephoneAgentService extends IService<CrmChannelTelephoneAgentEntity> {

    PageUtils queryPage(Map<String, Object> params);

    CrmChannelTelephoneAgentVo queryTelephoneAgentInfoByExtension(Map<String, Object> params);

    CrmChannelTelephoneAgentEntity queryrRandomTelephoneAgentInfo();


}

