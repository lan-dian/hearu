package com.landao.hearu.model.user;


import com.landao.inspector.annotations.InspectField;
import com.landao.inspector.annotations.special.TelePhone;
import com.landao.inspector.annotations.special.group.AddGroup;
import com.landao.inspector.annotations.special.group.Id;
import com.landao.inspector.annotations.special.group.UpdateGroup;
import com.landao.inspector.model.Inspect;
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
    @InspectField(value = "用户名",max = 32)
    private String name;


    /**
     * 电话
     */
    @TelePhone
    @InspectField(value = "电话")
    private String telephone;

    /**
     * 性别
     */
    private String password;

    /**
     * 性别
     */
    private String sex;


    /**
     * 头像
     */
    @Nullable
    @InspectField(value = "头像",max = 128)
    private String avatar;


    /**
     * 个性签名
     */
    @Nullable
    @InspectField(value = "个性签名",max =64)
    private String signature;


    private LocalDate birth;



    @Override
    public void inspect(Class<?> group) {
        if(UpdateGroup.class.equals(group)){
            telephone=null;
            password=null;
        }else if(AddGroup.class.equals(group)){
            if(!StringUtils.hasText(password)){

            }
            password=password.toLowerCase(Locale.ROOT);//转化成小写
        }

        if(!(Objects.equals(sex,"女") || Objects.equals(sex,"男"))){
            throw new IllegalArgumentException("性别必须为男或女");
        }
        if(birth.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("出生日期不能晚于现在");
        }
    }


}
