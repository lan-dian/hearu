package com.landao.hearu.util.check;

import com.landao.hearu.model.exception.BusinessException;
import com.landao.hearu.util.check.annotaions.FieldCheck;
import com.landao.hearu.util.check.enums.BaseCheck;
import com.landao.hearu.util.check.enums.CheckType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参数检查工具类
 * @apiNote 都是空返回值，让全局异常处理器来处理就行
 */
public class CheckUtil {

    private CheckUtil(){

    }

    private static final String telephoneRegex="1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}";




    public static void checkClass(Object obj){
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            checkField(obj,declaredField);
        }
    }

    public static void checkField(Object obj,Field field){
        FieldCheck fieldCheck = field.getAnnotation(FieldCheck.class);
        if(fieldCheck==null){
            return;
        }
        ReflectionUtils.makeAccessible(field);
        Object value = ReflectionUtils.getField(field, obj);
        baseCheck(field.getType(),fieldCheck,value);
        checkType(field.getType(),fieldCheck,value);
    }


    private static void baseCheck(Class<?> fieldType,FieldCheck fieldCheck,Object value){
        BaseCheck baseCheck = fieldCheck.baseCheck();

        if(baseCheck==BaseCheck.Auto){
            if(isString(fieldType)){//字符型
                baseCheck=BaseCheck.NotBlack;
            }else {
                baseCheck=BaseCheck.NotNull;
            }
        }
        String fieldName = fieldCheck.value();
        switch (baseCheck){
            case NotNull:{
                checkNotNull(value,fieldName);
                break;
            }case NotBlack:{
                if(!isString(fieldType)){
                    throw new BusinessException("该字段不是String类型,不能进行非空判断",-999);
                }
                checkNotBlank((String) value,fieldName);
                break;
            }case CanNull:{
                //do nothing
                break;
            }
            default:{
                throw new BusinessException("未知的类型"+baseCheck+"请补充代码");
            }
        }
    }



    private static boolean isString(Class<?> fieldType){
        return Objects.equals(fieldType,String.class);
    }

    private static boolean isInteger(Class<?> fieldType){
        return Objects.equals(fieldType,Integer.class) || Objects.equals(fieldType,int.class);
    }

    private static boolean isLong(Class<?> fieldType){
        return Objects.equals(fieldType,Long.class) || Objects.equals(fieldType,long.class);
    }


    private static void checkType(Class<?> fieldType,FieldCheck fieldCheck,Object value){
        CheckType checkType = fieldCheck.checkType();
        String fieldName = fieldCheck.value();

        if(checkType==CheckType.Auto){//自动校验的时候才走这样的路
            BaseCheck baseCheck = fieldCheck.baseCheck();
            if(baseCheck==BaseCheck.Auto){
                if(!isString(fieldType)){
                    checkType=CheckType.NoCheck;
                }
                if(isLong(fieldType) || isInteger(fieldType)){
                    checkType=CheckType.NotNegative;
                }
            }
            if(Objects.equals(fieldType,String.class)){
                checkType=CheckType.LengthLimit;//无论如何,string都不应该太长
            }
        }



        if(checkType==CheckType.NoCheck){
            return;
        }
        switch (checkType){
            case NotNegative:{
                if(isInteger(fieldType)){
                    checkNotNegative((Integer) value,fieldName);
                }else if(isLong(fieldType)){
                    checkNotNegative((Long)value, fieldName);
                }else {
                    throw new BusinessException(fieldType.getSimpleName()+"不支持非负判断",-999);
                }
                break;
            }case LengthLimit:{
                if(!isString(fieldType)){
                    throw new BusinessException(fieldType.getSimpleName()+"不支持字符串长度判断",-999);
                }
                checkStringLength((String) value,fieldName,fieldCheck.maxLength());
                break;
            }
        }

    }

    public static void checkTelephone(String telephone){
        if(telephone.length()!=11){
            throw new IllegalArgumentException("电话必须为11位");
        }

        Pattern p = Pattern.compile(telephoneRegex);
        Matcher matcher = p.matcher(telephone);
        if(!matcher.find()){
            throw new IllegalArgumentException("电话格式不合法");
        }
    }

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
