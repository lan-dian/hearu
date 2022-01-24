package com.landao.hearu.safe;

import com.landao.hearu.model.enums.RoleEnum;
import com.landao.hearu.model.exception.UnAuthorizationException;
import com.landao.hearu.model.exception.UnLoginException;
import com.landao.hearu.safe.annotations.RequiredRole;
import com.landao.hearu.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Order(3)
@Component
public class RoleAspect {

    @Pointcut("@annotation(com.landao.hearu.safe.annotations.RequiredRole)"
            + "|| @within(com.landao.hearu.safe.annotations.RequiredRole)")
    public void dsPointCut() {

    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        if(!TokenUtil.isLogin()){//前提肯定是登陆
            throw new UnLoginException();
        }

        Set<RoleEnum> requiredRole = getRequiredRole(point);
        if(!requiredRole.isEmpty()){
            boolean permission = SafeUtil.hasPermission(requiredRole);
            if(!permission){
                RoleEnum[] roleEnum=new RoleEnum[requiredRole.size()];
                throw new UnAuthorizationException(requiredRole.toArray(roleEnum));
            }
        }


        return point.proceed();
    }


    public Set<RoleEnum> getRequiredRole(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        RequiredRole methodAnnotation = AnnotationUtils.findAnnotation(signature.getMethod(), RequiredRole.class);
        if (Objects.nonNull(methodAnnotation)) {//方法标注了注解
            return Arrays.stream(methodAnnotation.requiredRole()).collect(Collectors.toSet());
        }

        RequiredRole typeAnnotation = AnnotationUtils.findAnnotation(signature.getDeclaringType(), RequiredRole.class);
        if(Objects.nonNull(typeAnnotation)){
            return Arrays.stream(typeAnnotation.requiredRole()).collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }


}
