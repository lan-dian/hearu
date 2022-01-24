package com.landao.hearu.model.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务内部异常
 * @apiNote 业务出现非法数据或业务无法正常返回
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException{

    private Integer code;

    private String msg;

    private Object data;

    public BusinessException(String msg){
        this.code=-1;
        this.msg=msg;
        this.data=null;
    }


    public BusinessException(Object data,String msg,Integer code){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }


}
