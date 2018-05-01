package org.omgcms.web.rest.controller;

import org.omgcms.core.model.Role;
import org.omgcms.core.service.RoleService;
import org.omgcms.web.constant.MessageKeys;
import org.omgcms.web.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/role/create")
    public Object createRole(@RequestParam(value = "name") String name,
                             @RequestParam(value = "roleKey") String roleKey,
                             @RequestParam(value = "description", defaultValue = "", required = false) String description) {

        Role role = new Role();
        role.setName(name);
        role.setRoleKey(roleKey);
        role.setDescription(description);

        Date now = new Date();
        role.setModifyDate(now);
        role.setCreateDate(now);

        Role newRole = roleService.save(role);

        return newRole;

    }

    @PostMapping("/role/update")
    public Object createRole(@RequestParam(value = "roleid") Long roleid,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "roleKey") String roleKey,
                             @RequestParam(value = "description", defaultValue = "", required = false) String description) {

        Role role = roleService.getRole(roleid);
        role.setName(name);

        role.setRoleKey(roleKey);
        role.setDescription(description);

        Date now = new Date();
        role.setModifyDate(now);

        Role updatedRole = roleService.save(role);
        return updatedRole;

    }


    @GetMapping("/roles")
    public Object getRoles(@RequestParam(defaultValue = "1") Integer pageNo,
                           @RequestParam(required = false, defaultValue = "20") Integer pageSize) {

        Page<Role> usersPage = roleService.findAll(pageNo, pageSize, "name", true);

        return usersPage;
    }

    @GetMapping("/role/delete")
    public Object deleteRole(@RequestParam(defaultValue = "0") Long roleId) {
        roleService.delete(roleId);
        return MessageUtil.getMessageMap(MessageKeys.MSG_SUCCESS);
    }

}
