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
import java.util.Objects;

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

    /**
     * 修改个人信息
     * @param userInfo 不要传递telephone和password
     * userId 也不需要传递
     */
    @RequiredLogin
    @PostMapping("/change/info")
    public CommonResult<Void> changeInfo(@RequestBody UserInfo userInfo){
        CommonResult<Void> result=new CommonResult<>();
        userInfo.setId(TokenUtil.getUserId());
        userInfo.updateCheck();

        boolean update = userService.changeUserInfo(userInfo);

        return result.ok(update);
    }

    /**
     * 修改密码
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @apiNote 两个都用md5加密
     */
    @RequiredLogin
    @PostMapping("/change/password")
    public CommonResult<Void> changePassword(@RequestParam String oldPassword,@RequestParam String newPassword){
        CommonResult<Void> result=new CommonResult<>();

        CheckUtil.checkNotBlank(oldPassword,"旧密码");
        if(oldPassword.length()!=32){
            return result.err("旧密码请用md5加密");
        }
        CheckUtil.checkNotBlank(newPassword,"新密码");
        if(newPassword.length()!=32){
            return result.err("新密码请用md5加密");
        }
        if(Objects.equals(oldPassword,newPassword)){
            return result.err("新密码不能和旧密码相同");
        }

        boolean update = userService.changePassword(TokenUtil.getUserId(),oldPassword,newPassword);

        return result.ok(update);
    }

}
