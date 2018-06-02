package org.omgcms.core.service.impl;

import org.omgcms.core.model.Role;
import org.omgcms.core.model.User;
import org.omgcms.core.model.UserRole;
import org.omgcms.core.model.pk.UserRolePK;
import org.omgcms.core.repository.RoleRepository;
import org.omgcms.core.repository.UserRepository;
import org.omgcms.core.repository.UserRoleRepository;
import org.omgcms.core.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Madfrog Yang
 * @Date: 2018/5/1 16:59
 * @Description:
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    public void deleteUserRole(long userId, long roleId) {
        User user = userRepository.findOne(userId);
        Role role = roleRepository.findOne(roleId);
        UserRole userRole = new UserRole(user, role);
        userRoleRepository.delete(userRole);
    }

    public void deleteUserRoles(long userId, long[] roleIds) {
        for (long roleId : roleIds) {
            deleteUserRole(userId, roleId);
        }
    }

    public void deleteUserRoles(long[] userIds, long roleId) {
        for (long userId : userIds) {
            deleteUserRole(userId, roleId);
        }
    }

    public UserRole getUserRole(long userId, long roleId) {
        UserRolePK pk = new UserRolePK(userId, roleId);
        return userRoleRepository.getOne(pk);
    }

    public List<UserRole> addUserRoles(User user, List<Role> roles) {

        List<UserRole> userRoles = new ArrayList<UserRole>();
        for (Role role : roles) {
            UserRole userRole = new UserRole(user, role);
            UserRole savedObj = userRoleRepository.save(userRole);
            if (!userRoles.contains(savedObj)) {
                userRoles.add(savedObj);
            }
        }

        userRoleRepository.flush();

        return userRoles;
    }

    public List<UserRole> addUserRoles(Role role, List<User> users) {

        List<UserRole> userRoles = new ArrayList<UserRole>();
        for (User user : users) {
            UserRole userRole = new UserRole(user, role);
            UserRole savedObj = userRoleRepository.save(userRole);
            if (!userRoles.contains(savedObj)) {
                userRoles.add(savedObj);
            }
        }

        userRoleRepository.flush();

        return userRoles;
    }

    public List<UserRole> addUserRoles(long userId, long[] roleIds) {

        List<UserRole> userRoles = new ArrayList<UserRole>();

        for (long roleId : roleIds) {
            UserRole savedUserRole = addUserRole(userId, roleId);
            userRoles.add(savedUserRole);
        }

        return userRoles;
    }

    public List<UserRole> addUserRoles(List<User> users, Role role) {
        List<UserRole> userRoles = new ArrayList<UserRole>();
        for (User user : users) {
            UserRole ur = new UserRole(user, role);
            userRoles.add(ur);
        }
        List<UserRole> savedUserRoles = userRoleRepository.save(userRoles);
        return savedUserRoles;
    }

    public List<UserRole> addUserRoles(long[] userIds, long roleId) {

        List<UserRole> userRoles = new ArrayList<UserRole>();

        for (long userId : userIds) {
            UserRole savedUserRole = addUserRole(userId, roleId);
            userRoles.add(savedUserRole);
        }

        return userRoles;
    }

    public UserRole addUserRole(long userId, long roleId) {
        User user = userRepository.findOne(userId);
        Role role = roleRepository.findOne(roleId);
        UserRole userRole = new UserRole(user, role);
        UserRole savedUserRole = userRoleRepository.save(userRole);
        return savedUserRole;
    }


}
