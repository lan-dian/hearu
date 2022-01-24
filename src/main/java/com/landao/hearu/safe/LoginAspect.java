package com.landao.hearu.safe;

import com.landao.hearu.model.exception.UnLoginException;
import com.landao.hearu.safe.annotations.RequiredLogin;
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

import java.util.Objects;

@Slf4j
@Aspect
@Order(2)
@Component
public class LoginAspect {

    @Pointcut("@annotation(com.landao.hearu.safe.annotations.RequiredLogin)"
            + "|| @within(com.landao.hearu.safe.annotations.RequiredLogin)")
    public void dsPointCut() {

    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        if(isRequiredLogin(point)){
            if(!TokenUtil.isLogin()){
                throw new UnLoginException();
            }
        }

        return point.proceed();
    }


    public boolean isRequiredLogin(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        RequiredLogin methodAnnotation = AnnotationUtils.findAnnotation(signature.getMethod(), RequiredLogin.class);
        if (Objects.nonNull(methodAnnotation)) {//方法标注了注解
            return true;
        }

        RequiredLogin TypeAnnotation = AnnotationUtils.findAnnotation(signature.getDeclaringType(), RequiredLogin.class);
        return Objects.nonNull(TypeAnnotation);
    }


}
