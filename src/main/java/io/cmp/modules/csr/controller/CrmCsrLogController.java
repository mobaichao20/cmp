package io.cmp.modules.csr.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.csr.entity.CrmCsrLog;
import io.cmp.modules.csr.service.CrmCsrLogService;
import io.cmp.modules.csr.vo.CrmCsrLogVO;
import io.cmp.modules.cus.service.CustomerBaseService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

//@CrossOrigin
@RestController
@RequestMapping("csr/csrlog")
public class CrmCsrLogController {

    @Autowired
    private CrmCsrLogService crmCsrLogService;
    @Autowired
    CustomerBaseService customerBaseService;

    /**
     * 列表
     */
    @ApiOperation(value ="展示满意度数据", httpMethod ="GET", notes ="  展示数据")
    @RequestMapping("/list")
//    @RequiresPermissions("csr:csrlog:li`st")
    public R AllList(@RequestParam Map<String, Object> params){

            PageUtils page =  crmCsrLogService.queryPage(params);

        return R.ok().put("page", page);
    }



    /**
     * 保存
     * 入参： 客户id  坐席名（暂定） 会话id  渠道id 满意度
     * 返回： OK消息
     */
    @RequestMapping("/save")
    @RequiresPermissions("csr:csrlog:save")
    public R save(@RequestBody CrmCsrLog crmCsrLog){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String dateNow = dateFormat.format(date);
        ParsePosition pos = new ParsePosition(0);
        crmCsrLog.setCreateTime(dateFormat.parse(dateNow,pos));                //获取当前时间
        crmCsrLog.setCustomerName(customerBaseService.getById(crmCsrLog.getCustomerId()).getCustomerName());  //根据id获得客户名

        /*
        *  待完成
        * 根据渠道ID    工号ID获得 坐席名    加入对象
        * */

        crmCsrLogService.save(crmCsrLog);

        return R.ok();
    }

    /**
     * 输出详细信息
     * @return  满意度数据表
     */
    public List<CrmCsrLogVO> getAll(){
        return crmCsrLogService.getAll();
    }
}
