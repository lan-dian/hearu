package com.landao.hearu.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 把对象转换为json字符串
 */
@Component//必须加这个注解让spring扫描到
public class JsonUtil {

    public static ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper){
        JsonUtil.objectMapper=objectMapper;
    }

    public static String parse(Object obj){
        if(obj==null){
            throw new NullPointerException("待转换的对象不能为空");
        }
        String res=null;
        try {
            res=objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            res=obj.toString();
        }
        return res;
    }


}
