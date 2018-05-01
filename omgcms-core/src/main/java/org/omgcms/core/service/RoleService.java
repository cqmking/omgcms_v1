package org.omgcms.core.service;

import org.omgcms.core.model.Role;
import org.springframework.data.domain.Page;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 2:34 2018/4/7
 * @Modified By
 */
public interface RoleService {

    /**
     * 新增或修改角色信息
     *
     * @param role 角色
     * @return 修改后的角色
     */
    Role save(Role role);

    Role getRole(long roleId);

    void delete(long roleId);

    Role findByName(String name);

    Role findByRoleKey(String roleKey);

    Page<Role> findAll(int pageNo, int pageSize, String orderByProperty, boolean isAsc);
}
