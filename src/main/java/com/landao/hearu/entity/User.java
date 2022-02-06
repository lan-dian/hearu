package com.landao.hearu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.landao.hearu.model.exception.BusinessException;
import com.landao.hearu.model.user.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author 常珂洁
 * @since 2022-01-24
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
     * 密码
     */
    private String password;

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


    public static User convert(UserInfo userInfo){
        User user = new User();
        user.setId(userInfo.getId());
        user.setName(userInfo.getName());
        user.setTelephone(userInfo.getTelephone());
        user.setPassword(userInfo.getPassword());
        user.setSex(userInfo.getSex());
        user.setAvatar(userInfo.getAvatar());
        user.setSignature(userInfo.getSignature());
        // user.setBirth(userInfo.getBirth());
        return user;
    }

    /**
     * 检查密码是否正确
     * @param password 待检测的密码
     */
    public void checkPassword(String password){
        if(!Objects.equals(password,this.password)){
            throw new BusinessException("密码错误");
        }
    }

}
