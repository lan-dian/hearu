package com.landao.hearu.service.impl;

import com.landao.hearu.entity.UserRole;
import com.landao.hearu.mapper.UserRoleMapper;
import com.landao.hearu.model.enums.RoleEnum;
import com.landao.hearu.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author 常珂洁
 * @since 2022-01-24
 */
@Service
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {


    /**
     * 获取用户角色
     * @param userId 用户id
     */
    @Override
    public Set<RoleEnum> getRoles(Long userId){
        List<UserRole> userRoles = lambdaQuery().select(UserRole::getRole)
                .eq(UserRole::getUserId, userId).list();
        return userRoles.stream().map(UserRole::getRole).collect(Collectors.toSet());
    }


}
