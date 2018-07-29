package org.omgcms.core.service;

import org.omgcms.core.model.ResourcePermission;
import org.omgcms.core.model.Role;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 17:04 2018/7/29
 * @Modified By
 */
public interface ResourcePermissionService {

    /**
     * 新增、更新资源权限
     *
     * @param resourcePermission 资源权限对象
     * @return 更新后的资源权限
     */
    ResourcePermission save(ResourcePermission resourcePermission);

    /**
     * 根据条件获取资源权限
     *
     * @param primaryKey   实体对象主键，scope除instance外，其他类型为0
     * @param resourceName 资源名称
     * @param role         角色
     * @param scope        范围，类型 1 system 2 model 3 instance
     * @return 资源权限对象
     */
    ResourcePermission getByPrimaryKeyAndResourceNameAndRoleAndScope(long primaryKey, String resourceName, Role role, int scope);
}
