package com.landao.hearu.config;


import com.landao.hearu.model.enums.RoleEnum;
import com.landao.hearu.model.user.BaseUserInfoDTO;
import com.landao.hearu.safe.SafeUtil;
import com.landao.hearu.service.IUserRoleService;
import com.landao.hearu.util.RequestLogUtil;
import com.landao.hearu.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 控制器日志切面
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class ControllerLogAspect {

    @Resource
    IUserRoleService iUserRoleService;


    @Around("execution(public * com.landao.hearu.controller.*.*(..))")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        RequestLogUtil.startLog(proceedingJoinPoint);

        if(TokenUtil.hasToken()){//token预处理
            BaseUserInfoDTO baseUserInfo = TokenUtil.parseToken(TokenUtil.getAuthorization());
            TokenUtil.setBaseUserInfo(baseUserInfo);
            Set<RoleEnum> roles = iUserRoleService.getRoles(baseUserInfo.getUserId());
            SafeUtil.setRoles(roles);
        }


        Object proceedResult = proceedingJoinPoint.proceed();

        if(proceedResult!=null){//正常返回
            RequestLogUtil.endLog(proceedResult);
        }

        return proceedResult;
    }


}
