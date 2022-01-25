package com.landao.hearu.model.user;


import com.landao.hearu.model.common.BaseCheckInfo;
import com.landao.hearu.util.check.CheckUtil;
import com.landao.hearu.util.check.annotaions.FieldCheck;
import com.landao.hearu.util.check.enums.BaseCheck;
import com.landao.hearu.util.check.enums.CheckType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

/**
 * 用户注册
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends BaseCheckInfo {

    /**
     * 用户名
     * @apiNote 最大长度32
     */
    @FieldCheck(value = "用户名",maxLength = 32,baseCheck = BaseCheck.NotBlack,checkType = CheckType.LengthLimit)
    private String name;

    /**
     * 电话
     * @apiNote 长度必须为11
     * 用电话登陆
     */
    private String telephone;

    /**
     * 密码
     * @apiNote md5加密后传递
     */
    @FieldCheck(value = "密码",maxLength = 32,baseCheck = BaseCheck.NotBlack,checkType = CheckType.LengthLimit)
    private String password;

    /**
     * 性别
     * @apiNote 必须为男或女
     */
    @FieldCheck(value = "性别",maxLength = 1,baseCheck = BaseCheck.NotBlack,checkType = CheckType.LengthLimit)
    private String sex;

    /**
     * 头像
     * @apiNote 可以为空
     */
    @FieldCheck(value = "头像",maxLength = 128,baseCheck = BaseCheck.CanNull,checkType = CheckType.LengthLimit)
    private String avatar;

    /**
     * 个性签名
     * @apiNote 可以为空
     */
    @FieldCheck(value = "个性签名",maxLength =64,baseCheck = BaseCheck.CanNull,checkType = CheckType.LengthLimit)
    private String signature;

    /**
     * 生日
     */
    @FieldCheck(value = "生日")
    private LocalDate birth;

    @Override
    public void updateCheck() {
        super.updateCheck();
        this.telephone=null;
        this.password=null;
    }

    @Override
    protected void commonCheck() {
        super.commonCheck();
        CheckUtil.checkNotBlank(telephone,"电话");
        CheckUtil.checkTelephone(telephone);
        if(password.length()!=32){
            throw new IllegalArgumentException("密码请用md5加密");
        }
        password=password.toLowerCase(Locale.ROOT);//装换成小写
        if(!(Objects.equals(sex,"女") || Objects.equals(sex,"男"))){
            throw new IllegalArgumentException("性别必须为男或女");
        }
        if(birth.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("出生日期不能晚于现在");
        }
    }
}
