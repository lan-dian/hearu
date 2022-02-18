package com.landao.hearu.model.user;


import com.landao.checker.annotations.Check;
import com.landao.checker.annotations.special.Before;
import com.landao.checker.annotations.special.TelePhone;
import com.landao.checker.annotations.special.group.Id;
import com.landao.checker.annotations.special.group.UpdateIgnore;
import com.landao.checker.core.Checked;
import com.landao.checker.utils.CheckUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.Locale;

/**
 * 用户注册
 */
@Data
@NoArgsConstructor
public class UserInfo implements Checked {

    @Id
    private Long id;

    /**
     * 用户名
     * @apiNote 最大长度32
     */
    @Check(value = "用户名",max = 32)
    private String name;

    /**
     * 电话
     */
    @TelePhone
    @UpdateIgnore
    private String telephone;

    /**
     * 密码
     */
    @UpdateIgnore
    @Check(value = "密码",ignore = true)
    private String password;

    /**
     * 性别
     */
    @Check(value = "性别")
    private String sex;

    /**
     * 头像
     */
    @Nullable
    @Check(value = "头像",max = 128)
    private String avatar;

    /**
     * 个性签名
     */
    @Nullable
    @Check(value = "个性签名",max =64)
    private String signature;

    @Before("生日")
    private LocalDate birth;

    @Override
    public void addCheck(String supperName) {
        password=CheckUtils.string(password,32,32,"password","密码");
        password=password.toLowerCase(Locale.ROOT);//转化成小写
    }

    @Override
    public void check(Class<?> group,String supperName) {
        CheckUtils.in(sex,"sex","性别","男","女");
    }



}
