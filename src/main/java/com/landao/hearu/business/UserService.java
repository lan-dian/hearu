package com.landao.hearu.business;

import com.landao.hearu.model.enums.RoleEnum;
import com.landao.hearu.model.user.LoginVO;
import com.landao.hearu.model.user.UserInfo;
import com.landao.hearu.model.user.UserInfoVO;

public interface UserService {

    UserInfoVO getUserInfo(Long userId);

    LoginVO login(String telephone, String password);

    boolean registerUser(UserInfo userInfo, RoleEnum roleEnum);

    boolean changeUserInfo(UserInfo userInfo);

}
