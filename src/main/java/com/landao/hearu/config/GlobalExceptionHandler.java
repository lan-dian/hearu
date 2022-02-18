package com.landao.hearu.config;


import com.landao.checker.model.collection.IllegalsHolder;
import com.landao.checker.model.exception.CheckIllegalException;
import com.landao.guardian.exception.author.UnAuthorizationException;
import com.landao.guardian.exception.author.UnLoginException;
import com.landao.hearu.model.common.CommonResult;
import com.landao.hearu.model.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 未登录
     */
    @ExceptionHandler(UnLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CommonResult<Void> unLoginHandler(UnLoginException e){
        CommonResult<Void> result=new CommonResult<>();
        return result.err(e.getMessage());
    }

    /**
     * 权限不足
     */
    @ExceptionHandler(UnAuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CommonResult<String> unAuthorizationHandler(UnAuthorizationException e){
        CommonResult<String> result=new CommonResult<>();
        return result.body(e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public CommonResult<Object> serviceHandler(BusinessException e){
        return new CommonResult<>(e.getCode(), e.getMsg(), e.getData());
    }

    /**
     * 参数不合法
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult<Void> illegalArgumentHandler(IllegalArgumentException e){
        CommonResult<Void> result = new CommonResult<>();
        return  result.err(e.getMessage());
    }

    @ExceptionHandler(CheckIllegalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult<IllegalsHolder> inspectIllegalHandler(CheckIllegalException e){
        CommonResult<IllegalsHolder> result=new CommonResult<>();
        return result.err().setData(e.getIllegalList());
    }

    /**
     * 必须传递的参数没有传递
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CommonResult<Void> missingParameterHandler(MissingServletRequestParameterException e){
        CommonResult<Void> result = new CommonResult<>();
        return result.err(e.getParameterName()+"未传递");
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Throwable.class)
	public CommonResult<Void> exceptionHandler(Throwable e){
        CommonResult<Void> result = new CommonResult<>();

        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement element : stackTrace) {
            if(element.getClassName().startsWith("com.landao")){
                log.error("类:{} 方法:{} 行:{}",element.getClassName(),element.getMethodName(),element.getLineNumber());
            }
        }
        return result.err(e.getMessage(),-999);
    }

}