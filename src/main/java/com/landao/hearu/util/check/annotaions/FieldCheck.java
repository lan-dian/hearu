package com.landao.hearu.util.check.annotaions;

import com.landao.hearu.util.check.enums.BaseCheck;
import com.landao.hearu.util.check.enums.CheckType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldCheck {

    String value() default "";

    int maxLength() default  0;

    BaseCheck baseCheck() default BaseCheck.Auto;

    CheckType checkType() default CheckType.NoCheck;

}
