package com.landao.hearu.model.common;

import com.landao.guardian.core.GuardianContext;
import com.landao.hearu.author.UserTokenBean;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 日志传输类
 */
@Data
@NoArgsConstructor
public class LogDTO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 访问的网址
     */
    private String url;

    /**
     * 传递的参数
     */
    private String args;

    /**
     * 返回的数据
     */
    private String returns;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 姓名
     */
    private String name;

    /**
     * 类与方法名
     */
    private String classMethod;

    /**
     * 开始时间
     */
    private Long startTime;


    public void setUserInfo(){
        if(GuardianContext.isLogin()){
            userId=GuardianContext.getUserId(Long.class);
            name=GuardianContext.getUser(UserTokenBean.class).getName();
        }
    }

}
