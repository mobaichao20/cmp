package io.cmp.modules.cus.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.cus.entity.CustomerBaseEntity;
import io.cmp.modules.cus.entity.FieldConfigurationEntity;
import io.cmp.modules.cus.service.CustomerBaseService;
import io.cmp.modules.cus.service.FieldConfigurationService;
import io.cmp.modules.cus.utils.ExportExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 客户信息表
 *
 * @author liwenxuan
 * @email liwenxuan@sinosoft.com.cn
 * @date 2019-07-02 15:13:26
 */
@Controller
@RequestMapping("cus/customerbaseexcle")
public class CustomerBaseExcleController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerBaseExcleController.class);

    @Autowired
    private CustomerBaseService customerBaseService;

    @Autowired
    private FieldConfigurationService fieldConfigurationService;

    /**
     * 导出选中客户信息列表
     */
    @CrossOrigin(origins = "*")
    @RequestMapping("/exportSelectList")
    public R exportSelectList(@RequestBody String[] ids,HttpServletResponse response){
        List<CustomerBaseEntity> customerBaseList = customerBaseService.selectList(ids);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("sidx","list_num");
        //params.put("order","asc");
        params.put("type","crm_customer_base");
        params.put("isDelete","0");
        //params.put("isEnable","1");
        //params.put("isListfield","1");

        List<FieldConfigurationEntity> fieldConfigurationList = fieldConfigurationService.queryList(params);

        //定义导出的excel名字
        String excelName = "客户信息表";

        //获取需要转出的excel表头的map字段
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();

        for(FieldConfigurationEntity fieldConfigurationEntity:fieldConfigurationList) {
            fieldMap.put(fieldConfigurationEntity.getDataAttribute(), fieldConfigurationEntity.getDisplayName());
        }

        //导出用户相关信息
        ExportExcelUtils.export(excelName,customerBaseList,fieldMap,response);

        return R.ok();
    }

    /**
     * 按条件导出客户信息列表
     */
    @CrossOrigin(origins = "*")
    @RequestMapping("/exportQueryList")
    public R exportQueryList(@RequestParam Map<String, Object> params,HttpServletResponse response){
        List<CustomerBaseEntity> customerBaseList = customerBaseService.queryList(params);

        Map<String, Object> params2 = new HashMap<String, Object>();
        params2.put("sidx","list_num");
        //params2.put("order","asc");
        params2.put("type","crm_customer_base");
        params2.put("isDelete","0");

        List<FieldConfigurationEntity> fieldConfigurationList = fieldConfigurationService.queryList(params2);

        //定义导出的excel名字
        String excelName = "客户信息表";

        //获取需要转出的excel表头的map字段
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();

        for(FieldConfigurationEntity fieldConfigurationEntity:fieldConfigurationList) {
            fieldMap.put(fieldConfigurationEntity.getDataAttribute(), fieldConfigurationEntity.getDisplayName());
        }

        //导出用户相关信息
        ExportExcelUtils.export(excelName,customerBaseList,fieldMap,response);
        return R.ok();
    }


    /**
     * 导出全部客户信息列表
     */
    @CrossOrigin(origins = "*")
    @RequestMapping("/exportAllList")
    public R exportAllList(@RequestParam Map<String, Object> params,HttpServletResponse response){
        List<CustomerBaseEntity> customerBaseList = customerBaseService.queryList(params);

        Map<String, Object> params2 = new HashMap<String, Object>();
        params2.put("sidx","list_num");
        //params2.put("order","asc");
        params2.put("type","crm_customer_base");
        params2.put("isDelete","0");

        List<FieldConfigurationEntity> fieldConfigurationList = fieldConfigurationService.queryList(params2);

        //定义导出的excel名字
        String excelName = "客户信息表";

        //获取需要转出的excel表头的map字段
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();

        for(FieldConfigurationEntity fieldConfigurationEntity:fieldConfigurationList) {
            fieldMap.put(fieldConfigurationEntity.getDataAttribute(), fieldConfigurationEntity.getDisplayName());
        }

        //导出用户相关信息
        ExportExcelUtils.export(excelName,customerBaseList,fieldMap,response);
        return R.ok();
    }


}
