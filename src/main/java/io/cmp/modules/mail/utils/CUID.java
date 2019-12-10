package io.cmp.modules.mail.utils;

import java.util.UUID;

/**
 * 系统主键生成类
 *
 */
public class CUID {

    /**生成主键,入参自动加到前边*/
    public static String createUUID(String rex){
        String uuid = UUID.randomUUID() + "";
        return rex + uuid.replaceAll("-", "");
    }

    /**生成主键*/
    public static String createUUID(){
        String uuid = UUID.randomUUID() + "";
        return uuid.replaceAll("-", "");
    }
}
