package org.omgcms.core.repository;

import org.omgcms.core.model.UserRole;
import org.omgcms.core.model.pk.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: Madfrog Yang
 * @Date: 2018/5/1 16:38
 * @Description:
 */
public interface UserRoleRepository extends JpaSpecificationExecutor<UserRole>, JpaRepository<UserRole, UserRolePK> {



}
