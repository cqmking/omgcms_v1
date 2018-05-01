package org.omgcms.core.service;

import org.omgcms.core.model.Role;
import org.omgcms.core.model.User;
import org.omgcms.core.model.UserRole;

import java.util.List;

/**
 * @Author: Madfrog Yang
 * @Date: 2018/5/1 16:59
 * @Description:
 */
public interface UserRoleService {

    UserRole save(UserRole userRole);

    void deleteUserRole(long userId, long roleId);

    void deleteUserRoles(long userId, long[] roleIds);

    void deleteUserRoles(long[] userIds, long roleId);

    UserRole getUserRole(long userId, long roleId);

    List<UserRole> addUserRoles(User user, List<Role> roles);

    List<UserRole> addUserRoles(Role role, List<User> users);

    List<UserRole> addUserRoles(long userId, long[] roleIds);

    List<UserRole> addUserRoles(long[] userIds, long roleId);

    UserRole addUserRole(long userId, long roleId);
}
