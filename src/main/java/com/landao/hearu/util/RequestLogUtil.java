package com.landao.hearu.util;

import com.landao.hearu.entity.RequestLog;
import com.landao.hearu.model.common.LogDTO;
import com.landao.hearu.service.IRequestLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志处理工具类
 */
@Component
public class RequestLogUtil {

    private static IRequestLogService iRequestLogService;

    @Autowired
    public void setIRequestLogService(IRequestLogService iRequestLogService){
        RequestLogUtil.iRequestLogService=iRequestLogService;
    }

    public static void startLog(ProceedingJoinPoint proceedingJoinPoint){
        long startTime = System.currentTimeMillis();//统计时间

        HttpServletRequest request = NewxWebUtil.getRequest();

        LogDTO logDTO=new LogDTO();
        logDTO.setStartTime(startTime);
        //设置基本信息
        logDTO.setUrl(request.getRequestURI());
        Signature signature = proceedingJoinPoint.getSignature();
        logDTO.setClassMethod(signature.getDeclaringTypeName().substring(28)+"."+signature.getName());

        //设置
        Object[] args = proceedingJoinPoint.getArgs();
        logDTO.setArgs(JsonUtil.parse(args));

        RequestLogUtil.setLogDTO(logDTO);//把它保存起来
    }

    public static void endLog(Object obj){
        LogDTO logDTO = getLogDTO();
        //设置返回内容
        logDTO.setReturns(JsonUtil.parse(obj));
        //设置用户信息
        logDTO.setUserInfo();

        RequestLog requestLog = new RequestLog(logDTO);
        // try {
            iRequestLogService.save(requestLog);
        // }catch (Exception e){
        //     //未知异常
        // }
    }

    public static void setLogDTO(LogDTO logDTO){
        NewxWebUtil.setAttribute("log",logDTO);
    }

    public static LogDTO getLogDTO(){
        return (LogDTO) NewxWebUtil.getAttribute("log");
    }

}
