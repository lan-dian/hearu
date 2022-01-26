package com.landao.hearu.model.page.topic;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 详细用户信息
 */
@Data
@NoArgsConstructor
public class DetailUserVO {

    /**
     * 用户id
     */
    private String userId;

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


}
