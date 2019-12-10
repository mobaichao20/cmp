package io.cmp.modules.mma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.mma.entity.CrmSmsSendRecordsEntity;
import io.cmp.modules.mma.vo.ReturnMsg;
import io.cmp.modules.mma.vo.SendMsg;
import io.cmp.modules.mma.vo.SmsFile;

import java.util.List;
import java.util.Map;

/**
 * 短信发送记录表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
public interface CrmSmsSendRecordsService extends IService<CrmSmsSendRecordsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public boolean updateByIds(String[] ids);

    List<SmsFile> findMail(SmsFile smsFile);
//对短信查询的集合进行添加为了前台查询界面的展示用
    PageUtils getInfo(Map<String, Object> params);

    List<String> drawText(String text);
//用来接受短信接口的返回信息
    ReturnMsg  findReturnMsgBySendMsg(SendMsg sendMsg);

    ReturnMsg findReturnMsgBySendMsgs(SendMsg sendMsg);

    void sendMessages(Map<String, Object>params);
}

