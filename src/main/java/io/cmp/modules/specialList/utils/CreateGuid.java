package io.cmp.modules.specialList.utils;

import java.util.UUID;

/**
 * 生成GUID全球唯一标识
 */
public class CreateGuid {

    /**
     * 生成GUID
     * @param modelName GUID前缀
     * @return  GUID
     */
    public static String create(String modelName){

        UUID uuid = UUID.randomUUID();

        return modelName+"-"+uuid.toString();
    }
}
