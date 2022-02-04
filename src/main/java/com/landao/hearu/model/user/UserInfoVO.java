package com.landao.hearu.model.user;

import com.landao.hearu.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
public class UserInfoVO {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 生日
     */
    private LocalDate birth;

    /**
     * 生日
     */
    private Integer age;

    public static UserInfoVO convert(User user){
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setId(user.getId());
        userInfoVO.setName(user.getName());
        userInfoVO.setTelephone(user.getTelephone());
        userInfoVO.setSex(user.getSex());
        userInfoVO.setAvatar(user.getAvatar());
        userInfoVO.setSignature(user.getSignature());
        userInfoVO.setBirth(user.getBirth());
        //计算年龄
        LocalDate birth = user.getBirth();
        LocalDate now = LocalDate.now();
        int age = Period.between(birth, now).getYears()+1;
        userInfoVO.setAge(age);
        return userInfoVO;
    }

}
