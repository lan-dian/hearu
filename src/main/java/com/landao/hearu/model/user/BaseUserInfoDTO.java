package com.landao.hearu.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 最基本用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserInfoDTO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String name;

}
