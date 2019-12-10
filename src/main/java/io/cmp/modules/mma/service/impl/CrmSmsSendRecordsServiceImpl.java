package io.cmp.modules.mma.service.impl;

import io.cmp.common.exception.RRException;
import io.cmp.common.utils.Constant;
import io.cmp.modules.cus.entity.CustomerBaseEntity;
import io.cmp.modules.cus.service.CustomerBaseService;
import io.cmp.modules.mma.entity.CrmMediaTemplateEntity;
import io.cmp.modules.mma.service.CrmMediaTemplateService;
import io.cmp.modules.mma.vo.ReturnMsg;
import io.cmp.modules.mma.vo.SendMsg;
import io.cmp.modules.mma.vo.SmsFile;
import io.cmp.modules.sys.service.HttpAPIService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.Query;

import io.cmp.modules.mma.dao.CrmSmsSendRecordsDao;
import io.cmp.modules.mma.entity.CrmSmsSendRecordsEntity;
import io.cmp.modules.mma.service.CrmSmsSendRecordsService;


@Service("crmSmsSendRecordsService")
public class CrmSmsSendRecordsServiceImpl extends  ServiceImpl<CrmSmsSendRecordsDao, CrmSmsSendRecordsEntity> implements CrmSmsSendRecordsService  {
    @Autowired
    private CrmSmsSendRecordsService crmSmsSendRecordsService;
    @Autowired
    private CustomerBaseService customerBaseService;
    @Autowired
    private CrmMediaTemplateService crmMediaTemplateService;
    @Autowired
    private HttpAPIService httpAPIService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CrmSmsSendRecordsEntity> page = this.page(
                new Query<CrmSmsSendRecordsEntity>().getPage(params),
                new QueryWrapper<CrmSmsSendRecordsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public boolean updateByIds(String[] ids) {
        return baseMapper.updateByIds(ids);

    }

    @Override
    public List<SmsFile> findMail(SmsFile smsFile) {
        return baseMapper.findMailBySms(smsFile);
    }

    @Override
    public PageUtils getInfo(Map<String, Object> params) {
        String customerName = (String)params.get("customerName");
        String templateType = (String)params.get("templateType");
        String sendNumber = (String)params.get("sendNumber");
        String content = (String)params.get("content");
        String status = (String)params.get("status");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        String realSendTime = (String) params.get("realSendTime");
        String batchFlag = (String) params.get("batchFlag");
        String returnMsg = (String) params.get("returnMsg");
        IPage<CrmSmsSendRecordsEntity> page = this.page(
                new Query<CrmSmsSendRecordsEntity>().getPage(params),
                new QueryWrapper<CrmSmsSendRecordsEntity>()
                        .like(StringUtils.isNotBlank(customerName),"customer_name", customerName)
                        .like(StringUtils.isNotBlank(templateType),"template_type", templateType)
                        .like(StringUtils.isNotBlank(sendNumber),"send_number", sendNumber)
                        .like(StringUtils.isNotBlank(content),"content", content)
                        .like(StringUtils.isNotBlank(status),"status", status)
                        .like(StringUtils.isNotBlank(realSendTime),"real_send_time", realSendTime)
                   //     .like(StringUtils.isNotBlank(batchFlag),"batch_flag", batchFlag)
                        .eq(StringUtils.isNotBlank(batchFlag),"batch_flag",batchFlag)//判断发送批次标志
                        .like(StringUtils.isNotBlank(returnMsg),"return_msg", returnMsg)
                        .eq("is_delete","0")
                        .between(StringUtils.isNotBlank(endTime), "create_time", startTime, endTime)
                        .apply(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

//        //通过page.getRecords()方法获取到CrmSmsSendRecordsEntityList的信息
//       List<CrmSmsSendRecordsEntity> smsSendRecordslist= page.getRecords();
//       int i = 0;
//        for (CrmSmsSendRecordsEntity smsSendRecord : smsSendRecordslist) {
//                //smsSendRecordList用来存放客户信息的Id
//                List smsSendRecordList = new ArrayList();
//                String smsSendRecordsIds[] = smsSendRecord.getCustomerId().split(",");
//                for (String sendId : smsSendRecordsIds) {
//                    smsSendRecordList.add(sendId);
//                }
//                //存放模板信息Id
//                List MediaTemplateList = new ArrayList();
//                String mediaTemplateIds[] = smsSendRecord.getTemplateId().split(",");
//                for (String mediaId : mediaTemplateIds) {
//                    MediaTemplateList.add(mediaId);
//                }
//                //将模板Id的List进行查询后再set到中
//                List<CrmMediaTemplateEntity> CrmMediaTemplateList = crmMediaTemplateService.findMediaTemplateById(MediaTemplateList);
//                smsSendRecord.setCrmMediaTemplateEntityList(CrmMediaTemplateList);
//                //将客户Id的List进行查询后再set到smsSendRecord.setCustomerBaseEntityList中即可获得所有信息
//                List<CustomerBaseEntity> CusList = customerBaseService.selectBatchIds(smsSendRecordList);
//                for (CustomerBaseEntity customerBaseEntity : CusList) {
//                    if (!customerBaseEntity.getCustomerName().equals("王强")) {
//                        page.getRecords().remove(i);
//                    }
//                }
//                smsSendRecord.setCustomerBaseEntityList(CusList);
//                i++;
//        }
        return new PageUtils(page);
    }

    @Override
    public List<String> drawText(String text) {
        List<String> list=new ArrayList<String>();
        if(!(text.trim().equals(""))&&text!= null){
            Pattern p = Pattern.compile("(\\[[^\\]]*\\])");
            Matcher m = p.matcher(text);
            while(m.find()){
                list.add(m.group().substring(1, m.group().length()-1));
            }
        }
        return list;
    }

    @Override
    public ReturnMsg  findReturnMsgBySendMsg(SendMsg sendMsg) {
        //用来收集调用短信接口的入参
//        SendMsg sendMsg = new SendMsg();
        Map<String,Object> sendMsgMap  = new HashMap<>();
        sendMsg.setCommand("sendSMSMD5");
        sendMsgMap.put("command",sendMsg.getCommand());
        sendMsg.setUsername("zkrtz");
        sendMsgMap.put("username",sendMsg.getUsername());
        sendMsg.setPwd("732f75e3cac6cf101b58f0b5fc93b408");
        sendMsgMap.put("pwd",sendMsg.getPwd());
        //手机号可以是多个
        sendMsgMap.put("mobiles",sendMsg.getSendNumber());
        //短信内容
        String content =  sendMsg.getContent();
        if(sendMsg.getSendFlag()!=null&&sendMsg.getSendFlag().equals("send")){
            //用正则表达式将数据中的[]进行拆分
            String [] patterns = {"\\{([^}]*)\\}"};
            //获取客户姓名
            String cusName =  sendMsg.getCustomerName();
            //获取当前日期
            Date d = new Date();
            System.out.println(d);
            //获取年月日
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //获取时分秒
            SimpleDateFormat sdfd = new SimpleDateFormat("HH:mm:ss");
            String dateNowStr = sdf.format(d);
            String timeNowStrs = sdfd.format(d);
            //获取用户所属机构
            String createCode =  sendMsg.getCreateCode();
            //获取当前用户姓名
            String sendName =  sendMsg.getSendName();
            for (int i = 0; i < patterns.length; i++) {
                Matcher matcher = Pattern.compile(patterns[i]).matcher(content);
                while(matcher.find()){
                    content  = content.replace(matcher.group(), matcher.group().replaceAll("\\{客户姓名\\}", cusName));
                    content  = content.replace(matcher.group(), matcher.group().replaceAll("\\{当前日期\\}", dateNowStr));
                    content  = content.replace(matcher.group(), matcher.group().replaceAll("\\{时间\\}", timeNowStrs));
                    content  = content.replace(matcher.group(), matcher.group().replaceAll("\\{用户所属机构\\}", createCode));
                    content  = content.replace(matcher.group(), matcher.group().replaceAll("\\{当前用户姓名\\}", sendName));
                    System.out.println(content);
                }
            }
            content = "【和佳汇智】"+content;
        }

        System.out.println(content);
        sendMsgMap.put("content",content);
        //字符在传递过程中的编码,与outcode保持一致,GBK或UTF-8
        sendMsg.setIncode("UTF-8");
        sendMsgMap.put("incode",sendMsg.getIncode());
        //字符初始编码与incode保持一致, GBK或UTF-8
        sendMsg.setOutcode("UTF-8");
        sendMsgMap.put("outcode",sendMsg.getOutcode());
        //可选参数。“text”，返回结果为普通字符串格式；
        // “xml”，返回结果为xml格式。不填该参数，则默认为text
        sendMsg.setRstype("");
        sendMsgMap.put("rstype",sendMsg.getRstype());
        //pfex为违禁词过滤开关: 0表示开启 ; 1表示关闭 ; 空表示默认开启
        sendMsg.setPfex("0");
        sendMsgMap.put("pfex",sendMsg.getPfex());
        //扩展码参数只允许数字字符,机构+用户+自定义<=20位。
     //   String ExtCode = "";
        if (sendMsg.getExtCode()!=null&&!sendMsg.getExtCode().equals("")){
     //     sendMsg.setExtCode(ExtCode);
            sendMsgMap.put("extCode",sendMsg.getExtCode());
        }
        //入参结束


        if(sendMsg.getSendFlag()!=null&&sendMsg.getSendFlag().equals("send")){


        //开始调用接口
        String url = "http://smsb.hjhz.net/smsSendServlet.htm";
        ReturnMsg returnMsgs =new ReturnMsg();
        try {
            String returnMsg = httpAPIService.doGet(url,sendMsgMap);
            String flag = returnMsg.substring(0,returnMsg.indexOf("_"));
            String retMsg = returnMsg.substring(returnMsg.indexOf("_")+1);
            System.out.println("returnMsg:"+ returnMsg+"flag:"+flag+"retMsg:"+retMsg);

            //返回的的参数添加到ReturnMsg中
            returnMsgs.setFlag(flag);
            returnMsgs.setRetMsg(retMsg);
            CrmSmsSendRecordsEntity crmSmsSendRecords = new CrmSmsSendRecordsEntity();
            if (flag!=null&&!flag.equals("")){
                if(sendMsg.getId()!=null){
                    crmSmsSendRecords = crmSmsSendRecordsService.getById(sendMsg.getId());
                    crmSmsSendRecords.setId(sendMsg.getId());
                }
                boolean sendFlag = true;
                if(crmSmsSendRecords.getStatus()!=null&&crmSmsSendRecords.getStatus().equals("1")){
                    sendFlag = false;
                    crmSmsSendRecords.setId(null);
                }
                crmSmsSendRecords.setSendNumber(sendMsg.getSendNumber());
                crmSmsSendRecords.setTemplateId(sendMsg.getTemplateName());
                if (flag.equals("0")){
                    crmSmsSendRecords.setStatus("1");//成功
                }else {
                    crmSmsSendRecords.setStatus("2");//失败
                }
                crmSmsSendRecords.setCustomerId(sendMsg.getCusNameId());
                crmSmsSendRecords.setIsDelete("0");//是否删除  0否1是
                crmSmsSendRecords.setContent(content);//放入内容
                crmSmsSendRecords.setIsDelivery("0");//是否定时发送 0:否 1:是
                crmSmsSendRecords.setCreateName(sendMsg.getSendName());
                crmSmsSendRecords.setCreateCode(sendMsg.getCreateCode());
                crmSmsSendRecords.setCreateTime(new Date());
                crmSmsSendRecords.setCustomerName(sendMsg.getCustomerName());
                crmSmsSendRecords.setTemplateType("1");
                if(sendMsg.getId()!=null&&sendFlag){
                    crmSmsSendRecordsService.updateById(crmSmsSendRecords);;
                }else{
                    crmSmsSendRecordsService.save(crmSmsSendRecords);
                }

            }
            return returnMsgs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RRException("返回数据错误请修正传参");
        }
        }else{
            CrmSmsSendRecordsEntity crmSmsSendRecords = new CrmSmsSendRecordsEntity();
            if(sendMsg.getId()!=null) {
                crmSmsSendRecords = crmSmsSendRecordsService.getById(sendMsg.getId());
                crmSmsSendRecords.setId(sendMsg.getId());
            }
                crmSmsSendRecords.setSendNumber(sendMsg.getSendNumber());
                crmSmsSendRecords.setTemplateId(sendMsg.getTemplateName());
                crmSmsSendRecords.setCustomerId(sendMsg.getCusNameId());
                crmSmsSendRecords.setIsDelete("0");//是否删除  0否1是
                crmSmsSendRecords.setContent(content);//放入内容
                crmSmsSendRecords.setIsDelivery("0");//是否定时发送 0:否 1:是
                crmSmsSendRecords.setCreateName(sendMsg.getSendName());
                crmSmsSendRecords.setStatus("3");//暂存
                crmSmsSendRecords.setCreateCode(sendMsg.getCreateCode());
                crmSmsSendRecords.setCreateTime(new Date());
                crmSmsSendRecords.setCustomerName(sendMsg.getCustomerName());
                crmSmsSendRecords.setTemplateType("1");
                if(sendMsg.getId()!=null&&!sendMsg.getSendFlag().equals("1")){
                    crmSmsSendRecordsService.updateById(crmSmsSendRecords);
                }else{
                    crmSmsSendRecordsService.save(crmSmsSendRecords);
                }
            return null;
            }



    }

    @Override
    public ReturnMsg findReturnMsgBySendMsgs(SendMsg sendMsg) {
        String sendNumbers = sendMsg.getSendNumber();
        String[] sendNumberes = sendNumbers.split(",");
        String sendCusName = sendMsg.getCustomerName();
        String[] sendCusNames = sendCusName.split(",");
        String sendCusId = sendMsg.getCustomerName();
        String[] sendCusIds = sendCusId.split(",");
        //定义一个ID用来标示同一批次的内容规则是当前时间精确到毫秒
        String idFlag = new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date() );
        for (int i = 0; i < sendNumberes.length; i++) {
            Map<String, Object> sendMsgMap = new HashMap<>();
            //获取当前用户姓名
            String sendName = sendMsg.getSendName();
            //获取当前日期
            Date d = new Date();
            System.out.println(d);
            //获取年月日
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //获取时分秒
            SimpleDateFormat sdfd = new SimpleDateFormat("HH:mm:ss");
            String dateNowStr = sdf.format(d);
            String timeNowStrs = sdfd.format(d);
            //获取用户所属机构
            String createCode = sendMsg.getCreateCode();
            String cusName = sendCusNames[i];//客户姓名
            //用正则表达式将数据中的[]进行拆分
            String[] patterns = {"\\{([^}]*)\\}"};
            String content = sendMsg.getContent();
            for (int z = 0; z < patterns.length; z++) {
                Matcher matcher = Pattern.compile(patterns[z]).matcher(content);
                while (matcher.find()) {
                    content = content.replace(matcher.group(), matcher.group().replaceAll("\\{客户姓名\\}", cusName));
                    content = content.replace(matcher.group(), matcher.group().replaceAll("\\{当前日期\\}", dateNowStr));
                    content = content.replace(matcher.group(), matcher.group().replaceAll("\\{时间\\}", timeNowStrs));
                    content = content.replace(matcher.group(), matcher.group().replaceAll("\\{用户所属机构\\}", createCode));
                    content = content.replace(matcher.group(), matcher.group().replaceAll("\\{当前用户姓名\\}", sendName));
                    System.out.println(content);
                }
            }
            content = "【和佳汇智】" + content;
            sendMsgMap.put("content", content);
            CrmSmsSendRecordsEntity crmSmsSendRecords = new CrmSmsSendRecordsEntity();
            crmSmsSendRecords.setSendNumber(sendNumberes[i]);
            crmSmsSendRecords.setTemplateId(sendMsg.getTemplateName());//这个是ID模板的记得改
            crmSmsSendRecords.setCustomerId(sendCusIds[i]);
            crmSmsSendRecords.setIsDelete("0");//是否删除  0否1是
            crmSmsSendRecords.setContent(content);//放入内容
            crmSmsSendRecords.setIsDelivery("0");//是否定时发送 0:否 1:是
            crmSmsSendRecords.setCreateName(sendMsg.getSendName());
            crmSmsSendRecords.setCreateCode(sendMsg.getCreateCode());
            crmSmsSendRecords.setCreateTime(new Date());
            crmSmsSendRecords.setCustomerName(sendCusNames[i]);
            crmSmsSendRecords.setTemplateType("1");
            crmSmsSendRecords.setStatus("3");
            crmSmsSendRecords.setBatchFlag(idFlag);
            crmSmsSendRecords.setSendNumber(sendNumberes[i]);
            //判断是否是定时发送，如果是就从前台传过来的时间放进去，若不是就赋值当前时间
            if(sendMsg.getDeliveryTime()!=null){
                DateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                try {
                    Date date =  formatter.parse(sendMsg.getDeliveryTime());
                crmSmsSendRecords.setDeliveryTime(date);   //这里记得更换传来的定时发送时间
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                crmSmsSendRecords.setDeliveryTime(new Date());
            }
            crmSmsSendRecordsService.save(crmSmsSendRecords);
        }
        return null;
    }
//调用短信接口
    @Override
    public void sendMessages(Map<String, Object>params) {

      //  params.put("limit",20);
        IPage<CrmSmsSendRecordsEntity> page = this.page(
                new Query<CrmSmsSendRecordsEntity>().getPage(params),
                new QueryWrapper<CrmSmsSendRecordsEntity>()
                        .eq("status", "3")
                        .eq("is_delete","0")//删除的标记为0是假删除
        );
        //通过page.getRecords()方法获取到CrmSmsSendRecordsEntityList的信息
       List<CrmSmsSendRecordsEntity> smsSendRecordslist = page.getRecords();
        for (CrmSmsSendRecordsEntity CrmSmsSendRecord : smsSendRecordslist) {
           //判断预计发送时间是否小于等于当前时间如果是就执行发送任务
            if(CrmSmsSendRecord.getDeliveryTime()!=null&&CrmSmsSendRecord.getDeliveryTime().getTime()<=new Date().getTime()){
                Map<String, Object> sendMsgMap = new HashMap<>();
                SendMsg sendMsg = new SendMsg();
               //这里是网关
                sendMsg.setCommand("sendSMSMD5");
                sendMsgMap.put("command", sendMsg.getCommand());
                sendMsg.setUsername("zkrtz");
                sendMsgMap.put("username", sendMsg.getUsername());
                sendMsg.setPwd("732f75e3cac6cf101b58f0b5fc93b408");
                sendMsgMap.put("pwd", sendMsg.getPwd());
                sendMsgMap.put("mobiles",CrmSmsSendRecord.getSendNumber());
                //字符在传递过程中的编码,与outcode保持一致,GBK或UTF-8
                sendMsg.setIncode("UTF-8");
                sendMsgMap.put("incode", sendMsg.getIncode());
                //字符初始编码与incode保持一致, GBK或UTF-8
                sendMsg.setOutcode("UTF-8");
                sendMsgMap.put("outcode", sendMsg.getOutcode());
                //可选参数。“text”，返回结果为普通字符串格式；
                // “xml”，返回结果为xml格式。不填该参数，则默认为text
                sendMsg.setRstype("");
                sendMsgMap.put("rstype", sendMsg.getRstype());
                //pfex为违禁词过滤开关: 0表示开启 ; 1表示关闭 ; 空表示默认开启
                sendMsg.setPfex("0");
                sendMsgMap.put("pfex", sendMsg.getPfex());
                //扩展码参数只允许数字字符,机构+用户+自定义<=20位。
                //   String ExtCode = "";
                if (sendMsg.getExtCode() != null && !sendMsg.getExtCode().equals("")) {
                    //     sendMsg.setExtCode(ExtCode);
                    sendMsgMap.put("extCode", sendMsg.getExtCode());
                }
                sendMsgMap.put("content",CrmSmsSendRecord.getContent());
                //调用短信接口
                String  returnMsg = "";
                String url = "http://smsb.hjhz.net/smsSendServlet.htm";
                try {
                    returnMsg = httpAPIService.doGet(url, sendMsgMap);
                    String flag = returnMsg.substring(0, returnMsg.indexOf("_"));
                    String retMsg = returnMsg.substring(returnMsg.indexOf("_") + 1);
                    System.out.println("returnMsg:" + returnMsg + "flag:" + flag + "retMsg:" + retMsg);
                    //继续对短信发送表中的数据进行添加
                    CrmSmsSendRecord.setRealSendTime(new Date());
                    CrmSmsSendRecord.setReturnMsg(returnMsg);
                    if(flag!=null&&flag.equals("0")){
                        CrmSmsSendRecord.setStatus("1");//发送成功
                    }else{
                        CrmSmsSendRecord.setStatus("2");//发送失败
                    }
                    crmSmsSendRecordsService.updateById(CrmSmsSendRecord);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}