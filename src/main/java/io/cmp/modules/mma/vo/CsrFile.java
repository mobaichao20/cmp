package io.cmp.modules.mma.vo;

import lombok.Data;

//客户临时数据
@Data
public class CsrFile {
    //当前登录人
    private String  lander;
    //当前登陆机构
    private String  organ;
    //当前时间
    private String time;
    //当前日期
    private String date;
}
