package org.omgcms.core.service.impl;

import org.omgcms.core.model.ResourcePermission;
import org.omgcms.core.model.Role;
import org.omgcms.core.repository.ResourcePermissionRepository;
import org.omgcms.core.service.ResourcePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 18:26 2018/7/29
 * @Modified By
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ResourcePermissionServiceImpl implements ResourcePermissionService {

    @Autowired
    private ResourcePermissionRepository resourcePermissionRepository;

    public ResourcePermission save(ResourcePermission resourcePermission) {
        return resourcePermissionRepository.save(resourcePermission);
    }

    public ResourcePermission getByPrimaryKeyAndResourceNameAndRoleAndScope(long primaryKey, String resourceName, Role role, int scope){
       return resourcePermissionRepository.getByPrimaryKeyAndResourceNameAndRoleAndScope(primaryKey, resourceName, role, scope);
    }

}
