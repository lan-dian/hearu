package com.landao.hearu.author;

import com.landao.guardian.annotations.token.UserId;
import com.landao.hearu.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserTokenBean {

    @UserId
    private Long id;

    private String name;

    public static UserTokenBean convert(User user){
        UserTokenBean userTokenBean = new UserTokenBean();
        userTokenBean.setId(user.getId());
        userTokenBean.setName(user.getName());
        return userTokenBean;
    }

}
