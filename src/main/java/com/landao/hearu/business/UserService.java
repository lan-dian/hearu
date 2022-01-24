package com.landao.hearu.business;

import com.landao.hearu.model.enums.RoleEnum;
import com.landao.hearu.model.user.LoginVO;
import com.landao.hearu.model.user.UserInfo;

public interface UserService {

    LoginVO login(String telephone, String password);

    boolean registerUser(UserInfo userInfo, RoleEnum roleEnum);

}
