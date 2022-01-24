package com.landao.hearu.controller;

import com.landao.hearu.business.UserService;
import com.landao.hearu.model.common.CommonResult;
import com.landao.hearu.model.enums.RoleEnum;
import com.landao.hearu.model.user.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户相关
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 用户注册
     *
     * @param userInfo 用户信息
     */
    @PostMapping("/register")
    public CommonResult<Void> register(@RequestBody UserInfo userInfo) {
        CommonResult<Void> result = new CommonResult<>();
        userInfo.addCheck();

        boolean save = userService.registerUser(userInfo, RoleEnum.Ordinary);

        return result.ok(save);
    }

}
