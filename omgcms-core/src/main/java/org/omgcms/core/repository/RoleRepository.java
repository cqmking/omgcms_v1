package org.omgcms.core.repository;

import org.omgcms.core.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 2:33 2018/4/7
 * @Modified By
 */
@Repository
public interface RoleRepository extends JpaSpecificationExecutor<Role>, JpaRepository<Role, Long> {

    Role findByName(String name);

    Role findByRoleKey(String roleKey);

    Page<Role> findByNameLikeAndRoleKeyLike(String name, String roleKey, Pageable pageable);

}
