package com.landao.hearu.model.page.comment;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简略用户信息
 */
@Data
@NoArgsConstructor
public class SimpleUserVO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String name;


}
