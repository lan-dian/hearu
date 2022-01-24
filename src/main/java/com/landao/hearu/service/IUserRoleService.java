package com.landao.hearu.service;

import com.landao.hearu.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.landao.hearu.model.enums.RoleEnum;

import java.util.Set;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author 常珂洁
 * @since 2022-01-24
 */
public interface IUserRoleService extends IService<UserRole> {

    Set<RoleEnum> getRoles(Long userId);

}
