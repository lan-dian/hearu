package com.landao.hearu.safe.annotations;

import com.landao.hearu.model.enums.RoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface RequiredRole {

    RoleEnum[] requiredRole() default {};

}
