package com.landao.hearu.model.user;

import com.landao.hearu.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * 显示登陆信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {

    /**
     * Bearer token
     */
    private String token;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户角色
     */
    private Set<RoleEnum> roles;

}
