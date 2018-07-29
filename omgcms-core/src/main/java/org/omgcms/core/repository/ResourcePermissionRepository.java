package org.omgcms.core.repository;

import org.omgcms.core.model.ResourcePermission;
import org.omgcms.core.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 16:24 2018/7/29
 * @Modified By
 */
@Repository
public interface ResourcePermissionRepository extends JpaSpecificationExecutor<ResourcePermission>, JpaRepository<ResourcePermission, Long> {

    ResourcePermission getByPrimaryKeyAndResourceNameAndRoleAndScope(long primaryKey, String resourceName, Role role, int scope);

}
