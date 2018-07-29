package org.omgcms.web.rest.controller;

import org.omgcms.core.exception.CustomSystemException;
import org.omgcms.core.exception.ExceptionCode;
import org.omgcms.core.model.ResourcePermission;
import org.omgcms.core.model.Role;
import org.omgcms.core.service.ResourcePermissionService;
import org.omgcms.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 16:57 2018/7/29
 * @Modified By
 */
@RestController
public class ResourcePermissionAction {

    @Autowired
    private ResourcePermissionService resourcePermissionService;

    @Autowired
    private RoleService roleService;

    /**
     * 获取资源权限
     *
     * @param primaryKey   实体对象ID，system、model类型资源为0
     * @param resourceName 资源名称
     * @param scope        范围类型 system、model、instance 分别为1,2,3
     * @param roleId       角色id
     * @return 资源权限对象
     */
    @GetMapping("/resource-permission")
    public Object getResourcePermission(@RequestParam(value = "primaryKey", required = false, defaultValue = "0") Long primaryKey,
                                        @RequestParam(value = "resourceName") String resourceName,
                                        @RequestParam(value = "scope") Integer scope,
                                        @RequestParam(value = "roleId") Long roleId) {

        if (roleId == null || roleId <= 0) {
            throw new CustomSystemException(ExceptionCode.INVALID_PARAM_MESSAGE, "roleId");
        }

        Role role = roleService.getRole(roleId);

        if (role == null) {
            throw new CustomSystemException(ExceptionCode.ERROR_TARGET_OBJECT_NOT_EXIST, roleId);
        }

        ResourcePermission resourcePermission = resourcePermissionService
                .getByPrimaryKeyAndResourceNameAndRoleAndScope(primaryKey, resourceName, role, scope);

        return resourcePermission;

    }

    /**
     * 新增、更新资源权限
     *
     * @param actionIds    权限值 2,4,8,14 ....
     * @param primaryKey   实体对象ID，system、model类型资源为0
     * @param resourceName 资源名称
     * @param scope        范围类型 system、model、instance 分别为1,2,3
     * @param roleId       角色ID
     * @return 更新后资源权限对象
     */
    @PostMapping("/resource-permission")
    public Object updateResourcePermission(@RequestParam(value = "actionIds") Long actionIds,
                                           @RequestParam(value = "primaryKey", required = false, defaultValue = "0") Long primaryKey,
                                           @RequestParam(value = "resourceName") String resourceName,
                                           @RequestParam(value = "scope") Integer scope,
                                           @RequestParam(value = "roleId") Long roleId) {

        if (roleId == null || roleId <= 0) {
            throw new CustomSystemException(ExceptionCode.INVALID_PARAM_MESSAGE, "roleId");
        }

        Role role = roleService.getRole(roleId);

        if (role == null) {
            throw new CustomSystemException(ExceptionCode.ERROR_TARGET_OBJECT_NOT_EXIST, roleId);
        }

        ResourcePermission resourcePermission = resourcePermissionService
                .getByPrimaryKeyAndResourceNameAndRoleAndScope(primaryKey, resourceName, role, scope);

        if (resourcePermission == null) {
            // 新增
            resourcePermission = new ResourcePermission();
            resourcePermission.setResourceName(resourceName);
            resourcePermission.setActionIds(actionIds);
            resourcePermission.setPrimaryKey(primaryKey);
            resourcePermission.setRole(role);
            resourcePermission.setScope(scope);
        } else {
            // 更新
            resourcePermission.setResourceName(resourceName);
            resourcePermission.setActionIds(actionIds);
            resourcePermission.setPrimaryKey(primaryKey);
            resourcePermission.setRole(role);
            resourcePermission.setScope(scope);
        }

        ResourcePermission updatedResourcePermission = resourcePermissionService.save(resourcePermission);

        return updatedResourcePermission;
    }

}
