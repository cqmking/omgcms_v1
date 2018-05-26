package org.omgcms.web.rest.controller;

import org.omgcms.core.exception.CustomSystemException;
import org.omgcms.core.exception.ExceptionCode;
import org.omgcms.core.model.Role;
import org.omgcms.core.service.RoleService;
import org.omgcms.web.constant.MessageKeys;
import org.omgcms.web.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: Madfrog Yang
 * @Date: 2018/5/1 16:04
 * @Description:
 */
@RestController
public class RoleAction {

    private static Logger logger = LoggerFactory.getLogger(RoleAction.class);

    @Autowired
    private RoleService roleService;

    /**
     * 添加/更新角色形象
     *
     * @param roleId      角色ID，大于0时为修改
     * @param name        角色名称
     * @param roleKey     角色编码
     * @param description 角色描述
     * @return
     */
    @PostMapping("/role")
    public Object addOrUpdateRole(@RequestParam(value = "roleId") Long roleId,
                                  @RequestParam(value = "name") String name,
                                  @RequestParam(value = "roleKey") String roleKey,
                                  @RequestParam(value = "description", defaultValue = "", required = false) String description) {

        Role role;
        Date now = new Date();

        if (roleId == null || roleId <= 0) {
            //创建
            role = new Role();
            role.setCreateDate(now);
        } else {
            //修改
            role = roleService.getRole(roleId);
        }

        role.setName(name);
        role.setRoleKey(roleKey);
        role.setDescription(description);
        role.setModifyDate(now);

        Role newRole = roleService.save(role);

        return newRole;

    }

    @GetMapping("/roles")
    public Object getRoles(@RequestParam(defaultValue = "1") Integer pageNo,
                           @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        Page<Role> usersPage = roleService.findAll(pageNo, pageSize, "name", true);

        return usersPage;
    }

    @GetMapping("/role")
    public Object getRoleByRoleId(@RequestParam(value = "roleId", defaultValue = "0") Long roleId) {

        if (roleId == null || roleId <= 0) {
            throw new CustomSystemException(ExceptionCode.INVALID_PARAM_MESSAGE, "roleId");
        }

        Role role = roleService.getRole(roleId);

        return role;
    }

    @DeleteMapping("/role/delete")
    public Object deleteRole(@RequestParam(defaultValue = "0") Long roleId) {
        roleService.delete(roleId);
        return MessageUtil.getMessageMap(MessageKeys.MSG_SUCCESS);
    }


    /**
     * 获取用户具备的所有角色
     *
     * @param userId   用户ID
     * @param pageNo   第几页
     * @param pageSize 每页显示条数
     * @return 角色列表
     */
    @GetMapping("/user/roles")
    public Object getUserRoles(@RequestParam(value = "userId") Long userId,
                               @RequestParam(defaultValue = "1") Integer pageNo,
                               @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        if (userId == null || userId <= 0) {
            throw new CustomSystemException(ExceptionCode.INVALID_PARAM_MESSAGE, "userId");
        }

        Page<Role> rolesPage = roleService.getRolesByUserId(pageNo, pageSize, "name", true, userId);

        return rolesPage;
    }


    /**
     * 获取未分配给该用户的角色列表
     *
     * @param userId   用户ID
     * @param pageNo   当前页
     * @param pageSize 每页显示条数
     * @return 角色列表
     */
    @GetMapping("/user/unassigned-roles")
    public Object getUserUnassignedRoles(@RequestParam(value = "userId") Long userId,
                                         @RequestParam(defaultValue = "1") Integer pageNo,
                                         @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        if (userId == null || userId <= 0) {
            throw new CustomSystemException(ExceptionCode.INVALID_PARAM_MESSAGE, "userId");
        }

        Page<Role> unassignedUserRoles = roleService.getUnassignedUserRoles(pageNo, pageSize, "name", true, userId);

        return unassignedUserRoles;
    }


}
