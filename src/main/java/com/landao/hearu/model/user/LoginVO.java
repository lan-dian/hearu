package com.landao.hearu.model.user;

import com.landao.hearu.entity.User;
import com.landao.hearu.model.enums.RoleEnum;
import com.landao.hearu.util.TokenUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * 显示登陆信息
 */
@Data
@NoArgsConstructor
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


    public LoginVO(User user,Set<RoleEnum> roles){
        this.token=TokenUtil.getToken(user.getId(),user.getName());
        this.name=user.getName();
        this.roles=roles;
    }

}
