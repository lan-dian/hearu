package com.landao.hearu.author;

import com.landao.guardian.annotations.system.GuardianService;
import com.landao.guardian.core.TokenService;
import com.landao.hearu.entity.User;
import com.landao.hearu.entity.UserRole;
import com.landao.hearu.model.enums.RoleEnum;
import com.landao.hearu.model.user.LoginVO;
import com.landao.hearu.model.user.UserInfo;
import com.landao.hearu.model.user.UserInfoVO;
import com.landao.hearu.service.IUserRoleService;
import com.landao.hearu.service.IUserService;
import com.landao.web.plus.model.exception.BusinessException;
import org.springframework.dao.DuplicateKeyException;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

@GuardianService
public class UserService extends TokenService<UserTokenBean,Long> {

    @Resource
    IUserService iUserService;

    @Resource
    IUserRoleService iUserRoleService;

    @Override
    public Set<String> getRoles() {
        return iUserRoleService.getRoles(getUserId()).stream().map(Enum::name).collect(Collectors.toSet());
    }


    public boolean changePassword(String oldPassword, String newPassword){
        Integer count = iUserService.lambdaQuery()
                .eq(User::getId, getUserId())
                .eq(User::getPassword, oldPassword)
                .count();
        if(count==0){
            throw new BusinessException("原密码错误");
        }

        return iUserService.lambdaUpdate()
                .set(User::getPassword, newPassword)
                .eq(User::getId, getUserId())
                .update();
    }

    public boolean changeUserInfo(UserInfo userInfo) {
        User user = User.convert(userInfo);
        return iUserService.updateById(user);
    }

    public UserInfoVO getUserInfo(Long userId){
        User user = iUserService.getById(userId);
        return UserInfoVO.convert(user);
    }

    public LoginVO login(String telephone, String password){
        //查询用户
        User user = iUserService.lambdaQuery()
                .eq(User::getTelephone, telephone).one();
        if(user==null){
            throw new BusinessException("用户不存在,请先注册");
        }
        user.checkPassword(password);

        //获取角色列表
        Set<RoleEnum> roles = iUserRoleService.getRoles(user.getId());

        UserTokenBean userTokenBean=UserTokenBean.convert(user);

        String token = parseToken(userTokenBean);

        return new LoginVO(token,user.getName(),roles);
    }


    // @Transactional(rollbackFor = Throwable.class)
    public boolean registerUser(UserInfo userInfo,RoleEnum roleEnum){
        User user = User.convert(userInfo);

        boolean saveUser;
        try {
            saveUser=iUserService.save(user);
            if(saveUser){
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId())
                        .setRole(roleEnum);
                boolean saveRole = iUserRoleService.save(userRole);
                if(saveRole){
                    return true;
                }else {
                    throw new BusinessException("用户角色添加失败");
                }
            }else {
                return false;
            }
        }catch (DuplicateKeyException e){
            throw new BusinessException("电话重复");
        }

    }


}
