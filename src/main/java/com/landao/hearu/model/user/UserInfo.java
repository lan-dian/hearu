package com.landao.hearu.model.user;


import com.landao.inspector.annotations.Inspected;
import com.landao.inspector.annotations.special.TelePhone;
import com.landao.inspector.annotations.special.group.Id;
import com.landao.inspector.annotations.special.group.UpdateIgnore;
import com.landao.inspector.core.Inspect;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

/**
 * 用户注册
 */
@Data
@NoArgsConstructor
public class UserInfo implements Inspect {

    @Id
    private Long id;

    /**
     * 用户名
     * @apiNote 最大长度32
     */
    @Inspected(value = "用户名",max = 32)
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
    private String password;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像
     */
    @Nullable
    @Inspected(value = "头像",max = 128)
    private String avatar;

    /**
     * 个性签名
     */
    @Nullable
    @Inspected(value = "个性签名",max =64)
    private String signature;

    private LocalDate birth;


    @Override
    public void inspect(Class<?> group,String supperName) {
        if(isAddGroup(group)){
            if(!StringUtils.hasText(password)){
                addIllegal("password","密码不能为空");
            }
            if(password.length()!=32){
                addIllegal("password","密码必须用md5加密");
            }
            password=password.toLowerCase(Locale.ROOT);//转化成小写
        }
        if(!(Objects.equals(sex,"女") || Objects.equals(sex,"男"))){
            addIllegal("sex","性别必须为男或女");
        }
        if(birth==null){
            addIllegal("birth","生日不能为空");
        }else {
            if(birth.isAfter(LocalDate.now())){
                addIllegal("birth","出生日期不能晚于现在");
            }
        }
    }




}
