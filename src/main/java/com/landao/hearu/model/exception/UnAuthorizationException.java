package com.landao.hearu.model.exception;


import com.landao.hearu.model.enums.RoleEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnAuthorizationException extends RuntimeException{

    /**
     * 需要的角色,满足一个即可
     */
    private final RoleEnum[] requiredRole;

    public UnAuthorizationException(RoleEnum... requiredRole){
        this.requiredRole = requiredRole;
    }

    public String getDescription(){
        return "你得至少拥有"+Arrays.toString(requiredRole)+"其中的一个角色才可以访问";
    }

}
