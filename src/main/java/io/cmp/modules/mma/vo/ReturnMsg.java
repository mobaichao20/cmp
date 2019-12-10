package io.cmp.modules.mma.vo;


import lombok.Data;

/**
 * 短信接口返回信息接收
 */
@Data
public class ReturnMsg {
    //返回标示位
    private String flag;
    //返回的信息
    private String retMsg;
}
