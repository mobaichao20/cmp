package io.cmp.modules.specialList.controller;
import io.cmp.common.utils.PageUtils;
import io.cmp.common.utils.R;
import io.cmp.modules.specialList.config.Log;
import io.cmp.modules.specialList.entity.SpecialListEntity;
import io.cmp.modules.specialList.vo.VoEntity;
import io.cmp.modules.specialList.service.SpecialListService;
import io.cmp.modules.specialList.utils.CreateGuid;
import io.cmp.modules.sys.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 特殊名单
 */
@RestController
@RequestMapping("special")
public class SpecialListController  extends AbstractController {
    @Autowired
    private SpecialListService specialListService;

    /**
     * 列表
     * @return 返回名单列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = specialListService.queryPage(params);
        return R.ok().put("page",page);
    }
    /**
     * 保存名单
     * @param params 名单
     * @return
     */
    @Log("保存名单")
    @PostMapping("/save")
    public R save(@RequestParam Map<String, Object> params){
        SpecialListEntity list = new SpecialListEntity();
            list .setId(CreateGuid.create("sp"));
            list.setCustomer((String)params.get("customer"));
            list.setCreator(getUser().getUsername());  //获取当前登录用户
            list.setCreateTime(new Date());
            list.setFailureTime((String)params.get("failureTime"));
            list.setListType((String)params.get("listType"));
            list.setModifier(getUser().getUsername());
            specialListService.save(list);
        return R.ok();
    }
    /**
     * 修改名单
     * @param list 名单
     * @return
     */
    @Log("修改名单")
    @GetMapping("/update")
     public R update(SpecialListEntity list){
        list.setVerifier(getUser().getUsername());
        list.setVerifierTime(new Date());
        specialListService.updateById(list);
        return R.ok();
     }

    /**
     * 删除名单
     * @param list 名单id
     * @return
     */
    @Log("删除名单")
     @GetMapping("/delete")
     public R delete(SpecialListEntity list){
         specialListService.removeById(list);
        return R.ok();
     }

    /**
     * 批量删除
     * @param listIds id
     * @return
     */
     @Log("批量删除")
     @PostMapping("/delList")
     public R delList(@RequestBody String[] listIds){
         specialListService.deleteBatch(listIds);
         return R.ok();
     }

     /**
     * 条件查询  根据客户类型查询
     * @return 返回名单列表
     */
    @Log(" 根据客户类型查询")
    @GetMapping("/queryByCondition")
    public R queryByCondition(@RequestParam Map<String, Object> params){
        List<VoEntity> list =  specialListService.getByPhone(params);
        return R.ok().put("list",list);
    }
    /**
     * 没选择客户类型时查询
     * @return 返回名单列表
     */
    @Log(" 没选择客户类型时查询")
    @GetMapping("/queryBySel")
    public R queryBySel(@RequestParam Map<String, Object> params){
        List<SpecialListEntity> list =  specialListService.queryBySel(params);
        return R.ok().put("list",list);
    }
}
