package com.landao.hearu.business.impl;

import com.landao.hearu.business.UserService;
import com.landao.hearu.entity.User;
import com.landao.hearu.entity.UserRole;
import com.landao.hearu.model.enums.RoleEnum;
import com.landao.hearu.model.exception.BusinessException;
import com.landao.hearu.model.user.UserInfo;
import com.landao.hearu.service.IUserRoleService;
import com.landao.hearu.service.IUserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    IUserService iUserService;

    @Resource
    IUserRoleService iUserRoleService;


    @Override
    @Transactional(rollbackFor = Throwable.class)
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
