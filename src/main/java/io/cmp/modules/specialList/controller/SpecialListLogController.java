package io.cmp.modules.specialList.controller;

import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.specialList.service.SpecialListLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 特殊名单日志
 */
@RestController
@RequestMapping("special/log")
public class SpecialListLogController {
    @Autowired
    private SpecialListLogService specialListLogService;

    /**
     * 列表
     * @return
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = specialListLogService.queryPage(params);
        return R.ok().put("page",page);
    }
}
