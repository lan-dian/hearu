package com.landao.hearu.model.common;

import lombok.Data;

/**
 * 统一返回结果
 */
@Data
public class CommonResult<T> {

    /**
     * 状态码
     * @mock 0
     * @apiNote 0:成功
     * -1:操作失败并将错误消息msg反馈给用户
     * -999:系统错误，请让我改bug
     * 1,2,3...:需要特殊处理，我会在文档里面指定
     */
    private int code;

    /**
     * 消息
     * @mock 成功
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public CommonResult() {
        this.code = 0;
        this.msg = "成功";
        this.data = null;
    }

    public CommonResult(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonResult<T> ok(){
        this.code=0;
        this.msg="成功";
        this.data=null;
        return this;
    }

    public CommonResult<T> ok(String msg){
        this.code=0;
        this.msg=msg;
        this.data=null;
        return this;
    }

    public CommonResult<T> body(T data){
        this.code=0;
        this.msg="成功";
        this.data=data;
        return this;
    }

    public CommonResult<T> body(T data,String msg){
        this.code=0;
        this.msg=msg;
        this.data=data;
        return this;
    }

    public CommonResult<T> err(String msg){
        this.code=-1;
        this.msg=msg;
        this.data=null;
        return this;
    }

    public CommonResult<T> err(String msg,T data){
        this.code=-1;
        this.msg=msg;
        this.data=data;
        return this;
    }

    public CommonResult<T> err(){
        this.code=-1;
        this.msg="失败";
        this.data=null;
        return this;
    }

    public CommonResult<T> err(String msg,Integer code){
        this.code=code;
        this.msg=msg;
        this.data=null;
        return this;
    }

    public CommonResult<T> ok(boolean success){
        if(success){
            this.code=0;
            this.msg="成功";
        }else {
            this.code=-1;
            this.msg="失败";
        }
        this.data=null;
        return this;
    }

    public CommonResult<T> ok(boolean success, String msg) {
        if(success){
            this.code=0;
            this.msg="成功";
        }else {
            this.code=-1;
            this.msg=msg;
        }
        this.data=null;
        return this;
    }

}
