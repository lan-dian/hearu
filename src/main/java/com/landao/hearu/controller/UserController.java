package com.landao.hearu.controller;

import com.landao.hearu.business.UserService;
import com.landao.hearu.model.common.CommonResult;
import com.landao.hearu.model.enums.RoleEnum;
import com.landao.hearu.model.user.LoginVO;
import com.landao.hearu.model.user.UserInfo;
import com.landao.hearu.model.user.UserInfoVO;
import com.landao.hearu.safe.annotations.RequiredLogin;
import com.landao.hearu.util.TokenUtil;
import com.landao.hearu.util.check.CheckUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * 用户相关
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 获取用户基本信息
     */
    @RequiredLogin
    @GetMapping("/info")
    public CommonResult<UserInfoVO> info(){
        CommonResult<UserInfoVO> result=new CommonResult<>();

        UserInfoVO userInfo = userService.getUserInfo(TokenUtil.getUserId());

        return result.body(userInfo);
    }

    /**
     * 用户登陆
     * @param telephone 电话号码|13034744809
     * @param password 密码(md5加密)|e10adc3949ba59abbe56e057f20f883e
     */
    @PostMapping("/login")
    public CommonResult<LoginVO> login(@RequestParam String telephone, @RequestParam String password){
        CommonResult<LoginVO> result=new CommonResult<>();
        CheckUtil.checkNotBlank(telephone,"电话");
        CheckUtil.checkTelephone(telephone);

        CheckUtil.checkNotBlank(password,"密码");
        if(password.length()!=32){
            return result.err("请用md5加密密码");
        }

        LoginVO loginVO = userService.login(telephone, password.toLowerCase(Locale.ROOT));

        return result.body(loginVO);
    }

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
