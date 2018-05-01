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

    /**
     * 获取用户的角色列表
     *
     * @param pageNo            页码，第几页
     * @param pageSize          每页显示条数
     * @param orderByProperty   排序字段
     * @param isAsc             排序类型（升序、降序）
     * @param userId            用户ID
     * @return
     */
    Page<Role> getRolesByUserId(int pageNo, int pageSize, String orderByProperty, boolean isAsc, long userId);

    /**
     * 获取用户不具备的所有角色列表
     *
     * @param pageNo            页码，第几页
     * @param pageSize          每页显示条数
     * @param orderByProperty   排序字段
     * @param isAsc             排序类型（升序、降序）
     * @param userId            用户ID
     * @return
     */
    Page<Role> getUnassignedUserRoles(int pageNo, int pageSize, String orderByProperty,  boolean isAsc, long userId);

}
