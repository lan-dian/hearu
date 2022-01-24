package com.landao.hearu.safe;

import com.landao.hearu.model.enums.RoleEnum;
import com.landao.hearu.util.NewxWebUtil;

import java.util.Set;

/**
 * 认证工具类
 */
public class SafeUtil {


    public static void setRoles(Set<RoleEnum> roles){
        NewxWebUtil.setAttribute("roles",roles);
    }

    @SuppressWarnings("all")
    public static Set<RoleEnum> getRoles(){
        return (Set<RoleEnum>) NewxWebUtil.getAttribute("roles");
    }

    /**
     * 是否拥有其中一个角色
     * @param requiredRoles 需要的角色
     */
    public static boolean hasPermission(Set<RoleEnum> requiredRoles){
        for (RoleEnum role : getRoles()) {
            if(requiredRoles.contains(role)){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否具有该权限
     * @param roleEnum 待判断的权限
     */
    public static boolean hasRole(RoleEnum roleEnum){
        return getRoles().contains(roleEnum);
    }


}
