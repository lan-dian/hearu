package com.landao.hearu.business;

import com.landao.hearu.model.enums.RoleEnum;
import com.landao.hearu.model.user.UserInfo;

public interface UserService {

    boolean registerUser(UserInfo userInfo, RoleEnum roleEnum);

}
