package io.cmp.modules.mma.controller;

import java.util.List;
import java.util.Map;

import io.cmp.modules.mma.vo.ReturnMsg;
import io.cmp.modules.mma.vo.SendMsg;
import io.cmp.modules.mma.vo.SmsFile;
import io.cmp.modules.sys.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.cmp.modules.mma.entity.CrmSmsSendRecordsEntity;
import io.cmp.modules.mma.service.CrmSmsSendRecordsService;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;



/**
 * 短信发送记录表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-26 10:47:53
 */
@RestController
@RequestMapping("mma/crmsmssendrecords")
public class CrmSmsSendRecordsController  extends AbstractController {
    @Autowired
    private CrmSmsSendRecordsService crmSmsSendRecordsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("mma:crmsmssendrecords:list")
    public R findSmsById(@RequestParam Map<String, Object> params){
        PageUtils Msg= crmSmsSendRecordsService.getInfo(params);

        return R.ok().put("page", Msg);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("mma:crmsmssendrecords:info")
    public R info(@PathVariable("id") String id){

		CrmSmsSendRecordsEntity crmSmsSendRecords = crmSmsSendRecordsService.getById(id);

        return R.ok().put("crmSmsSendRecords", crmSmsSendRecords);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("mma:crmsmssendrecords:save")
    public R save(@RequestBody CrmSmsSendRecordsEntity crmSmsSendRecords){
		crmSmsSendRecordsService.save(crmSmsSendRecords);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("mma:crmsmssendrecords:update")
    public R update(@RequestBody CrmSmsSendRecordsEntity crmSmsSendRecords){
		crmSmsSendRecordsService.updateById(crmSmsSendRecords);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("mma:crmsmssendrecords:delete")
    public R delete(@RequestBody String[] ids){
		crmSmsSendRecordsService.updateByIds(ids);

        return R.ok();
    }
    /**
     * 列表
     */

    @PostMapping("/lists")
    public R smsFindById(@RequestBody SmsFile smsFile){
        System.out.println("smsFile---------:"+smsFile);
        List<SmsFile> smsFiles= crmSmsSendRecordsService.findMail(smsFile);
        System.out.println("------------------------smsFiles:"+smsFiles);
        System.out.println("------------------------smsFiles:");
        return R.ok().put("smsFile", smsFiles);
    }
    /**
     * 截取数据信息
     */

    @GetMapping("/changeMsgList/{text}")
    public R changeMsgList(@PathVariable("text") String text){
        List<String> textList = crmSmsSendRecordsService.drawText(text);
        return R.ok().put("textList",textList);
    }

    /**
     * 拼装短信发送信息
     */
    @PostMapping("/sendMsgs")
    public R r(@RequestBody SendMsg sendMsg){
        sendMsg.setSendName(getUser().getRealname());
        sendMsg.setCreateCode(getUser().getDeptId().toString());
 //       ReturnMsg returnMsgs = crmSmsSendRecordsService.findReturnMsgBySendMsg(sendMsg);
        ReturnMsg returnMsges = crmSmsSendRecordsService.findReturnMsgBySendMsgs(sendMsg);
        if(returnMsges!=null){
            if(returnMsges.getFlag()!="0"){
                return R.error(Integer.parseInt(returnMsges.getFlag()),returnMsges.getRetMsg());
            }
        }
        return R.ok().put("returnMsg",returnMsges);
    }
    /**
     * 调用短信发送接口
     */
    @RequestMapping("/sendMessage")
    //@RequiresPermissions("mma:crmsmssendrecords:update")
    public R sendMessage(@RequestParam Map<String, Object> params){
        crmSmsSendRecordsService.sendMessages(params);
        return R.ok();
    }
}
