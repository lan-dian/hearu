package com.landao.hearu.config;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 控制器日志切面
 */
@Slf4j
@Aspect
@Component
public class ControllerLogAspect {


    @Around("execution(public * com.landao.hearu.controller.*.*(..))")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // RequestLogUtil.startLog(proceedingJoinPoint);


       return proceedingJoinPoint.proceed();

        // if(proceedResult!=null){//正常返回
        //     RequestLogUtil.endLog(proceedResult);
        // }
        //
        // return proceedResult;
    }


}
