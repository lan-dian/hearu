package com.landao.hearu.util;


import org.apache.commons.lang3.StringUtils;


/**
 * 参数检查工具类
 * @apiNote 都是空返回值，让全局异常处理器来处理就行
 */
public class CheckUtil {

    private CheckUtil(){

    }

    private static final String telephoneRegex="1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}";



    public static void checkStringLength(String str,String fieldName,Integer maxLength){
        if(str==null){
            return;
        }
        if(str.length()>maxLength){
            throw new IllegalArgumentException(fieldName+"长度不能超过"+maxLength);
        }
    }

    /**
     * 验证一个值不能为负数
     * @apiNote 可以为0
     * @param num 被验证的数
     */
    public static void checkNotNegative(Integer num,String filedName){
        if(num==null){
            return;
        }
        if(num.compareTo(0)<0){
            throw new IllegalArgumentException(filedName+"必须为非负数");
        }
    }

    public static void checkNotNegative(Long num,String filedName){
        if(num==null){
            return;
        }
        if(num.compareTo(0L)<0){
            throw new IllegalArgumentException(filedName+"必须为非负数");
        }
    }

    public static void checkNotBlank(String str,String filedName){
        if(StringUtils.isBlank(str)){
            throw new IllegalArgumentException(filedName+"不能为空");
        }
    }

    /**
     * 检查一个值是否为空
     * @param obj 值
     * @param fieldName 字段名称
     */
    public static void checkNotNull(Object obj,String fieldName){
        if(obj==null){
            throw new IllegalArgumentException(fieldName+"不能为空");
        }
    }

    public static void checkUpdateId(Long id){
        if(id==null){
            throw new IllegalArgumentException("修改时必须传递id");
        }
        checkId(id);
    }

    public static void checkUpdateId(Integer id){
        if(id==null){
            throw new IllegalArgumentException("修改时必须传递id");
        }
        checkId(id);
    }

    /**
     * 如果id为空或者不大于0会抛出异常
     * @param id 主键id
     */
    public static void checkId(Long id){
        if(id==null || id<=0){
            throw new IllegalArgumentException("id不合法");
        }
    }

    /**
     * 如果id为空或者不大于0会抛出异常
     * @param id 主键id
     */
    public static void checkId(Integer id){
        if(id==null || id<=0){
            throw new IllegalArgumentException("id不合法");
        }
    }




}
